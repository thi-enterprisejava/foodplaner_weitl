package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.Recipe;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Philipp on 09.01.16.
 */
@Stateless
public class FoodPlanerServiceDatabase{
    /******* Variables *******/
    private final static Logger LOGGER = Logger.getLogger(FoodPlanerServiceDatabase.class.getName());

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /******* Constructor *******/
    public FoodPlanerServiceDatabase(){
        LOGGER.log(Level.INFO,"FoodPlanerServiceDatabase created");
    }

    /******** Methods ********/
    public Recipe add(Recipe recipe) {
        LOGGER.log(Level.INFO,"Adding recipe to database");

        /*if(findByName(recipe.getName()).size() > 0) {
            LOGGER.log(Level.INFO,"Recipe with name " + " was found. Can not be created again");
            throw new RuntimeException(recipe.getName());
        }*/

        em.persist(recipe);

        return recipe;
    }

    public List<Recipe> findByName(String name) {
        LOGGER.log(Level.INFO,"Looking for Recipe in database with name: " + name);
        List<Recipe> returnList = new LinkedList<Recipe>();

        for(Recipe recipe: findAll()){
            if(recipe.getName().equals(name)){
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    public List<Recipe> findAll(){
        TypedQuery<Recipe> query = em.createQuery("SELECT c FROM Recipe as c", Recipe.class);
        return query.getResultList();
    }

}
