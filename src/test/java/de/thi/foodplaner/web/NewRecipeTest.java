package de.thi.foodplaner.web;

import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import de.thi.foodplaner.web.model.NewRecipe;

/**
 * Created by Philipp on 05.01.16.
 */
public class NewRecipeTest {

    NewRecipe testNewRecipe;
    FoodPlanerServiceDatabase mockedService;
/*
    @Before
    public void setUp() throws Exception {
        mockedService = Mockito.mock(FoodPlanerServiceDatabase.class);
        testNewRecipe = new NewRecipe(mockedService);
    }

    @Test
    public void testDoAddingFoodReturnValue() throws Exception {
        //TODO
        //assertEquals("Return value from doAddingFood is wrong", "", testNewRecipe.doAddFood());
    }

    @Test
    public void testDoSaveRecipeReturnValue() throws Exception {
        //TODO
        //assertEquals("Return value from doSaveRecipe is wrong", "", testNewRecipe.doSaveRecipe());
    }

    @Test
    public void testLoadingRecipeWithInit() throws Exception {
        testNewRecipe.setId(1L);
        when(mockedService.findById(1L))
                .thenReturn(new Recipe("TestRecipe"));

        testNewRecipe.init();

        Recipe recipe = testNewRecipe.getRecipe();
        assertNotNull(recipe);
        assertEquals(new Recipe("TestRecipe"), recipe);
        verify(mockedService).findById(1L);
    }
*/
}
