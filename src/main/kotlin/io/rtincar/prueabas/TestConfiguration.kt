package io.rtincar.prueabas

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.web.embedded.netty.NettyReactiveWebServerFactory
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.server.adapter.WebHttpHandlerBuilder

@Configuration
class TestConfiguration {

    @Autowired
    lateinit var context: ApplicationContext


    @Bean
    fun reactiveWebServerFactory() = NettyReactiveWebServerFactory(9090)

    @Bean
    fun httpHandler() = WebHttpHandlerBuilder.applicationContext(context).build()
}