package de.thi.foodplaner.domain.planing;

import de.thi.foodplaner.domain.recipe.Food;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 15.01.16.
 */
@Entity
public class FoodList implements Serializable{

    /******* Variables *******/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Food> foodShoppingList;

    /******** Constructor ********/
    public FoodList() {
        this.foodShoppingList = new LinkedList<>();
    }

    /******** Methods ********/
    public void add(Food food){
        foodShoppingList.add(food);
    }

    public void addAll(Collection<Food> food){
        foodShoppingList.addAll(food);
    }

    /***** Setter Getter *****/
    public List<Food> getFoodShoppingList() {
        return foodShoppingList;
    }

    public void setFoodShoppingList(List<Food> foodList) {
        this.foodShoppingList = foodList;
    }

    public Long getId() {
        return id;
    }
}
