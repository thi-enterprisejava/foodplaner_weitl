package de.thi.foodplaner.domain.recipe;

import java.io.Serializable;

/**
 * Created by Philipp on 09.11.15.
 */
public class Food implements Serializable{

    /******* Variables *******/
    private String name;
    private double amount;
    private Unit unit;

    /******** Constructor ********/
    public Food(String name, double amount, Unit unit){
        this.name = name;
        this.amount = amount;
        this.unit = unit;
    }

    /***** Setter Getter *****/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Unit getUnit() {
        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
