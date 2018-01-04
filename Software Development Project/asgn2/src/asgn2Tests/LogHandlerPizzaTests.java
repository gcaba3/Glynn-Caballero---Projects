package asgn2Tests;

import asgn2Restaurant.LogHandler;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.VegetarianPizza;
import org.junit.Test;

import static org.junit.Assert.*;

import java.time.LocalTime;
import java.util.ArrayList;

/** A class that tests the methods relating to the creation of Pizza objects in the asgn2Restaurant.LogHander class.
* 
* @author Person B
* 
*/
public class LogHandlerPizzaTests {
	/*
	 * Tests if the populatePizzaDataset function returns an arraylist with 3 entries.
	 */
	@Test
	public void test_PopulatePizzaDataset() throws Exception{
		ArrayList<Pizza> pizzaList = LogHandler.populatePizzaDataset(".//logs/20170101.txt");
		int expectedSize = 3;
		assertEquals(expectedSize,pizzaList.size() );
	}
	
	/*
	 * Test's if populatePizzaDataset throws an LogHandlerException when given a file that doesn't exists
	 */
	@Test (expected = asgn2Exceptions.LogHandlerException.class)
	public void test_PopulatePizzaDataset_fileDoesntExists() throws Exception{
		ArrayList<Pizza> pizzaList = LogHandler.populatePizzaDataset(".//logs/notARealFile.txt");
		int expectedSize = 3;
		assertEquals(expectedSize,pizzaList.size() );
	}
	
	/*
	 * Tests if the createPizza function returns a pizza object given an input of string.
	 */
	@Test
	public void test_CreatePizza() throws Exception {
		String oneLine = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2"; // first line in 20170101.txt
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(19, 20, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Tests if create pizza function throws an exception when given a wrong pizza code
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_invalidCode() throws Exception {
		String oneLine = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,ERR,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(19, 20, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	
	/*
	 * Tests if createPizza throws a pizza exception when given wrong time format
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_invalidTime() throws Exception {
		String oneLine = "19:00::00,19:20:s,Casey Jones,0123456789,DVC,5,5,PZV,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(19, 20, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Tests if the createPizza function throws an exception when the difference between delivery time and order time, is greater than one hour.
	 * As the pizza should have been thrown out.
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_pizzaThrownOut() throws Exception {
		String oneLine = "19:00:00,21:00:00,Casey Jones,0123456789,DVC,5,5,PZV,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(21, 00, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Test's if the createPizza throws an exception when the delivery time is before the order time.
	 * As it doesn't make sense if the pizza was delivered before it was made.
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_DeliveredBeforeOrdered() throws Exception {
		String oneLine = "20:00:00,19:30:00,Casey Jones,0123456789,DVC,5,5,PZV,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(21, 00, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Test if the createPizza function throws an exception when the order is before 7pm
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_orderBeforeSeven() throws Exception {
		String oneLine = "16:59:59,21:00:00,Casey Jones,0123456789,DVC,5,5,PZV,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(21, 00, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * tests if the createPizza function throws an exception when the order is after eleven
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void test_CreatePizza_orderAfterEleven() throws Exception {
		String oneLine = "23:00:01,23:20:00,Casey Jones,0123456789,DVC,5,5,PZV,2"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(21, 00, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Test's if the createPizza throws a loghandler exception when the given file line is an improper format... i.e missing fields
	 */
	@Test (expected = asgn2Exceptions.LogHandlerException.class)
	public void test_CreatePizza_invalidLine() throws Exception {
		String oneLine = "19:00:00,19:20:00,Casey Jo";
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(19, 20, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
	/*
	 * Tests if createPizza throws a log handler exception when given a quantity thats not a number
	 */
	@Test (expected = asgn2Exceptions.LogHandlerException.class)
	public void test_CreatePizza_invalidQuantity() throws Exception {
		String oneLine = "19:00:00,19:20:00,Casey Jones,0123456789,DVC,5,5,PZV,s"; 
		Pizza ExpectedPizza = new VegetarianPizza(2, LocalTime.of(19, 0, 0), LocalTime.of(19, 20, 0));
		Pizza testPizza = LogHandler.createPizza(oneLine);
		assertEquals(ExpectedPizza, testPizza);
	}
	
}
