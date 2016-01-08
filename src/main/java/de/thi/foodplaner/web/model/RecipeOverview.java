package de.thi.foodplaner.web.model;

import de.thi.foodplaner.service.FoodPlanerService;
import de.thi.foodplaner.domain.Recipe;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Philipp on 08.01.16.
 */
@Named
@SessionScoped
public class RecipeOverview implements Serializable{
    /******* Variables *******/
    private final FoodPlanerService foodPlanerService;
    private String searchText;
    private List<Recipe> searchResultList;

    /******* Constructor *******/
    @Inject
    public RecipeOverview(FoodPlanerService foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
    }

    /******* Methods *******/
    public String doSearch(){
        this.searchResultList = this.foodPlanerService.findByName(searchText);

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
