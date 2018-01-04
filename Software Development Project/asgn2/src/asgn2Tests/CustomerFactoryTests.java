package asgn2Tests;

import static org.junit.Assert.*;
import org.junit.Test;

import asgn2Customers.*;
import asgn2Exceptions.CustomerException;

/**
 * A class the that tests the asgn2Customers.CustomerFactory class.
 * 
 * @author Person A
 *
 */
public class CustomerFactoryTests {
	
	//Tests if the DVC constructor is creating the object correctly
	@Test
	public void DVCConstructorTest() throws CustomerException {
		Customer ExpectedCustomer = new DriverDeliveryCustomer("Test1", "0123456789", 6, 6);
		Customer TestCustomer = CustomerFactory.getCustomer("DVC", "Test1", "0123456789", 6, 6);
		assertTrue(TestCustomer.equals(ExpectedCustomer));
	}
	
	//Tests if the DNC constructor is creating the object correctly
	@Test
	public void DNCConstructorTest() throws CustomerException {
		Customer ExpectedCustomer = new DroneDeliveryCustomer("Test1", "0123456789", 10, 10);
		Customer TestCustomer = CustomerFactory.getCustomer("DNC", "Test1", "0123456789", 10, 10);
		assertTrue(TestCustomer.equals(ExpectedCustomer));
	}
	
	//Tests if the PUC constructor is creating the object correctly
	@Test
	public void PUCConstructorTest() throws CustomerException {
		Customer ExpectedCustomer = new PickUpCustomer("Test1", "0123456789", 0, 0);
		Customer TestCustomer = CustomerFactory.getCustomer("PUC", "Test1", "0123456789", 0, 0);
		assertTrue(TestCustomer.equals(ExpectedCustomer));
	}
	
	//Tests if the constructor is throwing a customer exception when an invalid customer code is supplied
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void FactoryExceptionTest() throws CustomerException {
		CustomerFactory.getCustomer("ERR", "Test1", "0123456789", 6, 6);
	}
}
