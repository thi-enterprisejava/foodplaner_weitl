package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.Recipe;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 04.01.16.
 */
public class FoodPlanerServiceLocal {
    /******* Variables *******/
    private final static Logger LOGGER = Logger.getLogger(FoodPlanerServiceLocal.class.getName());

    private List<Recipe> recipeList;

    /******* Constructor *******/
    public FoodPlanerServiceLocal() {
        super();
        LOGGER.log(Level.INFO,"Local Service created");
        this.recipeList = new LinkedList<Recipe>();
    }

    /******** Methods ********/
    public Recipe add(Recipe recipe) {
        this.recipeList.add(recipe);
        return recipe;
    }

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
}
