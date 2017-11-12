package io.rtincar.kanbanboard.web

import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono
import reactor.core.publisher.toMono

class AccountHandler {

    fun createAccount(req: ServerRequest): Mono<ServerResponse> {
        return ok().body("{\"status\" : \"ok\"}".toMono())
    }

}