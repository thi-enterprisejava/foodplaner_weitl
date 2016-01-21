package de.thi.foodplaner.web.model;

import de.thi.foodplaner.domain.recipe.Food;
import de.thi.foodplaner.domain.planing.FoodList;
import de.thi.foodplaner.domain.recipe.Unit;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by Philipp on 20.01.16.
 */
public class ListOverviewTest {

    ListOverview listOverview = new ListOverview();

    private final static int NUMBER_OF_EXPECTED_ITEMS = 12;
    @Before
    public void setUp() throws Exception {
        FoodList foodList = new FoodList();
        foodList.add(new Food("Food1",1, Unit.KG));
        foodList.add(new Food("Food2",0.5, Unit.KG));
        foodList.add(new Food("Food3",1500, Unit.G));
        foodList.add(new Food("Food4",0.5, Unit.L));
        foodList.add(new Food("Food5",1500, Unit.ML));
        foodList.add(new Food("Food6",2, Unit.PIECE));
        foodList.add(new Food("Food1",1, Unit.KG));
        foodList.add(new Food("Food7",1, Unit.KG));
        foodList.add(new Food("Food7",500, Unit.G));
        foodList.add(new Food("Food8",100, Unit.G));
        foodList.add(new Food("Food8",0.1, Unit.KG));
        foodList.add(new Food("Food9",1, Unit.L));
        foodList.add(new Food("Food9",500, Unit.ML));
        foodList.add(new Food("Food10",100, Unit.ML));
        foodList.add(new Food("Food10",0.1, Unit.L));
        foodList.add(new Food("Food11",1, Unit.KG));
        foodList.add(new Food("Food11",1, Unit.L));

        listOverview.setFoodList(foodList);
    }

    @Test
    public void testGetFoodListSameNameAddingTogether() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on first position","Food1", foodList.getFoodShoppingList().get(0).getName());
        assertEquals("Wrong amount at first Food", 2.0 , foodList.getFoodShoppingList().get(0).getAmount(),0);
        assertEquals("Wrong unit at first Food", Unit.KG, foodList.getFoodShoppingList().get(0).getUnit());
    }

    @Test
    public void testGetFoodListChangingKgToG() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS, foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on second position","Food2", foodList.getFoodShoppingList().get(1).getName());
        assertEquals("Wrong amount at second Food", 500 , foodList.getFoodShoppingList().get(1).getAmount(),0);
        assertEquals("Wrong unit at second Food", Unit.G, foodList.getFoodShoppingList().get(1).getUnit());
    }

    @Test
    public void testGetFoodListChangingGToKg() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS, foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on third position","Food3", foodList.getFoodShoppingList().get(2).getName());
        assertEquals("Wrong amount at third Food", 1.5 , foodList.getFoodShoppingList().get(2).getAmount(),0);
        assertEquals("Wrong unit at third Food", Unit.KG, foodList.getFoodShoppingList().get(2).getUnit());
    }

    @Test
    public void testGetFoodListChangeLtoMl() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS, foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 4th position","Food4", foodList.getFoodShoppingList().get(3).getName());
        assertEquals("Wrong amount at 4th Food", 500 , foodList.getFoodShoppingList().get(3).getAmount(),0);
        assertEquals("Wrong unit at 4th Food", Unit.ML, foodList.getFoodShoppingList().get(3).getUnit());
    }

    @Test
    public void testGetFoodListChangeMltoL() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS, foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 5th position","Food5", foodList.getFoodShoppingList().get(4).getName());
        assertEquals("Wrong amount at 5th Food", 1.5 , foodList.getFoodShoppingList().get(4).getAmount(),0);
        assertEquals("Wrong unit at 5th Food", Unit.L, foodList.getFoodShoppingList().get(4).getUnit());

    }

    @Test
    public void testGetFoodListNotChangingPieces() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS, foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 6th position","Food6", foodList.getFoodShoppingList().get(5).getName());
        assertEquals("Wrong amount at 6th Food", 2 , foodList.getFoodShoppingList().get(5).getAmount(),0);
        assertEquals("Wrong unit at 6th Food", Unit.PIECE, foodList.getFoodShoppingList().get(5).getUnit());
    }

    @Test
    public void testGetFoodListSameNameAddingTogetherDifferentUnitTypesKgAndG() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 7th position","Food7", foodList.getFoodShoppingList().get(6).getName());
        assertEquals("Wrong amount at 7th Food", 1.5 , foodList.getFoodShoppingList().get(6).getAmount(),0);
        assertEquals("Wrong unit at 7th Food", Unit.KG, foodList.getFoodShoppingList().get(6).getUnit());
    }

    @Test
    public void testGetFoodListSameNameAddingTogetherDifferentUnitTypesGAndKg() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 8th position","Food8", foodList.getFoodShoppingList().get(7).getName());
        assertEquals("Wrong amount at 8th Food", 200 , foodList.getFoodShoppingList().get(7).getAmount(),0);
        assertEquals("Wrong unit at 8th Food", Unit.G, foodList.getFoodShoppingList().get(7).getUnit());
    }

    @Test
    public void testGetFoodListSameNameAddingTogetherDifferentUnitTypesLAndMl() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 9th position","Food9", foodList.getFoodShoppingList().get(8).getName());
        assertEquals("Wrong amount at 9th Food", 1.5 , foodList.getFoodShoppingList().get(8).getAmount(),0);
        assertEquals("Wrong unit at 9th Food", Unit.L, foodList.getFoodShoppingList().get(8).getUnit());
    }

    @Test
    public void testGetFoodListSameNameAddingTogetherDifferentUnitTypesMlAndL() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 10th position","Food10", foodList.getFoodShoppingList().get(9).getName());
        assertEquals("Wrong amount at 10th Food", 200 , foodList.getFoodShoppingList().get(9).getAmount(),0);
        assertEquals("Wrong unit at 10th Food", Unit.ML, foodList.getFoodShoppingList().get(9).getUnit());
    }

    @Test
    public void testGetFoodListSameNameAddingNotTogetherWithDifferentTypes() throws Exception {
        FoodList foodList = this.listOverview.getFoodList();

        assertEquals("The length of the food list is wrong", NUMBER_OF_EXPECTED_ITEMS , foodList.getFoodShoppingList().size());

        assertEquals("Wrong Food on 10th position","Food11", foodList.getFoodShoppingList().get(10).getName());
        assertEquals("Wrong amount at 10th Food", 1 , foodList.getFoodShoppingList().get(10).getAmount(),0);
        assertEquals("Wrong unit at 10th Food", Unit.KG, foodList.getFoodShoppingList().get(10).getUnit());

        assertEquals("Wrong Food on 10th position","Food11", foodList.getFoodShoppingList().get(11).getName());
        assertEquals("Wrong amount at 10th Food", 1 , foodList.getFoodShoppingList().get(11).getAmount(),0);
        assertEquals("Wrong unit at 10th Food", Unit.L, foodList.getFoodShoppingList().get(11).getUnit());
    }
}
