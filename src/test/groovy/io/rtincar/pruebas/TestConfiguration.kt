package io.rtincar.pruebas

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.boot.web.reactive.server.ReactiveWebServerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.server.adapter.WebHttpHandlerBuilder

/**
 *
 * In order to start the server for tests, we have to define an
 * instance of ReactiveWebServerFactory and an instance of
 * HttpHandler. If they are not provided, the context will fail
 * during initialization
 *
 *
 */
@Configuration
class TestConfiguration {

    @Autowired
    lateinit var context: ApplicationContext

    @Bean
    fun reactiveWebServerFactory(): ReactiveWebServerFactory {
        val port = context.environment.getProperty("server.port")?.toInt() ?: 9090
        return NettyReactiveWebServerFactory(port)
    }

    @Bean
    fun httpHandler() = WebHttpHandlerBuilder.applicationContext(context).build()
}