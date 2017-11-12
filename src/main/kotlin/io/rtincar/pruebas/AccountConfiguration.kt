package io.rtincar.kanbanboard.configuration


import io.rtincar.kanbanboard.web.AccountHandler
import io.rtincar.kanbanboard.web.AccountRouter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.EnableWebFlux

@Configuration
@EnableWebFlux
class AccountConfiguration {

    @Bean
    fun accountHandler() = AccountHandler()

    @Bean
    fun accountRouter(accountHandler: AccountHandler) = AccountRouter(accountHandler).accountRouter()

}