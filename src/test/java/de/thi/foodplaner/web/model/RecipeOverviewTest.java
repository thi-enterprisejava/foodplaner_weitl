package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.Recipe;
import de.thi.foodplaner.service.FoodPlanerServiceDatabase;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.List;

import static junit.framework.TestCase.assertNotNull;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Created by Philipp on 20.01.16.
 */
public class RecipeOverviewTest {

    RecipeOverview testRecipeOverview;
    FoodPlanerServiceDatabase mockedService;

    @Before
    public void setUp() throws Exception {
        mockedService = mock(FoodPlanerServiceDatabase.class);

        // mocking some lists
        List<Recipe> list = new LinkedList<>();
        list.add(new Recipe("Test1"));
        list.add(new Recipe("Test2"));
        list.add(new Recipe("Test3"));

        when(mockedService.findAll())
                .thenReturn(list);

        when(mockedService.findByName("Test"))
                .thenReturn(list);

        when(mockedService.findByName(""))
                .thenReturn(list);

        when(mockedService.findByName("Blub"))
                .thenReturn(new LinkedList<Recipe>());

        when(mockedService.findByName("Blab"))
                .thenReturn(null);

        testRecipeOverview = new RecipeOverview(mockedService);
    }

    @Test
    public void testDoSearchReturnValue() throws Exception {
        assertEquals("Return value from doSaveRecipe is wrong", "", testRecipeOverview.doSearch());
    }

    @Test
    public void testDoSearchCheckResultListWithSearchText() throws Exception {
        testRecipeOverview.setSearchText("Test");
        testRecipeOverview.doSearch();

        assertEquals("Full list with test-items", 3, testRecipeOverview.getSearchResultList().size());
    }

    @Test
    public void testDoSearchCheckResultListWithEmptySearchText() throws Exception {
        testRecipeOverview.setSearchText("");
        testRecipeOverview.doSearch();

        assertEquals("Full list with test-items", 3, testRecipeOverview.getSearchResultList().size());
    }

    @Test
    public void testDoSearchCheckResultListWithWrongSearchText() throws Exception {
        testRecipeOverview.setSearchText("Blub");
        testRecipeOverview.doSearch();

        assertEquals("Full list with test-items", 0, testRecipeOverview.getSearchResultList().size());
    }

    @Test
    public void testDoSearchCheckResultListWithNullResult() throws Exception {
        testRecipeOverview.setSearchText("Blab");
        testRecipeOverview.doSearch();

        assertNotNull(testRecipeOverview.getSearchResultList());
        assertEquals("Full list with test-items", 3, testRecipeOverview.getSearchResultList().size());
    }
}
