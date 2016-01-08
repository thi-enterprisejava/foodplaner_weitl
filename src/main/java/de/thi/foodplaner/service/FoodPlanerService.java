package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.Recipe;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 29.12.15.
 */
public class FoodPlanerService implements Serializable{

    /******* Variables *******/
    private final static Logger LOGGER = Logger.getLogger(FoodPlanerService.class.getName());

    //@PersistenceContext(unitName = "primary")
    //private EntityManager em;

    /******* Constructor *******/
    public FoodPlanerService(){
        LOGGER.log(Level.INFO,"FoodPlanerService created");
    }

    /******** Methods ********/
    public Recipe add(Recipe recipe) {
        LOGGER.log(Level.INFO,"Adding recipe on FoodPlanerService");

        /*if(findByName(recipe.getName()).size() > 0) {
            //TODO new not exist exception
            LOGGER.log(Level.INFO,"Recipe with name " + " was found. Can not be created again");
            throw new RuntimeException(recipe.getName());
        }
        //TODO Persistierung
        //em.persist(recipe);
        */
        return recipe;

    }

    public List<Recipe> findByName(String name) {
        return null;
    }

}
