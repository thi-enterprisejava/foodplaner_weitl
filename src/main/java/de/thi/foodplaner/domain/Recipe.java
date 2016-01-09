package de.thi.foodplaner.domain;


import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 09.11.15.
 */
public class Recipe implements Serializable{

    /******* Variables *******/
    private String name;
    private List<Food> foodList;
    private String description;

    /******** Constructor ********/
    public Recipe(){
        foodList = new LinkedList<Food>();
    }

    /******** Methods ********/
    public void addFood(Food food){
        this.foodList.add(food);
    }

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
