package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.FoodList;
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
    public abstract void remove(Recipe recipe);
    public abstract Recipe edit(Recipe recipe);

    public abstract List<Recipe> findByName(String name);
    public abstract List<Recipe> findAll();
    public abstract Recipe findById(Long id);

    public abstract FoodList addFoodList(FoodList list);
    public abstract FoodList findFoodListById(Long id);

}
