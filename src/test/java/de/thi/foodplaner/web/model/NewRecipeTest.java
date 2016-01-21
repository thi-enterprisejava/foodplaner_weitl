package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.recipe.Food;
import de.thi.foodplaner.domain.recipe.Recipe;
import de.thi.foodplaner.domain.recipe.Unit;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.*;

/**
 * Created by Philipp on 05.01.16.
 */
public class NewRecipeTest {

    NewRecipe testNewRecipe;
    FoodPlanerServiceDatabase mockedService;

    @Before
    public void setUp() throws Exception {
        mockedService = mock(FoodPlanerServiceDatabase.class);
        testNewRecipe = new NewRecipe(mockedService);
    }


    @Test
    public void testDoSaveRecipeReturnValue() throws Exception {
        Recipe testRecipe = new Recipe("Recipe1");
        testNewRecipe.setRecipe(testRecipe);

        assertEquals("Return value from doSaveRecipe is wrong", "recipedetail.xhtml?faces-redirect=true&id=null", testNewRecipe.doSaveRecipe());
    }

    @Test
    public void testDoEditRecipeReturnValue() throws Exception {
        Recipe testRecipe = new Recipe("Recipe1");
        testNewRecipe.setRecipe(testRecipe);

        assertEquals("Return value from doSaveRecipe is wrong", "newrecipe.xhtml?faces-redirect=true&id=null", testNewRecipe.doEditRecipe());
    }

    @Test
    public void testDoRemoveRecipeReturnValue() throws Exception {
        Recipe testRecipe = new Recipe("Recipe1");
        testNewRecipe.setRecipe(testRecipe);

        assertEquals("Return value from doSaveRecipe is wrong", "recipeOverview.xhtml", testNewRecipe.doRemoveRecipe());
    }

    @Test
    public void testDoAddingFoodIfExists() throws Exception {
        Recipe testRecipe = new Recipe("Recipe1");
        testNewRecipe.setRecipe(testRecipe);

        testNewRecipe.setFoodName("TestFood");
        testNewRecipe.setFoodAmount(1);
        testNewRecipe.setFoodUnit(Unit.KG);

        String addingReturnValue = testNewRecipe.doAddFood();

        assertEquals("Did not add a Food to the recipe", 1,testNewRecipe.getRecipe().getFoodlist().size());
        assertEquals("Return value from doAddingFood is wrong", "", addingReturnValue);
    }


    @Test
    public void testLoadingRecipeWithInit() throws Exception {
        testNewRecipe.setId(1L);
        when(mockedService.findById(1L))
                .thenReturn(new Recipe("TestRecipe"));

        testNewRecipe.init();

        Recipe recipe = testNewRecipe.getRecipe();
        assertNotNull(recipe);
        assertEquals("Name of initiated recipe is wrong", "TestRecipe", recipe.getName());
        verify(mockedService).findById(1L);
    }

    @Test
    public void testNotLoadingRecipeWithInitWithIdNotExists() throws Exception {
        testNewRecipe.setId(2L);
        when(mockedService.findById(2L))
                .thenReturn(null);

        testNewRecipe.init();

        Recipe recipe = testNewRecipe.getRecipe();
        assertNull(recipe);
    }

    @Test
    public void testIfRecipeCanBeSavedCorrectly() throws Exception {
        Recipe testRecipe = new Recipe("Recipe1");

        testRecipe.addFood(new Food("TestFood1",1, Unit.KG));
        testRecipe.addFood(new Food("TestFood2",1, Unit.L));
        testRecipe.setDescription("TestDescription");
        testRecipe.setShortDescription("TestShortDescription");
        testNewRecipe.setRecipe(testRecipe);

        testNewRecipe.doSaveRecipe();

        ArgumentCaptor<Recipe> argumentCaptor = ArgumentCaptor.forClass(Recipe.class);
        verify(mockedService).add(argumentCaptor.capture());
        Recipe recipeSubmittedToRepository = argumentCaptor.getValue();

        assertEquals("Wrong saved Name","Recipe1", recipeSubmittedToRepository.getName());
        assertEquals("Wrong saved Description","TestDescription", recipeSubmittedToRepository.getDescription());
        assertEquals("Wrong saved ShortDescription","TestShortDescription", recipeSubmittedToRepository.getShortDescription());
        assertEquals("Not all Food was saved",2, recipeSubmittedToRepository.getFoodlist().size());
    }
}
