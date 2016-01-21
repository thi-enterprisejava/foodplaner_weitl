package de.thi.foodplaner.webservice.soap;

import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.ejb.EJB;
import javax.jws.WebParam;
import javax.jws.WebService;
import java.util.List;

/**
 * Created by Philipp on 21.01.16.
 */

@WebService
public class FoodPlanerServiceSoap {

    @EJB
    private FoodPlanerServiceDatabase foodPlanerService;

    public List<Recipe> findAll() {
        return foodPlanerService.findAll();
    }

    public Recipe findById(@WebParam(name = "id") Long id) {
        return foodPlanerService.findById(id);
    }

    public List<Recipe> findByName(@WebParam(name = "name") String name) {
        return foodPlanerService.findByName(name);
    }
}
