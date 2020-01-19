package iteracode

import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Produces
import io.micronaut.http.MediaType

@Controller("/") 
class RootController {
    @Get
    @Produces(MediaType.TEXT_PLAIN)
    String index() {
        "Nightcode"
    }
}