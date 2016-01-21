package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.planing.FoodList;
import de.thi.foodplaner.domain.recipe.Recipe;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Philipp on 09.01.16.
 */
@Stateless
public class FoodPlanerServiceDatabase extends FoodPlanerService{
    /******* Variables *******/
    private static final Logger LOGGER = LogManager.getLogger(FoodPlanerServiceDatabase.class);

    @PersistenceContext(unitName = "primary")
    private EntityManager em;

    /******** Methods ********/
    /** Recipe **/

    @RolesAllowed("USER")
    @Override
    public Recipe add(Recipe recipe) {
        LOGGER.info("Adding recipe to database");

        em.persist(recipe);

        return recipe;
    }

    @RolesAllowed("USER")
    @Override
    public void remove(Recipe recipe){
        LOGGER.info("Remove recipe from database");

        em.remove(em.contains(recipe) ? recipe : em.merge(recipe));
    }

    @RolesAllowed("USER")
    @Override
    public Recipe edit(Recipe recipe){
        LOGGER.info("Updated recipe");

        em.merge(em.contains(recipe) ? recipe : em.merge(recipe));
        return recipe;
    }

    @PermitAll
    @Override
    public List<Recipe> findByName(String name) {
        LOGGER.info("Looking for Recipes in database with name: " + name);

        List<Recipe> returnList = new LinkedList<Recipe>();

        for(Recipe recipe: findAll()){
            if(recipe.getName().contains(name)){
                returnList.add(recipe);
            }
        }
        return returnList;
    }

    @PermitAll
    @Override
    public List<Recipe> findAll(){
        LOGGER.info("Looking for all recipes in database");

        TypedQuery<Recipe> query = em.createQuery("SELECT c FROM Recipe as c", Recipe.class);
        return query.getResultList();
    }

    @PermitAll
    @Override
    public Recipe findById(Long id){
        LOGGER.info("Locking for recipe with id: " + id);

        return em.find(Recipe.class, id);
    }

    /** FoodList **/
    @RolesAllowed("USER")
    @Override
    public FoodList addFoodList(FoodList list){
        LOGGER.info("Adding FoodList to database");

        em.persist(list);

        return list;
    }

    @RolesAllowed("USER")
    @Override
    public FoodList findFoodListById(Long id){
        LOGGER.info("Looking for FoodList with id: " + id);

        return em.find(FoodList.class, id);
    }

    @RolesAllowed("USER")
    @Override
    public FoodList findLastFoodList(){
        LOGGER.info("Looking for last FoodList");

        TypedQuery<FoodList> query = em.createQuery("SELECT c FROM FoodList as c", FoodList.class);
        List<FoodList> list = query.getResultList();
        if(list.isEmpty()){
            return new FoodList();
        }
        return list.get(list.size() - 1);
    }

}
