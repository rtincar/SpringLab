package io.rtincar.pruebas;

import io.rtincar.kanbanboard.configuration.AccountConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.web.reactive.function.BodyInserters;

@RunWith(SpringRunner.class)
@SpringBootTest(
        classes = AccountConfiguration.class,
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT
)
public class AccountIntegrationTest {

    @LocalServerPort
    Integer port;

    WebTestClient client = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:9090")
            .build();



    @Test
    public void createAccount() throws Exception {
        String jsonAccount = "{\"a\" : \"2\", \"b\" : \"7\"}";

        WebTestClient.ResponseSpec exchange = client.post()
                .uri("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(jsonAccount))
                .exchange();

        exchange.expectStatus().isOk();
    }
}
