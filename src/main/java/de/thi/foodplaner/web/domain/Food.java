package de.thi.foodplaner.web.domain;

/**
 * Created by Philipp on 09.11.15.
 */
public class Food {

    /******* Variables *******/
    private String name;
    private int amount;
    private Unit unit;

    /******** Methods ********/



    /***** Setter Getter *****/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Unit getUnit() {

        return unit;
    }

    public void setUnit(Unit unit) {
        this.unit = unit;
    }
}
