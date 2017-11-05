package io.rtincar.pruebas

import groovy.json.JsonOutput
import io.rtincar.kanbanboard.configuration.AccountConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title


@Title('Create new account')
@Narrative('''

In order to use the application
As a non registered user 
I want to create an account

''')
//@ContextConfiguration(classes = [AccountConfiguration])
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = [AccountConfiguration]/*,
        properties = [ "spring.main.webApplicationType=reactive" ]*/
)
class AccountSpec extends Specification {

    @LocalServerPort
    Integer port

    WebTestClient client = WebTestClient
            .bindToServer()
            .baseUrl("http://localhost:${port}")
            .build()

    void "Should return ok"() {
        given: "JSON account data"

        def jsonAccount = JsonOutput.toJson([a: 2, b: 'Data'])

        when: "Try to create an account"

        def exchange = client.post()
                .uri("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(jsonAccount))
                .exchange()

        then: "Should get ok status"

        exchange.expectStatus().isOk()
        exchange.expectBody().json("{\"status\" : \"ok\"}")

    }

}
