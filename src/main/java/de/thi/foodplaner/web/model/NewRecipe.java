package de.thi.foodplaner.web.model;

import de.thi.foodplaner.service.FoodPlanerService;
import de.thi.foodplaner.domain.Food;
import de.thi.foodplaner.domain.Recipe;
import de.thi.foodplaner.domain.Unit;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 25.11.15.
 */
@Named
@SessionScoped
public class NewRecipe implements Serializable {

    /******* Variables *******/
    private final static Logger LOGGER = Logger.getLogger(NewRecipe.class.getName());

    private Recipe recipe;

    private String foodName;
    private double foodAmount;
    private Unit foodUnit;

    private final FoodPlanerService foodPlanerService;

    /******* Constructor *******/
    @Inject
    public NewRecipe(FoodPlanerService foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
        recipe = new Recipe();
    }

    /******* Methods *******/
    public String doAddFood() {
        LOGGER.log(Level.INFO,"Adding new Food: " + foodName + " " + foodAmount);
        foodUnit = Unit.kg;

        recipe.addFood(new Food(foodName, foodAmount, foodUnit));
        foodName = "";
        foodAmount = 0;
        foodUnit = Unit.kg;


        return "";
    }

    public String doSaveRecipe() {
        LOGGER.log(Level.INFO,"Saving-process started:" + recipe.getName());

        this.foodPlanerService.add(recipe);

        return "";
    }

    /***** Setter Getter *****/
    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodAmount() {
        return foodAmount;
    }

    public void setFoodAmount(double foodAmount) {
        this.foodAmount = foodAmount;
    }

    public Unit getFoodUnit() {
        return foodUnit;
    }

    public void setFoodUnit(Unit foodUnit) {
        this.foodUnit = foodUnit;
    }
}
