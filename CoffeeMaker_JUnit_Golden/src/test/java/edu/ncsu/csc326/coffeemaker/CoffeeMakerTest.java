package edu.ncsu.csc326.coffeemaker;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;

import edu.ncsu.csc326.coffeemaker.exceptions.InventoryException;
import edu.ncsu.csc326.coffeemaker.exceptions.RecipeException;


public class CoffeeMakerTest {
	
	/**
	 * The object under test.
	 */
	private CoffeeMaker coffeeMaker;
	
	// Sample recipes to use in testing.
	private Recipe recipe1;
	private Recipe recipe2;
	private Recipe recipe3;
	private Recipe recipe4;


	/**
	 * Initializes some recipes to test with and the {@link CoffeeMaker} 
	 * object we wish to test.
	 * 
	 * @throws RecipeException  if there was an error parsing the ingredient 
	 * 		amount when setting up the recipe.
	 */
	@Before
	public void setUp() throws RecipeException {
		coffeeMaker = new CoffeeMaker();
		
		//Set up for r1
		recipe1 = new Recipe();
		recipe1.setName("Coffee");
		recipe1.setAmtChocolate("0");
		recipe1.setAmtCoffee("3");
		recipe1.setAmtMilk("1");
		recipe1.setAmtSugar("1");
		recipe1.setPrice("50");
		
		//Set up for r2
		recipe2 = new Recipe();
		recipe2.setName("Mocha");
		recipe2.setAmtChocolate("20");
		recipe2.setAmtCoffee("3");
		recipe2.setAmtMilk("1");
		recipe2.setAmtSugar("1");
		recipe2.setPrice("75");
		
		//Set up for r3
		recipe3 = new Recipe();
		recipe3.setName("Latte");
		recipe3.setAmtChocolate("0");
		recipe3.setAmtCoffee("3");
		recipe3.setAmtMilk("3");
		recipe3.setAmtSugar("1");
		recipe3.setPrice("100");
		
		//Set up for r4
		recipe4 = new Recipe();
		recipe4.setName("Hot Chocolate");
		recipe4.setAmtChocolate("4");
		recipe4.setAmtCoffee("0");
		recipe4.setAmtMilk("1");
		recipe4.setAmtSugar("1");
		recipe4.setPrice("65");
	}
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with well-formed quantities
	 * Then we do not get an exception trying to read the inventory quantities.
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test
	public void testAddInventory() throws InventoryException {
		coffeeMaker.addInventory("4","7","0","9");
	}
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventoryException() throws InventoryException {
		coffeeMaker.addInventory("4", "-1", "asdf", "3");
	}
	
	
	
	/**
	 * Given a coffee maker with one valid recipe
	 * When we make coffee, selecting the valid recipe and paying more than 
	 * 		the coffee costs
	 * Then we get the correct change back.
	 */
	@Test
	public void testMakeCoffee() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(25, coffeeMaker.makeCoffee(0, 75));
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * Here we are giving 100 to make coffee 
	 * 		the coffee costs 75
	 * Then we get back 100 as change, because recipe ingredients require more items.
	 */
	
	
	/**
	 * Given a coffee maker with the default inventory
	 * When we add inventory with malformed quantities (i.e., a negative 
	 * quantity and a non-numeric string)
	 * Then we get an inventory exception
	 * 
	 * @throws InventoryException  if there was an error parsing the quanity
	 * 		to a positive integer.
	 */
	@Test(expected = InventoryException.class)
	public void testAddInventory_2() throws InventoryException {
		coffeeMaker.addInventory("10", "0.5", "3", "6");
	}
	
	
	
