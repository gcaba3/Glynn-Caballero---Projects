package asgn2Tests;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import asgn2Exceptions.*;
import asgn2Restaurant.LogHandler;
import asgn2Customers.*;
import java.util.*;

/**
 * A class that tests the methods relating to the creation of Customer objects in the asgn2Restaurant.LogHander class.
 *
 * @author Person A
 */
public class LogHandlerCustomerTests {
	Customer Temp1;
	Customer Temp2;
	Customer Temp3;
	Customer Temp4;
	Customer Temp5;
	ArrayList<Customer> ExpectedLogList;
	
	@Before@Test
	public void setup() {
		try {
			ExpectedLogList = new ArrayList<Customer>();
			Temp1 = new DriverDeliveryCustomer("Test1", "0123456781", 5, 5);
			Temp2 = new DriverDeliveryCustomer("Test2", "0123456782", 5, 5);
			Temp3 = new DriverDeliveryCustomer("Test3", "0123456783", 5, 5);
			ExpectedLogList.add(Temp1);
			ExpectedLogList.add(Temp2);
			ExpectedLogList.add(Temp3);
		} catch(CustomerException e) {
			
		}
	}
	
	//Test's if the populatecustomerdataset is returning a correct list based on a logfile
	@Test
	public void PopulateCustomerTest() throws CustomerException {
		ArrayList<Customer> RunAnswer;
		try {
			RunAnswer = LogHandler.populateCustomerDataset(".//Logs/TestLogFileCustomer.txt");
			for(int x = 0; x < 3; x++) {
				Customer tempRun = RunAnswer.get(x);
				Customer tempExpect = ExpectedLogList.get(x);
				assertTrue(tempRun.equals(tempExpect));
			}
		} catch(CustomerException e) {
			fail();
		} catch(LogHandlerException e) {
			fail();
		}
	}
	
	//Tests if there is a log handler exception when supplied with an incomplete log
	@Test(expected = LogHandlerException.class)
	public void IncompleteDatasetTest() throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(".//Logs/ExceptionLogFileCustomer1.txt");
	}
	
	//Tests if there is a customer exception for a mobile number that is not integers
	@Test(expected = CustomerException.class)
	public void NonIntMobileNumberTest() throws CustomerException, LogHandlerException{
		String Temp = "20:00:00,20:20:00,Test1,ERROR,DVC,5,5,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests if there is a customer exception for a phone number not starting in 0
	@Test(expected = CustomerException.class)
	public void Non0startMobileNumberTest() throws CustomerException, LogHandlerException{
		String Temp = "20:00:00,20:20:00,Test1,2123456781,DVC,5,5,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests is a customer exception is thrown when there is more than 10 digits in a mobile number
	@Test(expected = CustomerException.class)
	public void MoreThan10DigitMobileNumberTest() throws CustomerException, LogHandlerException{
		String Temp = "20:00:00,20:20:00,Test1,01234567812222,DVC,6,5,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests is a customer exception is thrown when there is less than 10 digits in a mobile number
	@Test(expected = CustomerException.class)
	public void LessThan10DigitMobileNumberTest() throws CustomerException, LogHandlerException{
		String Temp = "20:00:00,20:20:00,Test1,0123451,DVC,5,5,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests if a customer exception is thrown when an unnacceptable customer code is supplied
	@Test(expected = CustomerException.class)
	public void InvalidCustomerCode() throws CustomerException, LogHandlerException{
		String Temp = "20:00:00,20:20:00,Test1,0123456781,ERR,5,5,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests if a customer exception is thrown when an a X value higher than 10 is supplied
	@Test(expected = CustomerException.class)
	public void InvalidXValue() throws CustomerException, LogHandlerException {
		String Temp = "20:00:00,20:20:00,Test1,0123456781,DVC,11,10,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests if a customer exception is thrown when an a Y value higher than 10 is supplied
	@Test(expected = CustomerException.class)
	public void InvalidYValue() throws CustomerException, LogHandlerException {
		String Temp = "20:00:00,20:20:00,Test1,0123456781,DVC,10,11,PZM,2";
		LogHandler.createCustomer(Temp);
	}
	
	//Tests if a LogHandlerException is thrown when a nonexistent log file is specified
	@Test(expected = LogHandlerException.class)
	public void MissingFileTest() throws CustomerException, LogHandlerException{
		LogHandler.populateCustomerDataset(".//Logs/NonExistent.txt");
	}
}