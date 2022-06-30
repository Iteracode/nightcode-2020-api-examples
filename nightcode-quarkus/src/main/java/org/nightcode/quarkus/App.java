package org.nightcode.quarkus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

@Path("/")
public class App {
    @Path("/")
    @GET
    public Response home()
    {
        return Response.ok("Salut la Nightcode !").build();
    }
}
