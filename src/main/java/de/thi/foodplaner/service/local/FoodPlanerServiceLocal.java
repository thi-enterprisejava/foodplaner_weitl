package de.thi.foodplaner.service.local;

import de.thi.foodplaner.domain.planing.FoodList;
import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.service.FoodPlanerService;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 04.01.16.
 *
 *
 * For first tests only
 *
 */
public class FoodPlanerServiceLocal extends FoodPlanerService {
    /******* Variables *******/
    private static final Logger LOGGER = Logger.getLogger(FoodPlanerServiceLocal.class.getName());

    private List<Recipe> recipeList;

    /******* Constructor *******/
    public FoodPlanerServiceLocal() {
        super();
        LOGGER.log(Level.INFO,"Local Service created");
        this.recipeList = new LinkedList<Recipe>();
    }

    /******** Methods ********/
    @Override
    public Recipe add(Recipe recipe) {
        this.recipeList.add(recipe);
        return recipe;
    }

    @Override
    public void remove(Recipe recipe) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Recipe edit(Recipe recipe) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<Recipe> findByName(String name) {
        LOGGER.log(Level.INFO,"Looking for Recipe with name: " + name);
        List<Recipe> returnList = new LinkedList<Recipe>();
        for(Recipe recipe: recipeList){
            if(recipe.getName().equals(name)){
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    @Override
    public List<Recipe> findAll() {
        throw new UnsupportedOperationException();
    }

    @Override
    public Recipe findById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FoodList addFoodList(FoodList list) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FoodList findFoodListById(Long id) {
        throw new UnsupportedOperationException();
    }

    @Override
    public FoodList findLastFoodList() {
        throw new UnsupportedOperationException();
    }
}
