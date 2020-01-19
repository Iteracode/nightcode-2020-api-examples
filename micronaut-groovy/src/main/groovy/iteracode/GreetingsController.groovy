package iteracode

import groovy.transform.CompileStatic
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.Controller
import io.micronaut.http.annotation.Get
import io.micronaut.http.annotation.Post
import io.micronaut.http.annotation.Body
import io.micronaut.http.annotation.QueryValue
import io.micronaut.http.MediaType
import javax.annotation.Nullable
import javax.validation.constraints.NotBlank

@Controller("/api/greetings") 
class HelloController {
    Map greetings(String name) {
        [message: "hello, " + name]
    }

    @Get
    Map getGreetings(@NotBlank @QueryValue(defaultValue = "world") String name) {
        greetings(name)
    }

    @Post
    HttpResponse<Map> postGreetings(@Nullable String name) {
        if ("".equals(name)) {
            return HttpResponse.badRequest([ error: "Name should not be empty" ])
        }

       HttpResponse.ok(greetings(name ?: "world"))
    }
}
