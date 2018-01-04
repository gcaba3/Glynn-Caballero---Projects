package asgn2Tests;

import org.junit.Test;
import static org.junit.Assert.*;
import java.time.LocalTime;
import asgn2Pizzas.PizzaFactory;
import asgn2Pizzas.VegetarianPizza;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.MargheritaPizza;
import asgn2Pizzas.MeatLoversPizza;

/** 
 * A class that tests the asgn2Pizzas.PizzaFactory class.
 * 
 * @author Person B 
 * 
 */
public class PizzaFactoryTests {
	/*
	 * Tests that the getPizza method returns a MargheritaPizza subclass
	 */
	@Test
	public void valid_classPZM() throws Exception{
		MargheritaPizza ExpectedPizza = new MargheritaPizza(1, LocalTime.of(19, 0,0), LocalTime.of(19, 30,0));
		Pizza testPizza = PizzaFactory.getPizza("PZM", 1,LocalTime.of(19, 0,0),LocalTime.of(19, 30,0));
		assertEquals(ExpectedPizza.getClass(), testPizza.getClass());
	}
	
	/*
	 * Same as above exception with VegetarianPizza subclass
	 */
	@Test
	public void valid_classPZV() throws Exception{
		VegetarianPizza ExpectedPizza = new VegetarianPizza(1, LocalTime.of(19, 0,0), LocalTime.of(19, 30,0));
		Pizza testPizza = PizzaFactory.getPizza("PZV", 1,LocalTime.of(19, 0,0),LocalTime.of(19, 30,0));
		assertEquals(ExpectedPizza.getClass(), testPizza.getClass());
	}
	
	/*
	 * Same as above exception with MeatLoversPizza subclass
	 */
	@Test
	public void valid_classPZL() throws Exception{
		MeatLoversPizza ExpectedPizza = new MeatLoversPizza(1, LocalTime.of(19, 0,0), LocalTime.of(19, 30,0));
		Pizza testPizza = PizzaFactory.getPizza("PZL", 1,LocalTime.of(19, 0,0),LocalTime.of(19, 30,0));
		assertEquals(ExpectedPizza.getClass(), testPizza.getClass());
	}
	
	/*
	 * Tests that the getPizza method throws an exception when an invalid pizza code is given
	 */
	@Test (expected = asgn2Exceptions.PizzaException.class)
	public void invalid_createPizza() throws Exception{
		MargheritaPizza ExpectedPizza = new MargheritaPizza(1, LocalTime.of(0, 0,0), LocalTime.of(0, 0,0));
		Pizza testPizza = PizzaFactory.getPizza("ASS", 1,LocalTime.of(0, 0,0),LocalTime.of(0, 0,0));
		assertEquals(ExpectedPizza.getClass(), testPizza.getClass());
	}
}
