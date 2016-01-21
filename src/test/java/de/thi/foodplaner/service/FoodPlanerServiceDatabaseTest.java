package de.thi.foodplaner.service;

import de.thi.foodplaner.domain.recipe.Food;
import de.thi.foodplaner.domain.planing.FoodList;
import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.domain.recipe.Unit;
import de.thi.foodplaner.security.AuthenticatedUser;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.formatter.Formatters;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Philipp on 05.01.16.
 */
@RunWith(Arquillian.class)
public class FoodPlanerServiceDatabaseTest {

    @EJB
    FoodPlanerServiceDatabase foodPlanerService;

    @EJB
    AuthenticatedUser authenticatedUser;

    @Deployment(testable = true)
    public static WebArchive createDeployment() {
        WebArchive webArchive = ShrinkWrap.create(WebArchive.class, "test.war")
                .addClass(FoodPlanerServiceDatabase.class)
                .addClass(FoodPlanerService.class)
                .addClass(Recipe.class)
                .addClass(Food.class)
                .addClass(FoodList.class)
                .addClass(Unit.class)
                .addClass(AuthenticatedUser.class)
                .addAsResource("test-persistence.xml", "META-INF/persistence.xml")
                .addAsWebInfResource("cocktailtest-ds.xml")
                ;
        System.out.println(webArchive.toString(Formatters.VERBOSE));
        return webArchive;
    }

    @Before
    public void setUp() throws Exception {
        //clean database
        authenticatedUser.call(() -> {

            List<Recipe> recipeList = foodPlanerService.findAll();
            for(Recipe recipe: recipeList){
                foodPlanerService.remove(recipe);
            }
            recipeList = foodPlanerService.findAll();
            assertEquals("Items left before new Test", 0, recipeList.size());


            return null;
        });

    }

    @Test(expected = EJBAccessException.class)
    public void thatRecipeCanNotBeAddedAsNotAuthenticatedUser() throws Exception {
        Recipe recipe = new Recipe("Test");

        foodPlanerService.add(recipe);
    }

    @Test(expected = EJBAccessException.class)
    public void thatRecipeCanNotBeEditedAsNotAuthenticatedUser() throws Exception {
        Recipe recipe = new Recipe("Test");

        foodPlanerService.edit(recipe);
    }

    @Test(expected = EJBAccessException.class)
    public void thatRecipeCanNotBeRemovedAsNotAuthenticatedUser() throws Exception {
        Recipe recipe = new Recipe("Test");

        foodPlanerService.remove(recipe);
    }

    @Test(expected = EJBAccessException.class)
    public void thatFoodListCanNotBeAddedAsNotAuthenticatedUser() throws Exception {
        FoodList foodList = new FoodList();
        foodPlanerService.addFoodList(foodList);
    }



    @Test(expected = EJBAccessException.class)
    public void thatFindFoodListByIdCanNotBeExecutedAsNotAuthenticatedUser() throws Exception {
        foodPlanerService.findFoodListById(1L);
    }

    @Test
    public void thatFindAllCanBeExecutedAsNotAuthenticatedUser() throws Exception {
        foodPlanerService.findAll();
    }

    @Test
    public void thatFindByIdCanBeExecutedAsNotAuthenticatedUser() throws Exception {
        foodPlanerService.findById(1L);
    }

    @Test
    public void thatFindByNameCanBeExecutedAsNotAuthenticatedUser() throws Exception {
        foodPlanerService.findByName("name");
    }

    @Test
    public void thatRecipeCanBeAddedAsAuthenticatedUser() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe = new Recipe("Test");

            foodPlanerService.add(recipe);

            List<Recipe> recipeList = foodPlanerService.findByName(recipe.getName());
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertNotNull("Did not create and recipe with id", recipeList.get(0).getId());

