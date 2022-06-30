package org.nightcode.quarkus;

import org.nightcode.quarkus.dto.Message;
import org.nightcode.quarkus.dto.Name;
import org.nightcode.quarkus.service.MessageService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/api/greetings")
@Produces(MediaType.APPLICATION_JSON)
public class Api {

    private final MessageService messageService;

    @Inject
    public Api(MessageService messageService) {
        this.messageService = messageService;
    }

    @Path("/")
    @GET
    public Message greetings(@NotEmpty @DefaultValue("world") @QueryParam(value = "name") String name)
    {
        return messageService.getMessage(name);
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Message greetings(@Valid Name name)
    {
        return messageService.getMessage(name.getName());
    }
}
