package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.Recipe;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Philipp on 29.12.15.
 */
public abstract class  FoodPlanerService implements Serializable{

    /******* Variables *******/

    /******* Constructor *******/

    /******** Methods ********/
    public abstract Recipe add(Recipe recipe);
    public abstract List<Recipe> findByName(String name);

}
