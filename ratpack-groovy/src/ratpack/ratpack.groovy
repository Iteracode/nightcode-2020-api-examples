import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json
import static ratpack.jackson.Jackson.fromJson

class NameContainer {
    String name
}

ratpack {
    handlers {
        get("") {
            render("Nightcode")
        }
        path("api/greetings") {
            byMethod {
                get { context ->
                    def name = request.queryParams.name != null
                        ? request.queryParams.name
                        : "world"

                    name.isEmpty()
                        ? context.response.status(400).send()
                        : render(json([message: "hello, ${name}".toString()]))
                }
                post { context ->
                    parse(fromJson(NameContainer))
                        .then { 
                            def name = it.name != null
                                ? it.name
                                : "world"

                        name.isEmpty()
                            ? context.response.status(400).send()
                            : render(json([message: "hello, ${name}".toString()]))
                        }
                }
            }
        }
    }
}