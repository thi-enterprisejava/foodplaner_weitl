package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.Food;
import de.thi.foodplaner.domain.FoodList;
import de.thi.foodplaner.domain.Unit;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;

import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 15.01.16.
 */
@Named
@ViewScoped
public class ListOverview implements Serializable{

    /******* Variables *******/
    private Long id;
    private FoodList foodList;

    @Inject
    private FoodPlanerServiceDatabase foodPlanerService;

    /******** Methods ********/
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

    /**
     * Filtering Food and sum up food with same name
     *
     * @param foodList list of FoodItems to filter
     * @return filtered list of FoodItems
     */
    private List<Food> filterFoodListWithName(List<Food> foodList){
        List<Food> returnList = new LinkedList<>();

        while(!foodList.isEmpty()){
            Food currentFood = foodList.remove(0);

            returnList.add(containsFoodWithName(foodList, currentFood));
        }

        return returnList;
    }

    private Food containsFoodWithName(List<Food> foodList, Food food){
        for(int i=0; i < foodList.size();i++){
            if(foodList.get(i).getName().equals(food.getName()) && compatibleUnits(foodList.get(i).getUnit(),food.getUnit())){
                food.setAmount(food.getAmount() + calculateAmountInUnit(foodList.get(i), food.getUnit()));
                foodList.remove(i);
                containsFoodWithName(foodList, food);
                return food;
            }
        }
        return food;
    }

    private boolean compatibleUnits(Unit a, Unit b){
        if(a.equals(b)){
            return true;
        }
        if((Unit.KG.equals(a) && Unit.G.equals(b)) || (Unit.KG.equals(b) && Unit.G.equals(a))){
            return true;
        }
        if((Unit.L.equals(a) && Unit.ML.equals(b)) || (Unit.L.equals(b) && Unit.ML.equals(a))){
            return true;
        }

        return false;
    }

    private double calculateAmountInUnit(Food food, Unit newUnit){
        if(newUnit.equals(food.getUnit())){
            return food.getAmount();
        }

        if(newUnit.equals(Unit.G) && food.getUnit().equals(Unit.KG)){
            return food.getAmount() * 1000;
        }

        if(newUnit.equals(Unit.KG) && food.getUnit().equals(Unit.G)){
            return food.getAmount() / 1000;
        }

        if(newUnit.equals(Unit.ML) && food.getUnit().equals(Unit.L)){
            return food.getAmount() * 1000;
        }

        if(newUnit.equals(Unit.L) && food.getUnit().equals(Unit.ML)){
            return food.getAmount() / 1000;
        }

        return -1;

    }

    private List<Food> checkUnit(List<Food> foodList){
        List<Food> returnList = new LinkedList<>();

        for(Food food: foodList){
            if(food.getUnit().equals(Unit.G) && food.getAmount() > 1000){
                food.setAmount(food.getAmount()/1000);
                food.setUnit(Unit.KG);
            }else
            if(food.getUnit().equals(Unit.ML) && food.getAmount() > 1000){
                food.setAmount(food.getAmount()/1000);
                food.setUnit(Unit.L);
            }else
            if(food.getUnit().equals(Unit.KG) && food.getAmount() < 1){
                food.setAmount(food.getAmount()*1000);
                food.setUnit(Unit.G);
            }else
            if(food.getUnit().equals(Unit.L) && food.getAmount() < 1){
                food.setAmount(food.getAmount()*1000);
                food.setUnit(Unit.ML);
            }
            returnList.add(food);
        }
        return returnList;
    }

    /***** Setter Getter *****/
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public FoodList getFoodList() {
        foodList.setFoodShoppingList(filterFoodListWithName(foodList.getFoodShoppingList()));
        foodList.setFoodShoppingList(checkUnit(foodList.getFoodShoppingList()));
        return foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }
}
