package asgn2Pizzas;

import static java.time.temporal.ChronoUnit.MINUTES;

import java.time.LocalTime;

import asgn2Exceptions.PizzaException;


/**
 * 
 *  A class that represents a vegetarian pizza made at the Pizza Palace restaurant. 
 *  The vegetarian pizza has certain toppings listed in Section 5.1 of the Assignment Specification Document.  
 *  A description of the class's fields and their constraints is provided in Section 5.1 of the Assignment Specification.
 * 
 * @author PersonA
 *
 */
public class VegetarianPizza extends Pizza {

	/**
	 * 
	 *  This class represents a vegetarian pizza made at the  Pizza Palace restaurant. The vegetarian pizza has certain
	 *  toppings listed in Section 5.1 of the Assignment Specification Document.  A description of the class's
	 *  fields and their constraints is provided in Section 5.1 of the Assignment Specification.
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification are violated. 
	 * 
     * <P> PRE: TRUE
	 * <P> POST: All field values including the cost per pizza are set
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 *
	 */
	public VegetarianPizza(int quantity, LocalTime orderTime, LocalTime deliveryTime) throws PizzaException {
		super(quantity, orderTime, deliveryTime, "Vegetarian", 10);
		if(orderTime.isAfter(LocalTime.of(23, 0)) || orderTime.isBefore(LocalTime.of(19, 0))) {
			throw new PizzaException("The time is invalid");
		} else if (MINUTES.between(orderTime, deliveryTime) > 60) {
			throw new PizzaException("Delivery longer than an hour");
		} else if(quantity == 0 || quantity > 10) {
			throw new PizzaException("The number of pizzas is invalid");
		}
	}
}
