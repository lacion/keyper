package co.lgmk.keyper.resources;

import com.codahale.dropwizard.auth.Auth;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import co.lgmk.keyper.core.Saying;
import co.lgmk.keyper.core.User;


@Path("/hello")
@Produces(MediaType.APPLICATION_JSON)
public class HelloWorldResource
{

    public HelloWorldResource()
    {
    }

    @GET
    public Saying sayHello(@Auth User user)
    {
        return new Saying(1,"hello test");
    }

}