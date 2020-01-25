package nighcode.server

import org.http4k.core.*
import org.http4k.core.Method.GET
import org.http4k.core.Method.POST
import org.http4k.core.Status.Companion.BAD_REQUEST
import org.http4k.core.Status.Companion.OK
import org.http4k.filter.ServerFilters
import org.http4k.format.Jackson.auto
import org.http4k.lens.Query
import org.http4k.lens.nonEmptyString
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Jetty
import org.http4k.server.asServer

data class Name(val name: String)
data class Greetings(val message: String)

fun main(args: Array<String>) {
    var queryNameLens = Query.nonEmptyString().defaulted("name", "world");
    val nameLens = Body.auto<Name>().toLens()
    val greetingsLens = Body.auto<Greetings>().toLens()

    val greetingsHandler= routes(
            GET to { request: Request ->
                val name: String = queryNameLens.extract(request)
                Response(OK).with(greetingsLens of Greetings("hello, ${name}"))
            },
            POST to { request: Request ->
                val name: String = nameLens.extract(request).name
                if (name?.length === 0) Response(BAD_REQUEST)
                else Response(OK).with(greetingsLens of Greetings("hello, ${name}"))
            }
    )

    val endpoint = routes(
        "/" bind GET to { Response(OK).body("Nightcode") },
        "/api" bind routes(
                "/greetings" bind greetingsHandler
        )
    )

    // Convert lenses conversion errors (500) into bad request (400)
    val app = ServerFilters.CatchLensFailure.then(endpoint)

    app.asServer(Jetty(3000)).start()
}
