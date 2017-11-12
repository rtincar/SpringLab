package io.rtincar.pruebas

import groovy.json.JsonOutput
import io.rtincar.kanbanboard.configuration.AccountConfiguration
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.web.server.LocalServerPort
import org.springframework.http.MediaType
import org.springframework.test.web.reactive.server.WebTestClient
import org.springframework.web.reactive.function.BodyInserters
import spock.lang.Narrative
import spock.lang.Shared
import spock.lang.Specification
import spock.lang.Title

@Title('Create new account')
@Narrative('''

In order to use the application
As a non registered user 
I want to create an account

''')
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        classes = [TestConfiguration, AccountConfiguration]
)
class AccountSpec extends Specification {

    @LocalServerPort
    Integer port

    /**
     * If we create several future methods, we reuse the same instance
     */
    @Shared
    WebTestClient testClient

    def setup() {
        testClient = WebTestClient
                .bindToServer()
                .baseUrl("http://localhost:${port}")
                .build()
    }

    void "Should create an account"() {

        given: "JSON account data"

        def jsonAccount = JsonOutput.toJson([
                firstName: 'John',
                lastName: 'Doe',
                email: 'account@domain.com',
                pasword: 'secret',
                confirmationPassword: 'secret'
        ])

        when: "User send the new account data"

        def exchange = testClient.post()
                .uri("/api/v1/account")
                .contentType(MediaType.APPLICATION_JSON)
                .body(BodyInserters.fromObject(jsonAccount))
                .exchange()

        then: "Should get ok status"

        exchange.expectStatus().isOk()
        exchange.expectBody().json("{\"status\" : \"ok\"}")

    }

}