	/**
	 * Add a recipe to the recipe list
	 * 
	 * returns true if given recipe as parameter
	 * 
	 * returns false otherwise
	 *
	 */
	
	
	@Test
	public void testAddRecipe() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe1));
	}
	
	
	/**
	 * Add a recipe to the recipe list
	 * 
	 * returns true if given recipe as parameter
	 * 
	 * returns false otherwise
	 *
	 */
	
	
	@Test
	public void testAddRecipe_2() {
		coffeeMaker.addRecipe(recipe1);
		coffeeMaker.addRecipe(recipe2);
		coffeeMaker.addRecipe(recipe3);
		assertFalse(coffeeMaker.addRecipe(recipe4));
	}
	
	
	/**
	 * Recipe to delete, checking if null than it will return null
	 * else will return the deleted recipe name 
	 */
	
	@Test
	public void testDeleteRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1.getName(),coffeeMaker.deleteRecipe(0));
	}
	
	
	/**
	 * To edit a recipe add it to recipe book  
	 *  editRecipe method in the coffee maker will return the name of the recipe
	 *  
	 *  if the index does not exist or the recipe is null it will return null
	 *  
	 *  */
	
	@Test
	public void testEditRecipe() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(recipe1.getName(),coffeeMaker.editRecipe(0, recipe1));
	}
	
	
	
	/**
	 * Check inventory of all ingredients
	 * */
	
	@Test
	public void testCheckInventory() {
		assertEquals(
				"Coffee: 15\n"
				+ "Milk: 15\n"
				+ "Sugar: 15\n"
				+ "Chocolate: 15\n",
				coffeeMaker.checkInventory());
	}
	
	
	@Test
	public void testCheckInventory_2() throws InventoryException {
		try {
		coffeeMaker.addInventory("3", "4", "5", "0");
		} catch (InventoryException e) {
			throw new InventoryException("Numbers must be integer.");
		}
		assertEquals(
				"Coffee: 18\n"
				+ "Milk: 19\n"
				+ "Sugar: 20\n"
				+ "Chocolate: 15\n",
				coffeeMaker.checkInventory());
	}
	
	
	@Test
	public void testCheckInventory_3() throws InventoryException {
		try {
		coffeeMaker.addInventory("0", "0", "0", "0");
		} catch (InventoryException e) {
			throw new InventoryException("Numbers must be integer.");
		}
		assertFalse(
				"Coffee: 18\n"
				+ "Milk: 19\n"
				+ "Sugar: 20\n"
				+ "Chocolate: 15\n" ==
				coffeeMaker.checkInventory());
	}
	
	
	
	
	@Test
	public void testMakeCoffee_2() {
		coffeeMaker.addRecipe(recipe2);
		assertEquals(100,coffeeMaker.makeCoffee(0, 100));
	}
	
	
	/**
	 * Given a coffee maker with one valid recipe
	 * Here we are giving 50 to make coffee 
	 * 		the coffee costs 100
	 * Then we get back 100 as change, because it is costly than what we paid.
	 */
	
	@Test
	public void testMakeCoffee_3() {
		coffeeMaker.addRecipe(recipe3);
		assertEquals(50,coffeeMaker.makeCoffee(0, 50));
	}
	
	
	/**
	 * Given a coffee maker with one valid recipe
	 * Here we are giving 65 to make coffee 
	 * 		the coffee costs 65
	 * Then we get back 0 as change.
	 */
	
	@Test
	public void testMakeCoffee_4() {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(0,coffeeMaker.makeCoffee(0, 65));
	}
	
	/**
	 * Given a coffee maker with one valid recipe
	 * Here we are giving 65 to make coffee 
	 * 		the coffee costs 65, but we are giving 6 as recipe number which does not exist.
	 *     Here it is out of bounds to the recipe array
	 * So we get back 65 as change.
	 */
	
	
	@Test
	public void testMakeCoffee_5() {
		coffeeMaker.addRecipe(recipe4);
		assertEquals(65,coffeeMaker.makeCoffee(6, 65));
	}
	
	
	/**
	 * Given a coffee maker with one valid recipe
	 * Here we are giving negative value -50 to make coffee 
	 * 		the coffee costs 50,
	 * So we get back -50 as change.
	 */
	
	@Test
	public void testMakeCoffee_6() {
		coffeeMaker.addRecipe(recipe1);
		assertEquals(-50,coffeeMaker.makeCoffee(-1, -50));
	}
	
	
	@Test
	public void testMakeCoffee_7() {
		assertEquals(50,coffeeMaker.makeCoffee(0, 50));
	}
	
	
	
	
	
}
