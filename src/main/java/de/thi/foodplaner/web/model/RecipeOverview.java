package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Philipp on 08.01.16.
 */
@Named
@ViewScoped
public class RecipeOverview implements Serializable{
    /******* Variables *******/
    private final FoodPlanerServiceDatabase foodPlanerService;
    private String searchText;
    private List<Recipe> searchResultList;

    /******* Constructor *******/
    @Inject
    public RecipeOverview(FoodPlanerServiceDatabase foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
        this.searchResultList = this.foodPlanerService.findAll();
    }

    /******* Methods *******/
    public String doSearch(){
        List<Recipe> foundList = this.foodPlanerService.findByName(searchText);
        if(foundList != null) {
            this.searchResultList = foundList;
        }

        return "";
    }

    /***** Setter Getter *****/
    public String getSearchText() {
        return searchText;
    }

    public void setSearchText(String searchText) {
        this.searchText = searchText;
    }

    public List<Recipe> getSearchResultList() {
        return searchResultList;
    }

    public void setSearchResultList(List<Recipe> searchResultList) {
        this.searchResultList = searchResultList;
    }
}