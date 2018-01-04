package asgn2Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import asgn2Restaurant.PizzaRestaurant;
import asgn2Pizzas.Pizza;

/**
 * A class that tests the methods relating to the handling of Pizza objects in the asgn2Restaurant.PizzaRestaurant class as well as
 * processLog and resetDetails.
 * 
 * @author Person B
 *
 */
public class RestaurantPizzaTests {
	private PizzaRestaurant testRestaurant;
	
	/*
	 * constructs an instance of the pizza restaurant
	 */
	@Before
	public void makePizzaRestaurant(){
		testRestaurant = new PizzaRestaurant();
	}
	
	/*
	 * tests if the constructor made an empty array list
	 */
	@Test
	public void test_constructor(){
		int expectedListSize = 0;
		assertEquals(expectedListSize, this.testRestaurant.getNumPizzaOrders());
	}
	
	/*
	 * Test's if it returns the first pizza in the 20170101.txt. which should be vegetarian
	 */
	@Test
	public void test_GetPizzaByIndex() throws Exception {
		this.testRestaurant.processLog(".//logs/20170101.txt");
		Pizza testPizza = this.testRestaurant.getPizzaByIndex(0);
		String expectedPizza = "Vegetarian";
		assertEquals(expectedPizza, testPizza.getPizzaType());
	}
	
	/*
	 * test's if the getNumPizzaOrders has 3 entries. Given the log file 20170101.txt
	 */
	@Test
	public void test_GetNumPizzaOrders() throws Exception {
		this.testRestaurant.processLog(".//logs/20170101.txt");
		int expectedNumberOfPizzas = 3;
		assertEquals(expectedNumberOfPizzas, this.testRestaurant.getNumPizzaOrders() );
	}
	
	/*
	 * test's if the getTotalProfit returns the proper calculated value in the specification. 
	 * given: 2 vegetarians (cost:5.5, price: 10), 1 Margherita (cost:1.5, price:8), 3 Meat lovers (cost:5,price:12)
	 * it should do the calculations: ((2 * 10) + (1 * 8) + (3 * 12)) - ((2 * 5.5) + (1 * 1.5) + (3 * 5) = 64 - 27.5 = 36.5
	 */
	@Test
	public void test_getTotalProfit() throws Exception {
		this.testRestaurant.processLog(".//logs/20170101.txt");
		double expectedProfits = 36.50; 
		assertEquals(expectedProfits, this.testRestaurant.getTotalProfit() , 0.01);
	}
	
	/*
	 * Test's if it flags an exception when given an invalid index.
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_GetPizzaByIndex_invalidIndex() throws Exception {
		this.testRestaurant.processLog(".//logs/20170101.txt");
		Pizza testPizza = this.testRestaurant.getPizzaByIndex(4);
		String expectedPizza = "Vegetarian";
		assertEquals(expectedPizza, testPizza.getPizzaType());
	}
}
