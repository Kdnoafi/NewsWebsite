package raf.rs.WEB_Projekat.resources;

import raf.rs.WEB_Projekat.entities.Kategorija;
import raf.rs.WEB_Projekat.services.CategoryService;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/categories")
public class CategoryResource {

    @Inject
    private CategoryService categoryService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response all() {
        return Response.ok(this.categoryService.allCategories()).build();
    }

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija create(@Valid Kategorija category) {
        return this.categoryService.addCategory(category);
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Kategorija find(@PathParam("id") Integer id) {
        return this.categoryService.findCategory(id);
    }

    @DELETE
    @Path("/{id}")
    public void delete(@PathParam("id") Integer id) {
        this.categoryService.deleteCategory(id);
    }
}
