package asgn2Tests;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalTime;
import asgn2Pizzas.PizzaTopping;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;
import asgn2Pizzas.VegetarianPizza;
/**
 * A class that that tests the asgn2Pizzas.MargheritaPizza, asgn2Pizzas.VegetarianPizza, asgn2Pizzas.MeatLoversPizza classes. 
 * Note that an instance of asgn2Pizzas.MeatLoversPizza should be used to test the functionality of the 
 * asgn2Pizzas.Pizza abstract class. 
 * 
 * @author Person B
 *
 */
public class PizzaTests {
	private Pizza testMeatLoversPizza;
	
	//Constructs a MeatLoversPizza instance
	@Before
	public void createMeatLoversPizza() throws Exception{
		this.testMeatLoversPizza = new MeatLoversPizza(5, LocalTime.of(19, 0, 0), LocalTime.of(19, 30,0));
	}
	
	/*
	 * Test's if the meatLoversPizza constructs properly with valid inputs. Should construct
	 */
	@Test
	public void test_MeatLoversPizzaConstructor() throws Exception {
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, this.testMeatLoversPizza.getPizzaType());
	}
	
	/*
	 * Same as above except its the margherita subclass
	 */
	@Test
	public void test_MargheritaPizzaConstructor() throws Exception {
		Pizza testMargheritaPizza = new MargheritaPizza(5, LocalTime.of(19, 0, 0), LocalTime.of(19,30,0));
		String expectedPizzaType = "Margherita";
		assertEquals(expectedPizzaType, testMargheritaPizza.getPizzaType());
	}
	
	/*
	 * same as above except its the vegetarian subclass
	 */
	@Test
	public void test_VegetarianPizzaConstructor() throws Exception {
		//Time needs to be between 19 and 23 to be valid
		Pizza testVegetarianPizza = new VegetarianPizza(10, LocalTime.of(19,0, 0), LocalTime.of(19, 30,0));
		String expectedPizzaType = "Vegetarian";
		assertEquals(expectedPizzaType, testVegetarianPizza.getPizzaType());
	}
	
	/*
	 * Test's if the meatLoversPizza constructor still accepts an order during the working hours, but delivery was after 11. Should construct
	 */
	@Test
	public void deliveryAfterEleven() throws Exception {
		Pizza validPizza = new MeatLoversPizza(1, LocalTime.of(22, 30, 0), LocalTime.of(23, 15,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, validPizza.getPizzaType());
	}
	
	/*
	 * Constructor expected to throw exception when the quantity is 0. it should only construct a pizza with a minimum of 1
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void quantityIsZero() throws Exception {
		Pizza invalidPizza = new MeatLoversPizza(0, LocalTime.of(19, 0, 0), LocalTime.of(19, 30,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Test's if the Constructor throws an exception, when the delivery time is before the order time. 
	 * As it doesn't make sense if the pizza was delivered before it was made.
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void pizzaDeliveredBeforeOrder() throws Exception {
		Pizza invalidPizza = new MeatLoversPizza(1, LocalTime.of(20, 0, 0), LocalTime.of(19, 30,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Constructor expected to throw exception when the quantity is over 10. it should only construct pizzas if they're 10 and under
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void quantityIsOverTen() throws Exception{
		Pizza invalidPizza = new MeatLoversPizza(11, LocalTime.of(19, 0, 0), LocalTime.of(19, 30,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Constructor expected to throw exception as the difference between order time and delivery time is more than 60 minutes
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void pizzaShouldBeThrown() throws Exception{
		Pizza invalidPizza = new MeatLoversPizza(3, LocalTime.of(19, 0, 0), LocalTime.of(20, 30,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Constructor expected to throw exception as the order was placed before 7pm
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void orderBeforeOpeningHours() throws Exception{
		Pizza invalidPizza = new MeatLoversPizza(11, LocalTime.of(18, 0, 0), LocalTime.of(18, 30,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Constructor expected to throw exception as the order was placed after 11
	 */
	@Test(expected = asgn2Exceptions.PizzaException.class)
	public void orderAfterClosingHours() throws Exception{
		Pizza invalidPizza = new MeatLoversPizza(11, LocalTime.of(23, 30, 0), LocalTime.of(0, 0,0));
		String expectedPizzaType = "Meat Lovers";
		assertEquals(expectedPizzaType, invalidPizza.getPizzaType());
	}
	
	/*
	 * Test's if getCostPerPizza function returns the proper value, described in the specifications
	 */
	@Test
	public void test_CostOfMeatLoversPizza(){
		double expectedCost = 5.00;
		assertEquals(expectedCost,this.testMeatLoversPizza.getCostPerPizza(),0.01);
	}
	
	/*
	 * Test's if the getPricePerPizza function returns the proper value, described in the specifications
	 */
	@Test
	public void test_GetPriceOfMeatLoversPizza(){
		double expectedPrice = 12.00;
		assertEquals(expectedPrice,this.testMeatLoversPizza.getPricePerPizza(),0.01);
	}
	
	/*
	 * test's if the getOrderCost returns the properly calculated cost given 5 quantity
	 */
	@Test
	public void test_GetOrderCost(){
		double expectedCost = 5 * 5;//25
		assertEquals(expectedCost,this.testMeatLoversPizza.getOrderCost(),0.01);
	}
	
	/*
	 * test's if the getOrderPrice returns the price of 5 meatlovers pizzas, as described in the specification
	 */
	@Test
	public void test_GetOrderPrice(){
		double expectedPrice = 12 * 5; //60
		assertEquals(expectedPrice,this.testMeatLoversPizza.getOrderPrice(),0.01);
	}
	
	/*
	 * test's if the getOrderProfit returns the calculated profits as described in specifiction given 5 meatlovers pizza
	 */
	@Test
	public void test_GetOrderProfit(){
		double expectedProfit = (12 * 5) - (5 * 5); // 60 - 25 = 35
		assertEquals(expectedProfit,this.testMeatLoversPizza.getOrderProfit(),0.01);
	}
	
	/*
	 * checks if the meatlovers pizza has the topping tomato. which it should.
	 */
	@Test
	public void test_ContainsToppingTrue(){
		assertTrue(this.testMeatLoversPizza.containsTopping(PizzaTopping.TOMATO));
	}
	
	/*
	 * test's the quantity of the pizzas in the order
	 */
	@Test
	public void test_GetQuantity(){
		int expectedQuantity = 5;
		assertEquals(expectedQuantity, this.testMeatLoversPizza.getQuantity());
	}
	
}
