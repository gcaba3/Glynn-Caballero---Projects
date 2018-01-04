package asgn2Tests;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import asgn2Exceptions.CustomerException;
import asgn2Customers.*;

/**
 * A class that tests the that tests the asgn2Customers.PickUpCustomer, asgn2Customers.DriverDeliveryCustomer,
 * asgn2Customers.DroneDeliveryCustomer classes. Note that an instance of asgn2Customers.DriverDeliveryCustomer 
 * should be used to test the functionality of the  asgn2Customers.Customer abstract class. 
 * 
 * @author Person A
 * 
 *
 */
public class CustomerTests {
	static Customer TestCustomer1;
	static Customer TestCustomer2;
	static Customer TestCustomer3;
	static Customer TestCustomer4;
	
	@Before @Test
	public void Setup() throws CustomerException {
		TestCustomer1 = new DriverDeliveryCustomer("Test1", "0123456781", 4, 5);
		TestCustomer3 = new DroneDeliveryCustomer("Test3", "0123456783", 7, 7);
		TestCustomer4 = new PickUpCustomer("Test4", "0123456783", 0, 0);
	}
	
	//BASIC FUNCTIONALITY TESTS
	
	//Tests if the DVC constructor was functioning
	@Test
	public void DVCConstructorTest() throws CustomerException {
		TestCustomer2 = new DriverDeliveryCustomer("Test2", "0123456781", 4, 5);
	}
	
	//Tests if the DNC constructor was functioning
	@Test
	public void DNCConstructorTest() throws CustomerException {
		TestCustomer2 = new DroneDeliveryCustomer("Test2", "0123456781", 4, 5);
	}
	
	//Tests if the PUC constructor was functioning
	@Test
	public void PUCConstructorTest() throws CustomerException {
		TestCustomer2 = new PickUpCustomer("Test2", "0123456781", 0, 0);
	}
	
	//Tests if the GetName function is returning the correct value
	@Test
	public void GetNameTest() throws CustomerException {
		String RunAnswer = TestCustomer1.getName();
		String ExpectedAnswer = "Test1";
		assertEquals(RunAnswer, ExpectedAnswer);
	}
	
	//Tests if the GetMobileNumber function is returning the correct value
	@Test
	public void GetMobileNumber() throws CustomerException {
		String RunAnswer = TestCustomer1.getMobileNumber();
		String ExpectedAnswer = "0123456781";
		assertEquals(RunAnswer,ExpectedAnswer);
	}
	
	//Tests if the GetCustomerType function is returning the correct value
	@Test
	public void GetCustomerTypeTest() throws CustomerException {
		String RunAnswer = TestCustomer1.getCustomerType();
		String ExpectedAnswer = "Driver Delivery";
		assertTrue(RunAnswer.equals(ExpectedAnswer));
	}
	
	//Tests if the GetLocationX function is returning the correct value
	@Test
	public void GetLocationXTest() throws CustomerException {
		int RunAnswer = TestCustomer1.getLocationX();
		int ExpectedAnswer = 4;
		assertEquals(RunAnswer, ExpectedAnswer);
	}
	
	//Tests if the GetLocationY function is returning the correct value
	@Test
	public void GetLocationYTest() throws CustomerException {
		int RunAnswer = TestCustomer1.getLocationY();
		int ExpectedAnswer = 5;
		assertEquals(RunAnswer, ExpectedAnswer);
	}
	
	//Tests if the GetDeliveryDistance function is returning the correct value for a driver customer
	@Test
	public void GetDriverDeliveryDistanceTest() throws CustomerException {
		double RunAnswer = TestCustomer1.getDeliveryDistance();
		double ExpectedAnswer = 9;
		assertTrue(RunAnswer == ExpectedAnswer);
	}
	
	//Tests if the GetDeliveryDistance function is returning the correct value for a drone customer
	@Test
	public void GetDroneDeliveryDistanceTest() throws CustomerException {
		double RunAnswer = TestCustomer3.getDeliveryDistance();
		double ExpectedAnswer = 9.899494936611665;
		assertTrue(RunAnswer == ExpectedAnswer);
	}
	
	//Tests if the GetDeliveryDistance function is returning the correct value for a pickup customer
	@Test
	public void GetPickupDistanceTest() throws CustomerException {
		double RunAnswer = TestCustomer4.getDeliveryDistance();
		double ExpectedAnswer = 0.0;
		assertTrue(RunAnswer == ExpectedAnswer);
	}
	
	//EXCEPTION TESTS
	
	//Tests if a customerException is thrown when a mobile number doesn't start with 0
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestMobileNumber1() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "2123456789", 6, 6);
	}
	
	//Tests if a customerException is thrown when a mobile number is more than 10 digits
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestMobileNumber2() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "0123456789123", 6, 6);
	}
	
	//Tests if a customerException is thrown when a mobile number has less than 10 digits
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestMobileNumber3() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "012345", 6, 6);
	}
	
	//Tests if a customerException is thrown when a mobile number is not comprised of numbers
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestMobileNumber4() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "ERRORERROR", 6, 6);
	}
	
	//Tests if a customerException is thrown when the X delivery location is greater than 10
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestLocationX() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "0123456789", 11, 6);
	}
	
	//Tests if a customerException is thrown when the Y delivery location is greater than -10
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestLocationX2() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "0123456789", -11, -6);
	}
	
	//Tests if a customerException is thrown when the Y delivery location is greater than 10
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestLocationY() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "0123456789", 6, 11);
	}
	
	//Tests if a customerException is thrown when the X delivery location is greater than -10
	@Test(expected = asgn2Exceptions.CustomerException.class)
	public void ConstructorExceptionTestLocationY2() throws CustomerException {
		TestCustomer2 =  new DriverDeliveryCustomer("Test2", "0123456789", -6, -11);
	}
}
