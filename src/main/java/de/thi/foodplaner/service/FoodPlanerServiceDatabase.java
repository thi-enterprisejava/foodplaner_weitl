package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.FoodList;
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
    private static final  Logger LOGGER = Logger.getLogger(FoodPlanerServiceDatabase.class.getName());

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /******* Constructor *******/
    public FoodPlanerServiceDatabase(){
        LOGGER.log(Level.INFO,"FoodPlanerServiceDatabase created");
    }

    /******** Methods ********/
    /** Recipe **/
    public Recipe add(Recipe recipe) {
        LOGGER.log(Level.INFO,"Adding recipe to database");

        em.persist(recipe);

        return recipe;
    }

    public void remove(Recipe recipe){
        em.remove(em.contains(recipe) ? recipe : em.merge(recipe));
    }

    public Recipe edit(Recipe recipe){
        em.merge(em.contains(recipe) ? recipe : em.merge(recipe));
        return recipe;
    }

    public List<Recipe> findByName(String name) {
        LOGGER.log(Level.INFO,"Looking for Recipe in database with name: " + name);
        List<Recipe> returnList = new LinkedList<Recipe>();

        for(Recipe recipe: findAll()){
            if(recipe.getName().contains(name)){
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    public List<Recipe> findAll(){
        TypedQuery<Recipe> query = em.createQuery("SELECT c FROM Recipe as c", Recipe.class);
        return query.getResultList();
    }

    public Recipe findById(Long id){
        return em.find(Recipe.class, id);
    }

    /** FoodList **/
    public FoodList addFoodList(FoodList list){
        em.persist(list);

        return list;
    }

    public FoodList findFoodListById(Long id){
        return em.find(FoodList.class, id);
    }

}
