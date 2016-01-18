package de.thi.foodplaner.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 15.01.16.
 */
@Entity
public class FoodList implements Serializable {

    /******* Variables *******/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Food> foodList;

    public FoodList() {
        this.foodList = new LinkedList<Food>();
    }

    public void add(Food food){
        foodList.add(food);
    }

    public void addAll(Collection<Food> food){
        foodList.addAll(food);
    }

    public List<Food> getFoodList() {
        return foodList;
    }

    public void setFoodList(List<Food> foodList) {
        this.foodList = foodList;
    }

    public Long getId() {
        return id;
    }
}
