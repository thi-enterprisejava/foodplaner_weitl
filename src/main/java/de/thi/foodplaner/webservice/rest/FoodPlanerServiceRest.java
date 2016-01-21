package de.thi.foodplaner.webservice.rest;

import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.ejb.EJB;
import javax.ws.rs.*;
import java.util.List;

/**
 * Created by Philipp on 12.01.16.
 */
@Path("/FoodPlaner")

public class FoodPlanerServiceRest {
    @EJB
    FoodPlanerServiceDatabase foodPlanerService;

    @GET
    @Produces("application/json")
    public List<Recipe> findAll() {
        return foodPlanerService.findAll();
    }

    @GET
    @Path("/{id}")
    @Produces("application/json")
    public Recipe findById(@PathParam("id") Long id) {
        return foodPlanerService.findById(id);
    }

    @POST
    @Consumes("application/json")
    public Recipe add(Recipe recipe) {
        return foodPlanerService.add(recipe);
    }

    @POST
        @Consumes("application/json")
        public Recipe edit(Recipe recipe) {
        return foodPlanerService.edit(recipe);
    }

    @POST
    @Consumes("application/json")
    public void remove(Recipe recipe) {
        foodPlanerService.remove(recipe);
    }

}
