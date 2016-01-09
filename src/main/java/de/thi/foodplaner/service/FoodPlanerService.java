package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.Recipe;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 29.12.15.
 */
public abstract class  FoodPlanerService implements Serializable{

    /******* Variables *******/
    private final static Logger LOGGER = Logger.getLogger(FoodPlanerService.class.getName());

    /******* Constructor *******/
    public FoodPlanerService(){
        LOGGER.log(Level.INFO,"FoodPlanerService created");
    }

    /******** Methods ********/
    public abstract Recipe add(Recipe recipe);
    public abstract List<Recipe> findByName(String name);

}
