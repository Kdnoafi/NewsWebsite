package raf.rs.WEB_Projekat.resources;

import raf.rs.WEB_Projekat.entities.Vest;
import raf.rs.WEB_Projekat.services.ArticleService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/articles")
public class ArticleResource {

    @Inject
    private ArticleService articleService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.articleService.allArticles()).build();
    }

    @GET
    @Path("/category/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response allByCategory(@PathParam("id") Integer id) {
        return Response.ok(this.articleService.articlesByCategory(id)).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Vest create(@Valid Vest vest) {
        return this.articleService.addArticle(vest);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Vest find(@PathParam("id") Integer id) {
        return this.articleService.findArticle(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.articleService.deleteArticle(id);
    }
}
