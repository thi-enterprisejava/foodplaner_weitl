package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.FoodList;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;

/**
 * Created by Philipp on 15.01.16.
 */
@Named
@ViewScoped
public class ListOverview implements Serializable{

    private Long id;

    private FoodList foodList;

    private final FoodPlanerServiceDatabase foodPlanerService;

    @Inject
    public ListOverview(FoodPlanerServiceDatabase foodPlanerService) {
        this.foodPlanerService = foodPlanerService;
    }

    public void init() {
        FoodList loadedFoodList = null;
        if(id != null) {
            loadedFoodList = foodPlanerService.findFoodListById(id);
        }
        if(loadedFoodList != null) {
            this.foodList = loadedFoodList;
        } else {
            // TODO LOGGING
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodList getFoodList() {
        return foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }
}
