package de.thi.foodplaner.web.domain;

import de.thi.foodplaner.web.domain.Food;

import java.util.List;

/**
 * Created by Philipp on 09.11.15.
 */
public class Recipe {
    /******* Variables *******/
    private String name;
    private List<Food> foodList;
    private String description;

    /******** Methods ********/



    /***** Setter Getter *****/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoodlist() {
        return foodList;
    }

    public void setFoodlist(List<Food> foodlist) {
        this.foodList = foodlist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
