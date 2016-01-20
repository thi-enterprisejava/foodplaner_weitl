package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.FoodList;
import de.thi.foodplaner.domain.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Philipp on 14.01.16.
 */
    @Named
    @ViewScoped
    public class Planing implements Serializable {

    /******* Variables *******/
    private Long first;
    private Long second;
    private Long third;
    private List<Recipe> allRecipes;



    private final FoodPlanerServiceDatabase foodPlanerService;

    /******* Constructor *******/
    @Inject
    public Planing(FoodPlanerServiceDatabase foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
        this.allRecipes = this.foodPlanerService.findAll();
    }

    public String doGo() {
        FoodList foodList = new FoodList();

        if(first == 0 || second == 0 || third == 0){
            return "";
        }

        foodList.addAll(this.foodPlanerService.findById(first).getFoodlist());
        foodList.addAll(this.foodPlanerService.findById(second).getFoodlist());
        foodList.addAll(this.foodPlanerService.findById(third).getFoodlist());

        foodPlanerService.addFoodList(foodList);

        return "list.xhtml?faces-redirect=true&id=" + foodList.getId();
    }

    /***** Setter Getter *****/
    public Long getFirst() {
        return first;
    }

    public void setFirst(Long first) {
        this.first = first;
    }

    public Long getSecond() {
        return second;
    }

    public void setSecond(Long second) {
        this.second = second;
    }

    public Long getThird() {
        return third;
    }

    public void setThird(Long third) {
        this.third = third;
    }

    public List<Recipe> getAllRecipes() {
        return allRecipes;
    }

    public void setAllRecipes(List<Recipe> allRecipes) {
        this.allRecipes = allRecipes;
    }
}