            return null;
        });
    }

    @Test
    public void thatRecipeCanBeEditedAsAuthenticatedUser() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe = new Recipe("TestRecipe1");

            foodPlanerService.add(recipe);

            List<Recipe> recipeList = foodPlanerService.findByName(recipe.getName());
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertEquals("Name is not like created", "TestRecipe1", recipeList.get(0).getName());
            Long creationId = recipeList.get(0).getId();

            recipe.setName("ChangedRecipeName");
            foodPlanerService.edit(recipe);

            recipeList = foodPlanerService.findByName(recipe.getName());
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertEquals("Name was not changed", "ChangedRecipeName", recipeList.get(0).getName());
            Long changedId = recipeList.get(0).getId();

            assertEquals("Recipe was not changed correctly - two different ids", creationId, changedId);
            return null;
        });
    }

    @Test
    public void thatRecipeCanBeRemovedAsAuthenticatedUser() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe = new Recipe("TestRecipe2");

            foodPlanerService.add(recipe);

            List<Recipe> recipeList = foodPlanerService.findByName(recipe.getName());
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertEquals("Name is not like created", "TestRecipe2", recipeList.get(0).getName());

            foodPlanerService.remove(recipe);

            recipeList = foodPlanerService.findByName(recipe.getName());
            assertEquals("Size of list was not like expected", 0, recipeList.size());

            return null;
        });
    }

    @Test
    public void thatFindAllReturnsAllRecipes() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe1 = new Recipe("TestRecipe3");
            foodPlanerService.add(recipe1);
            Recipe recipe2 = new Recipe("TestRecipe4");
            foodPlanerService.add(recipe2);
            Recipe recipe3 = new Recipe("TestRecipe5");
            foodPlanerService.add(recipe3);

            List<Recipe> recipeList = foodPlanerService.findAll();
            assertEquals("Size of list was not like expected", 3, recipeList.size());
            assertEquals("Wrong Recipe", "TestRecipe3", recipeList.get(0).getName());
            assertEquals("Wrong Recipe", "TestRecipe4", recipeList.get(1).getName());
            assertEquals("Wrong Recipe", "TestRecipe5", recipeList.get(2).getName());

            return null;
        });
    }

    @Test
    public void thatFindByNameReturnContains() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe1 = new Recipe("TestRecipe6");
            foodPlanerService.add(recipe1);
            Recipe recipe2 = new Recipe("TestRecipe7");
            foodPlanerService.add(recipe2);
            Recipe recipe3 = new Recipe("OtherName");
            foodPlanerService.add(recipe3);

            List<Recipe> recipeList = foodPlanerService.findByName("Test");
            assertEquals("Size of list was not like expected", 2, recipeList.size());
            assertEquals("Wrong Recipe", "TestRecipe6", recipeList.get(0).getName());
            assertEquals("Wrong Recipe", "TestRecipe7", recipeList.get(1).getName());

            return null;
        });
    }

    @Test
    public void thatFoodIsAddedCorrectly() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe = new Recipe("TestRecipe8");
            recipe.addFood(new Food("Food1",1.5, Unit.KG));

            foodPlanerService.add(recipe);

            List<Recipe> recipeList = foodPlanerService.findByName("Test");
            Food loadedFood = recipeList.get(0).getFoodlist().get(0);
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertEquals("Food name is not correct", "Food1", loadedFood.getName());
            assertEquals("Food amount is not correct", 1.5, loadedFood.getAmount(),0);
            assertEquals("Food unit is not correct", Unit.KG, loadedFood.getUnit());

            return null;
        });
    }

    @Test
    public void thatDescriptionsAreAddedCorrectly() throws Exception {
        authenticatedUser.call(() -> {

            Recipe recipe = new Recipe("TestRecipe8");
            recipe.setDescription("Description");
            recipe.setShortDescription("ShortDescription");

            foodPlanerService.add(recipe);

            List<Recipe> recipeList = foodPlanerService.findByName("Test");
            Recipe loadedRecipe = recipeList.get(0);
            assertEquals("Size of list was not like expected", 1, recipeList.size());
            assertEquals("Food description is not correct", "Description", loadedRecipe.getDescription());
            assertEquals("Food shortDescription is not correct", "ShortDescription", loadedRecipe.getShortDescription());

            return null;
        });
    }

    @Test
    public void thatFoodListCanBeAddedAsAuthenticatedUser() throws Exception {
        authenticatedUser.call(() -> {

            FoodList foodList = new FoodList();
            foodList.add(new Food("Food1",1.5, Unit.KG));

            foodPlanerService.addFoodList(foodList);

            FoodList loadedFoodList = foodPlanerService.findFoodListById(foodList.getId());
            assertEquals("Size of shopping list was not like expected", 1, loadedFoodList.getFoodShoppingList().size());
            assertNotNull("Did not create a FoodList with id", loadedFoodList.getId());

            return null;
        });
    }

    @Test
    public void thatComplexFoodListCanBeAddedAsAuthenticatedUser() throws Exception {
        authenticatedUser.call(() -> {

            FoodList foodList = new FoodList();
            foodList.add(new Food("Food1",1.1, Unit.KG));
            foodList.add(new Food("Food2",2.2, Unit.L));
            foodList.add(new Food("Food3",3.3, Unit.ML));
            foodList.add(new Food("Food4",4.4, Unit.G));
            foodList.add(new Food("Food5",5.5, Unit.PIECE));

            foodPlanerService.addFoodList(foodList);

            FoodList loadedFoodList = foodPlanerService.findFoodListById(foodList.getId());
            assertEquals("Size of shopping list was not like expected", 5, loadedFoodList.getFoodShoppingList().size());
            assertEquals("Name of Food not like expected", "Food1" , loadedFoodList.getFoodShoppingList().get(0).getName());
            assertEquals("Name of Food not like expected", "Food2" , loadedFoodList.getFoodShoppingList().get(1).getName());
            assertEquals("Name of Food not like expected", "Food3" , loadedFoodList.getFoodShoppingList().get(2).getName());
            assertEquals("Name of Food not like expected", "Food4" , loadedFoodList.getFoodShoppingList().get(3).getName());
            assertEquals("Name of Food not like expected", "Food5" , loadedFoodList.getFoodShoppingList().get(4).getName());
            assertEquals("Amount of Food not like expected", 1.1 , loadedFoodList.getFoodShoppingList().get(0).getAmount(),0);
            assertEquals("Amount of Food not like expected", 2.2 , loadedFoodList.getFoodShoppingList().get(1).getAmount(),0);
            assertEquals("Amount of Food not like expected", 3.3 , loadedFoodList.getFoodShoppingList().get(2).getAmount(),0);
            assertEquals("Amount of Food not like expected", 4.4 , loadedFoodList.getFoodShoppingList().get(3).getAmount(),0);
            assertEquals("Amount of Food not like expected", 5.5 , loadedFoodList.getFoodShoppingList().get(4).getAmount(),0);
            assertEquals("Unit of Food not like expected", Unit.KG , loadedFoodList.getFoodShoppingList().get(0).getUnit());
            assertEquals("Unit of Food not like expected", Unit.L , loadedFoodList.getFoodShoppingList().get(1).getUnit());
            assertEquals("Unit of Food not like expected", Unit.ML , loadedFoodList.getFoodShoppingList().get(2).getUnit());
            assertEquals("Unit of Food not like expected", Unit.G , loadedFoodList.getFoodShoppingList().get(3).getUnit());
            assertEquals("Unit of Food not like expected", Unit.PIECE , loadedFoodList.getFoodShoppingList().get(4).getUnit());

            return null;
        });
    }
}
