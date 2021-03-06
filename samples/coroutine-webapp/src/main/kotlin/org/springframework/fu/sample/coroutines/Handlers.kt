package org.springframework.fu.sample.coroutines

import org.springframework.fu.module.webflux.coroutine.web.function.server.CoroutineServerRequest
import org.springframework.fu.module.webflux.coroutine.web.function.server.body
import org.springframework.fu.module.webflux.coroutine.web.function.server.coroutineHandler
import org.springframework.http.MediaType

@Suppress("UNUSED_PARAMETER")
class UserHandler(private val repository: UserRepository,
				  private val configuration: SampleConfiguration) {

	suspend fun listApi(request: CoroutineServerRequest) = coroutineHandler {
		ok().contentType(MediaType.APPLICATION_JSON_UTF8).body(repository.findAll())
	}

	suspend fun listView(request: CoroutineServerRequest) = coroutineHandler {
		ok().render("users", mapOf("users" to repository.findAll()))
	}

	suspend fun conf(request: CoroutineServerRequest) = coroutineHandler {
		ok().syncBody(configuration.property)
	}

}