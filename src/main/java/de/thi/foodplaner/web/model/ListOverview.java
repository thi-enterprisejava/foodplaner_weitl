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

    private List<Food> filterFoodlist(List<Food> foodList){
        List<Food> returnList = new LinkedList<>();
        for(int i=0; i < foodList.size();i++){
            double newAmount = foodList.get(i).getAmount();
            for(int j=i+1;j < foodList.size(); j++){
                if(foodList.get(i).getName().equals(foodList.get(j).getName())){
                    if(foodList.get(i).getUnit().equals(foodList.get(j).getUnit())){
                        newAmount += foodList.get(j).getAmount();
                        foodList.remove(j);
                        j--;
                    }else{
                        double calculatedAmount = calculateAmountInUnit(foodList.get(j),foodList.get(i).getUnit());
                        if(calculatedAmount != -1){
                            newAmount += calculatedAmount;
                            foodList.remove(j);
                            j--;
                        }
                    }
                }
            }
            returnList.add(new Food(foodList.get(i).getName(),newAmount ,foodList.get(i).getUnit()));
        }
        return returnList;
    }

    private double calculateAmountInUnit(Food food, Unit newUnit){
        if(newUnit.equals(Unit.g) && food.getUnit().equals(Unit.kg)){
            return food.getAmount() * 1000;
        } else
        if(newUnit.equals(Unit.kg) && food.getUnit().equals(Unit.g)){
            return food.getAmount() / 1000;
        } else
        if(newUnit.equals(Unit.ml) && food.getUnit().equals(Unit.l)){
            return food.getAmount() * 1000;
        } else
        if(newUnit.equals(Unit.l) && food.getUnit().equals(Unit.ml)){
            return food.getAmount() / 1000;
        } else{
            return -1;
        }
    }

    private List<Food> checkUnit(List<Food> foodList){
        List<Food> returnList = new LinkedList<>();

        for(Food food: foodList){
            if(food.getUnit().equals(Unit.g) && food.getAmount() > 1000){
                food.setAmount(food.getAmount()/1000);
                food.setUnit(Unit.kg);
            }else
            if(food.getUnit().equals(Unit.ml) && food.getAmount() > 1000){
                food.setAmount(food.getAmount()/1000);
                food.setUnit(Unit.l);
            }else
            if(food.getUnit().equals(Unit.kg) && food.getAmount() < 1){
                food.setAmount(food.getAmount()*1000);
                food.setUnit(Unit.g);
            }else
            if(food.getUnit().equals(Unit.l) && food.getAmount() < 1){
                food.setAmount(food.getAmount()*1000);
                food.setUnit(Unit.ml);
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
        foodList.setFoodList(filterFoodlist(foodList.getFoodList()));
        foodList.setFoodList(checkUnit(foodList.getFoodList()));
        return foodList;
    }

    public void setFoodList(FoodList foodList) {
        this.foodList = foodList;
    }
}
