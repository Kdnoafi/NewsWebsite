package raf.rs.WEB_Projekat.resources;

import raf.rs.WEB_Projekat.entities.Korisnik;
import raf.rs.WEB_Projekat.requests.LoginRequest;
import raf.rs.WEB_Projekat.services.UserService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.HashMap;
import java.util.Map;

@Path("/users")
public class UserResource {

    @Inject
    private UserService userService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.userService.allUsers()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik create(@Valid Korisnik user) {
        return this.userService.addUser(user);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik find(@PathParam("id") Integer id) {
        return this.userService.findUser(id);
    }

    @GET
    @Path("/email/{email}")
    @Produces(MediaType.APPLICATION_JSON)
    public Korisnik findByEmail(@PathParam("email") String email) {
        return this.userService.findByEmail(email);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.userService.deleteUser(id);
    }

    @POST
    @Path("/login")
    @Produces({MediaType.APPLICATION_JSON})
    public Response login(@Valid LoginRequest loginRequest) {
        Map<String, String> response = new HashMap<>();

        String jwt = this.userService.login(loginRequest.getUsername(), loginRequest.getPassword());
        if (jwt == null) {
            response.put("message", "These credentials do not match our records");
            return Response.status(422, "Unprocessable Entity").entity(response).build();
        }

        response.put("jwt", jwt);

        return Response.ok(response).build();
    }

    @POST
    @Path("/authorization")
    @Produces({MediaType.APPLICATION_JSON})
    public Response check(String jwt) {
        if(userService.isAuthorized(jwt)) {
            return Response.ok().build();
        }

        return Response.status(403, "Forbidden").build();
    }
}
