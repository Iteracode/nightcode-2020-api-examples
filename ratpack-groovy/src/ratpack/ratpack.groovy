import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    handlers {
        get("") {
            render("Nightcode")
        }
        get("api/greetings") { context ->
            def name = request.queryParams.name != null
                ? request.queryParams.name
                : "world"

            name.isEmpty()
                ? context.response.status(400).send()
                : render(json([message: "hello, ${name}".toString()]))
        }
    }
}