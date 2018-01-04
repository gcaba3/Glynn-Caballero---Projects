package asgn2Restaurant;


import java.util.ArrayList;
import asgn2Customers.Customer;
import asgn2Customers.CustomerFactory;
import asgn2Exceptions.*;
import asgn2Pizzas.Pizza;
import asgn2Pizzas.PizzaFactory;

import java.io.*;
import java.time.LocalTime;

/**
 *
 * A class that contains methods that use the information in the log file to return Pizza 
 * and Customer object - either as an individual Pizza/Customer object or as an
 * ArrayList of Pizza/Customer objects.
 * 
 * @author Person A and Person B
 *
 */
public class LogHandler {
	


	/**
	 * Returns an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file.
	 * Done by Person B
	 * @param filename The file name of the log file
	 * @return an ArrayList of Customer objects from the information contained in the log file ordered as they appear in the log file. 
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 */

	public static ArrayList<Customer> populateCustomerDataset(String filename) throws CustomerException, LogHandlerException{
		String line = null;
		ArrayList<Customer> customerList = new ArrayList<Customer>();
		try{
			FileReader fileReader = new FileReader(filename);
			BufferedReader bufferedReader = new BufferedReader(fileReader);
			
			//Read each line
			while((line = bufferedReader.readLine()) != null){
				customerList.add(LogHandler.createCustomer(line));
			}
			
			bufferedReader.close();

		} catch(FileNotFoundException fnfe) {
            throw new LogHandlerException("Unable to open file '" + filename + "'");                
        } catch(IOException ioe) {
        	throw new LogHandlerException("Error reading file '" + filename + "'");                  
        } catch (CustomerException ce){
			throw new CustomerException (ce.getMessage());
		} catch (LogHandlerException le){
			throw new LogHandlerException (le.getMessage());
		}
		
		return customerList;
	}		

	/**
	 * Returns an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @param filename The file name of the log file
	 * @return an ArrayList of Pizza objects from the information contained in the log file ordered as they appear in the log file. .
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above
	 * 
	 */
	public static ArrayList<Pizza> populatePizzaDataset(String filename) throws PizzaException, LogHandlerException{
		ArrayList<Pizza> Temp = new ArrayList<Pizza>();
		String line;
		
		try {
			FileReader File = new FileReader(filename);
			BufferedReader buff = new BufferedReader(File);
			while((line = buff.readLine()) != null) {
				Temp.add(LogHandler.createPizza(line));
			}
			buff.close();
			return Temp;
		} catch(FileNotFoundException e) {
            throw new LogHandlerException("Unable to open file '" + filename + "'");                
        } catch(IOException e) {
        	throw new LogHandlerException("Error reading file '" + filename + "'");                  
        } catch(LogHandlerException e) {
        	throw new LogHandlerException("Error with file");
        } catch(PizzaException e) {
        	throw new PizzaException("Data invalid");
        }
	}		

	
	/**
	 * Creates a Customer object by parsing the  information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * 
	 * Done by Person B
	 * @param line - A line from the log file
	 * @return- A Customer object containing the information from the line in the log file
	 * @throws CustomerException - If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Customer createCustomer(String line) throws CustomerException, LogHandlerException{
		Customer newCustomer = null;
		final String commaSplitter = ",";
		String[] customer = line.split(commaSplitter);
		
		//the array locations of fields in the log file
		final int xlocField = 5;
		final int ylocField = 6;
		final int custcodeField = 4;
		final int custnameField = 2;
		final int custnumbField = 3;
		
		//accepted size of one line in the log file
		final int fieldSize = 9;
		
		if(customer.length != fieldSize){
			throw new LogHandlerException("Invalid field size. The log file has a line consisting of '" + customer.length + "' fields.");
		} else {
			try{
				int locationX = Integer.parseInt(customer[xlocField]);
				int locationY = Integer.parseInt(customer[ylocField]);
				newCustomer = CustomerFactory.getCustomer(customer[custcodeField], customer[custnameField], customer[custnumbField], locationX, locationY);
			} catch (CustomerException ce){
				throw new CustomerException (ce.getMessage());
			} catch (ArrayIndexOutOfBoundsException ioe){
				throw new LogHandlerException("Failed to access a certain field in the line.");
			} catch (NumberFormatException nfe){
				throw new LogHandlerException("Either X or Y customer coordinates is not a number in the log file.");
			}
			
			return newCustomer;
		}
	}
	
	/**
	 * Creates a Pizza object by parsing the information contained in a single line of the log file. The format of 
	 * each line is outlined in Section 5.3 of the Assignment Specification.  
	 * @param line - A line from the log file
	 * @return- A Pizza object containing the information from the line in the log file
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException - If there was a problem parsing the line from the log file.
	 */
	public static Pizza createPizza(String line) throws PizzaException, LogHandlerException{
		Pizza Temp;
		LocalTime TempOrdTime = null;
		LocalTime TempDelTime = null;
		String[] PizzaInfo = line.split(",");
		
		
		if(PizzaInfo.length < 9) {
			throw new LogHandlerException("Not enough fields in the log file.");
		}
		
		
		try {
			String[] OrdTime = PizzaInfo[0].split(":");
			String[] DelTime = PizzaInfo[1].split(":");
			if(OrdTime.length != 3 || DelTime.length != 3) {
				throw new PizzaException("Invalid Time format");
			}
			TempOrdTime = LocalTime.of(Integer.parseInt(OrdTime[0]), Integer.parseInt(OrdTime[1]), Integer.parseInt(OrdTime[2]));
			TempDelTime = LocalTime.of(Integer.parseInt(DelTime[0]), Integer.parseInt(DelTime[1]), Integer.parseInt(DelTime[2]));
		} catch(ArrayIndexOutOfBoundsException e){
			throw new LogHandlerException("Invalid Time format");
			
		} catch(NumberFormatException e) {
			throw new LogHandlerException("Invalid Time format");
		}
		
		try {
			if(Integer.parseInt(PizzaInfo[8]) > 10 || Integer.parseInt(PizzaInfo[8]) <= 0) {
				throw new PizzaException("Number of Pizzas is invalid");
			} 
		} catch(NumberFormatException e) {
			throw new LogHandlerException("Number of Pizzas is not an int");
		}
		
		if(!PizzaInfo[7].equals("PZM") && !PizzaInfo[7].equals("PZV") && !PizzaInfo[7].equals("PZL")) {
			throw new PizzaException("Invalid pizza type");
		} else if(TempOrdTime.isBefore(LocalTime.of(19, 0)) || TempOrdTime.isAfter(LocalTime.of(23, 00))) {
			throw new PizzaException("Order time is invalid");
		}
		Temp = PizzaFactory.getPizza(PizzaInfo[7], Integer.parseInt(PizzaInfo[8]), TempOrdTime, TempDelTime);
		return Temp;
	}

}
