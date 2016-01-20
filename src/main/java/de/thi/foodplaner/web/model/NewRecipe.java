package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.Food;
import de.thi.foodplaner.domain.Recipe;
import de.thi.foodplaner.domain.Unit;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import org.apache.commons.io.IOUtils;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.Transient;
import javax.servlet.http.Part;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Philipp on 25.11.15.
 */
@Named
@ViewScoped
public class NewRecipe implements Serializable {

    /******* Variables *******/
    private static final Logger LOGGER = LogManager.getLogger(NewRecipe.class);

    private FoodPlanerServiceDatabase foodPlanerService;

    private Recipe recipe;
    private Long id;

    @Transient
    private Part part;

    private String foodName;
    private double foodAmount;
    private Unit foodUnit;

    /******* Constructor *******/
    @Inject
    public NewRecipe(FoodPlanerServiceDatabase foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
    }

    @PostConstruct
    public void postConstruct() {
        this.recipe = new Recipe();
    }

    /******* Methods *******/
    public String doAddFood() {

        recipe.addFood(new Food(foodName, foodAmount, foodUnit));
        foodName = "";
        foodAmount = 0;
        foodUnit = Unit.KG;


        return "";
    }

    public String doSaveRecipe() {

        try {
            if(part != null) {
                recipe.setImage(IOUtils.toByteArray(part.getInputStream()));
            }
        }catch (IOException e){
            LOGGER.error(e.getMessage());
        }


        if(recipe.getId() == null){
            this.foodPlanerService.add(recipe);
        }else{
            this.foodPlanerService.edit(recipe);
        }

        return "recipedetail.xhtml?faces-redirect=true&id=" + recipe.getId();
    }

    public String doRemoveRecipe(){

        this.foodPlanerService.remove(recipe);

        return "recipeOverview.xhtml";
    }

    public String doEditRecipe(){
        return "newrecipe.xhtml?faces-redirect=true&id="+ recipe.getId();
    }

    public void init() {
        Recipe loadedRecipe = null;
        if(id != null) {
            loadedRecipe = foodPlanerService.findById(id);
        }
        if(loadedRecipe != null) {
            this.recipe = loadedRecipe;
        } else {
            // TODO LOGGING
        }
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Part getPart() {
        return part;
    }

    public void setPart(Part part) {
        this.part = part;
    }
}
