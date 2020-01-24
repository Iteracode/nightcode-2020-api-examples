package nighcode.server

import org.http4k.core.Body
import org.http4k.core.Method.DELETE
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.format.Jackson.auto
import org.http4k.core.with

data class Greetings(val message: String)

fun main(args: Array<String>) {
    val greetingsLens = Body.auto<Greetings>().toLens()

    // val app = { request: Request -> Response(OK).body("Nightcode") }
    val app = routes(
        "/" bind GET to { Response(OK).body("Nightcode") },
        "/api/greetings" bind GET to { Response(OK).with(greetingsLens of Greetings("hello, world")) }
    )

    app.asServer(Jetty(3000)).start()
}
