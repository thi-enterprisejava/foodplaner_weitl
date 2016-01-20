package de.thi.foodplaner.domain;

import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

/**
 * Created by Philipp on 08.01.16.
 */
public class RecipeTest {

    Recipe testRecipe;

    @Before
    public void setUp() throws Exception {
        this.testRecipe = new Recipe();
    }

    @Test
    public void testAddingFood() throws Exception{
        Food testFood = new Food("Test Adding", 10, Unit.KG);
        this.testRecipe.addFood(testFood);

        assertEquals("Adding Food to Recipe did not work", testFood,this.testRecipe.getFoodlist().get(0));
    }
}
