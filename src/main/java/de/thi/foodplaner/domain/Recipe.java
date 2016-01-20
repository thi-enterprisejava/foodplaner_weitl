package de.thi.foodplaner.domain;


import javax.enterprise.context.ApplicationScoped;
import javax.persistence.*;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 09.11.15.
 */
@Entity
@ApplicationScoped
public class Recipe implements Serializable{

    /******* Variables *******/
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @ElementCollection(fetch = FetchType.EAGER)
    private List<Food> foodList;

    private String description;
    private String shortDescription;

    @Column(length = 100000)
    @Lob
    private byte[] image;

    /******** Constructor ********/
    public Recipe(){
        foodList = new LinkedList<Food>();
    }

    public Recipe(String name) {
        this.name = name;
        foodList = new LinkedList<Food>();
    }

    /******** Methods ********/
    public void addFood(Food food){
        this.foodList.add(food);
    }

    /***** Setter Getter *****/
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Food> getFoodlist() {
        return foodList;
    }

    public void setFoodlist(List<Food> foodlist) {
        this.foodList = foodlist;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getShortDescription() {
        return shortDescription;
    }

    public void setShortDescription(String shortDescription) {
        this.shortDescription = shortDescription;
    }

    public Long getId() {
        return id;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }
}
