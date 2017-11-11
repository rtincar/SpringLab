package io.rtincar.kanbanboard.configuration


import io.rtincar.kanbanboard.web.AccountHandler
import io.rtincar.kanbanboard.web.AccountRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
@EnableWebFlux
class AccountConfiguration {

    @Bean
    fun accountHandler() = AccountHandler()

    @Bean
    fun accountRouter(accountHandler: AccountHandler) = AccountRouter(accountHandler).accountRouter()

}