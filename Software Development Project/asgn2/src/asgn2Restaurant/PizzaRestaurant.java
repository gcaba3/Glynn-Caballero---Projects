package asgn2Restaurant;

import java.util.ArrayList;

import asgn2Customers.Customer;
import asgn2Exceptions.CustomerException;
import asgn2Exceptions.LogHandlerException;
import asgn2Exceptions.PizzaException;
import asgn2Pizzas.Pizza;
import asgn2Restaurant.LogHandler;

/**
 * This class acts as a ‘model’ of a pizza restaurant. It contains an ArrayList of Pizza objects and an ArrayList of  Customer objects.
 *  It contains a method that can populate the ArrayLists,  several methods to retrieve information about the ArrayLists and 
 *  a method to reset the array list. Information about the x and y location of the restaurant and the time that first and last 
 *  orders are accepted are listed in Section 5 of the Assignment Specification. 
 *  
 *  Any exceptions raised by one of the methods called by this class should be passed to asgn2GUIs.PizzaGUI so that it can be shown to
 *  the user.
 * 
 * @author Person A and Person B
 *
 */
public class PizzaRestaurant {

	private ArrayList<Customer> customers;
	private ArrayList<Pizza> pizzas;

	
	/**
	 * Creates an instance of the PizzaRestaurant and sets the customers and pizzas fields to
	 * an appropriate initial empty state. 
	 * 
	 * <P> PRE: TRUE
	 * <P> POST: The customers and pizzas fields are initialized to an empty state
	 * 
	 */
	public PizzaRestaurant() {
		pizzas = new ArrayList<Pizza>();
		customers = new ArrayList<Customer>();
		
	}

	/**
	 * This method processes the information contained in the log file and populates the customers and pizzas fields.  
	 * The other classes that the method interacts with are listed in Section 11 of the specification document. 
     *
     * Done by Person B
     *
     * <P> PRE: TRUE
     * <P>POST: If no exception is thrown then the customers and pizzas fields are populated with the details in the log file ordered as they appear in teh log file.
     * <P>      If an exception is thrown then the customers and pizzas fields should be empty.
     * 
	 * @param filename The log's filename
	 * @return true if the file was process correctly otherwise false
	 * @throws CustomerException If the log file contains semantic errors leading that violate the customer constraints listed in Section 5.3 of the Assignment Specification or contain an invalid customer code (passed by another class).
	 * @throws PizzaException If the log file contains semantic errors leading that violate the pizza constraints listed in Section 5.3 of the Assignment Specification or contain an invalid pizza code (passed by another class).
	 * @throws LogHandlerException If there was a problem with the log file not related to the semantic errors above (passed by another class).
	 */
	public boolean processLog(String filename) throws CustomerException, PizzaException, LogHandlerException{
		boolean isValid = false;
		try {
			LogHandler.populateCustomerDataset(filename);
			LogHandler.populatePizzaDataset(filename);
			
			isValid = true;
		} catch (CustomerException ce){
			isValid = false;
			throw new CustomerException (ce.getMessage());
		} catch (LogHandlerException le){
			isValid = false;
			throw new LogHandlerException (le.getMessage());
		} catch (PizzaException pe){
			isValid = false;
			throw new PizzaException (pe.getMessage());
		}
		
		
		if(isValid){
			this.customers = LogHandler.populateCustomerDataset(filename);
			this.pizzas = LogHandler.populatePizzaDataset(filename);
		} else {
			this.resetDetails();
		}
		
		return isValid;
	}

	/**
	 * Returns the Customer object contained at the specified index of the customers field. The index should be the same as the index in the log file.
	 * 
	 * Done by Person B
	 * 
	 * @param index - The index within the customers field to retrieve.
	 * @return The Customer object located at the specified index.
	 * @throws CustomerException if index is invalid.
	 */
	public Customer getCustomerByIndex(int index) throws CustomerException{
		try{
			return this.customers.get(index);
		} catch (IndexOutOfBoundsException e){
			throw new CustomerException("Invalid index given");
		}
	}
	
	/**
	 * Returns the Pizza object contained at the specified index of the pizzas field. The index should be the same as the index in the log file.
	 * @param index - The index within the pizzas field to retrieve.
	 * @return The Pizza object located at the specified index.
	 * @throws PizzaException if index is invalid.
	 */	
	public Pizza getPizzaByIndex(int index) throws PizzaException{
		if(index > this.pizzas.size() || index < 0) {
			throw new PizzaException("Index Invalid");
		} else {
			return this.pizzas.get(index);
		}
	}
	
	/**
	 * Returns the number of objects contained in the pizzas field. This value SHOULD be the same as 
	 * the value returned by getNumCustomerOrders.
	 * 
	 * @return the number of objects contained in the pizzas field.
	 */
	public int getNumPizzaOrders(){
		return this.pizzas.size();
	}

	/**
	 * Returns the number of objects contained in the customers field. This value SHOULD be the same as 
	 * the value returned by getNumPizzaOrders.
	 * 
	 * Done by Person B
	 * 
	 * @return the number of objects contained in the customers field.
	 */
	public int getNumCustomerOrders(){
		return this.customers.size();
	}

			
	
	/**
	 * Returns the total delivery distance for all of the customers.
	 * 
	 * Done by Person B
	 * 
	 * @return the total delivery distance for all Customers objects in the customers field.
	 */
	public double getTotalDeliveryDistance(){
		double totalDeliveryDistance = 0;
		for(Customer customer : this.customers){
			totalDeliveryDistance += customer.getDeliveryDistance();
		}
		return totalDeliveryDistance;
	}

	/**
	 * Returns the total profit for all of the pizza orders.
	 * 
	 * @return the total profit for all of the Pizza objects in the pizzas field.
	 */	
	public double getTotalProfit(){
		double profit = 0;
		for(int x = 0; x < this.pizzas.size(); x++) {
			profit = profit + pizzas.get(x).getOrderProfit();
		}
		return profit;
	}
	
	/**
	 * Resets the pizzas and customers fields to their initial empty states.
	 * 
	 * <P> PRE: True
	 * <P> POST:  The pizzas and customers fields are set to their initial empty states
	 */
	public void resetDetails(){
		this.pizzas = new ArrayList<Pizza>();
		this.customers = new ArrayList<Customer>();
	}

}
