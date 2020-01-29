import static ratpack.groovy.Groovy.ratpack
import static ratpack.jackson.Jackson.json

ratpack {
    handlers {
        get("api/greetings") {
            render(json([message: "hello, world"]))
        }
    }
}