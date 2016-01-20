package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.Food;
import de.thi.foodplaner.domain.FoodList;
import de.thi.foodplaner.domain.Recipe;
import de.thi.foodplaner.domain.Unit;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentCaptor;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

/**
 * Created by Philipp on 20.01.16.
 */
public class PlaningTest {

    Planing testPlaning;
    FoodPlanerServiceDatabase mockedService;

    @Before
    public void setUp() throws Exception {
        mockedService = mock(FoodPlanerServiceDatabase.class);
        testPlaning = new Planing(mockedService);


        Recipe recipe1 = new Recipe("Recipe1");
        recipe1.addFood(new Food("Food1", 1, Unit.KG));
        Recipe recipe2 = new Recipe("Recipe2");
        recipe2.addFood(new Food("Food2", 1, Unit.KG));
        Recipe recipe3 = new Recipe("Recipe3");
        recipe3.addFood(new Food("Food3", 1, Unit.KG));


        when(mockedService.findById(1L))
                .thenReturn(recipe1);
        when(mockedService.findById(2L))
                .thenReturn(recipe2);
        when(mockedService.findById(3L))
                .thenReturn(recipe3);
        testPlaning.setFirst(1L);
        testPlaning.setSecond(2L);
        testPlaning.setThird(3L);

    }

    @Test
    public void testIfDoGoAddsAllRecipesToFoodList() throws Exception {
        testPlaning.doGo();

        ArgumentCaptor<FoodList> argumentCaptor = ArgumentCaptor.forClass(FoodList.class);
        verify(mockedService).addFoodList(argumentCaptor.capture());
        FoodList foodListSubmittedToRepository = argumentCaptor.getValue();

        assertEquals("FoodList is not filled with 3 Foods",3,foodListSubmittedToRepository.getFoodShoppingList().size());
        assertEquals("First Food in list was wrong","Food1",foodListSubmittedToRepository.getFoodShoppingList().get(0).getName());
        assertEquals("Second Food in list was wrong","Food2",foodListSubmittedToRepository.getFoodShoppingList().get(1).getName());
        assertEquals("Third Food in list was wrong","Food3",foodListSubmittedToRepository.getFoodShoppingList().get(2).getName());
    }

    @Test
    public void testDoGoReturnValue() throws Exception {
        assertEquals("Wrong return value","list.xhtml?faces-redirect=true&id=null",testPlaning.doGo());
    }

    @Test
    public void testDoGoIfOneRecipeIsZero() throws Exception {
        testPlaning.setFirst(0L);

        assertEquals("Wrong return value", "", testPlaning.doGo());
    }
}
