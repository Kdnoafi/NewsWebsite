package raf.rs.WEB_Projekat.resources;

import raf.rs.WEB_Projekat.entities.Komentar;
import raf.rs.WEB_Projekat.services.CommentService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/comments")
public class CommentResource {

    @Inject
    CommentService commentService;

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response all(@PathParam("id") Integer id) {
        return Response.ok(this.commentService.allComments(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Komentar create(@Valid Komentar comment) {
        return this.commentService.addComment(comment);
    }
}
