package io.rtincar.prueabas

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.RouterFunctions
import org.springframework.web.reactive.function.server.ServerResponse

@Configuration
class TestConfiguration {


    @Bean
    fun reactiveWebServerFactory() = NettyReactiveWebServerFactory(9090)

    @Autowired
    @Bean
    fun httpHandler(accountRouter: RouterFunction<ServerResponse>) = RouterFunctions.toHttpHandler(accountRouter)
}