package asgn2Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import asgn2Exceptions.*;
import asgn2Restaurant.PizzaRestaurant;
import asgn2Customers.Customer;
import asgn2Customers.DriverDeliveryCustomer;


/**
 * A class that that tests the methods relating to the handling of Customer objects in the asgn2Restaurant.PizzaRestaurant
 * class as well as processLog and resetDetails.
 * 
 * @author Person A
 */
public class RestaurantCustomerTests {
	PizzaRestaurant Restaurant1;
	
	@Before @Test
	public void Setup() {
		Restaurant1 = new PizzaRestaurant();
		try {
			Restaurant1.processLog(".//Logs/RestaurantCustomerTestLog.txt");
		} catch(CustomerException e) {
			
		} catch(LogHandlerException e) {
			
		} catch(PizzaException e) {
			
		}
	}
	
	// BASIC FUNCTIONALITY TESTS
	
	//Tests if the GetCustomerByIndex function returns the correct item
	@Test
	public void GetCustomerByIndexTest() throws CustomerException {
		Customer TempCustomer;
		Customer TestCustomer1 = new DriverDeliveryCustomer("Test1", "0123456781", 5, 5);
		TempCustomer = Restaurant1.getCustomerByIndex(0);
		assertTrue(TempCustomer.equals(TestCustomer1));
	}
	
	//Tests if the resetDetails function resets the values
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ResetDetailsTest() throws CustomerException {
		Restaurant1.resetDetails();
		Restaurant1.getCustomerByIndex(0);
	}
	
	//Tests if the getNumCustomerOrders returns the correct value and that its the same as Pizza Orders
	@Test
	public void GetCustomerOrders() throws CustomerException {
		int CustomerValues = Restaurant1.getNumCustomerOrders();
		int expectedAnswer = 5;
		int PizzaValues = Restaurant1.getNumPizzaOrders();
		assertEquals(CustomerValues, expectedAnswer);
		assertEquals(CustomerValues, PizzaValues);
	}
	
	//Tests that the getTotalDeliveryDistance function is returning the correct value
	@Test
	public void GetDeliveryDistance() throws CustomerException {
		
		double runAnswer = Restaurant1.getTotalDeliveryDistance();
		double expectedAnswer = 48.60232526704263;
		assertTrue(runAnswer == expectedAnswer);
		
	}
	
	//Tests if a customer exception is thrown when an invalid index is supplied
	@Test(expected = CustomerException.class)
	public void InvalidIndexTest() throws CustomerException {
		Restaurant1.getCustomerByIndex(5);
	}
}
