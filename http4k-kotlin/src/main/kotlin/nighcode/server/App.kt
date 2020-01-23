package nighcode.server

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.server.Jetty
import org.http4k.server.asServer

fun main(args: Array<String>) {
    val app = { request: Request -> Response(OK).body("Nightcode") }

    app.asServer(Jetty(3000)).start()

}
