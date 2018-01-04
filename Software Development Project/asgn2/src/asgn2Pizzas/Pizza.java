package asgn2Pizzas;

import static java.time.temporal.ChronoUnit.MINUTES;
import java.time.LocalTime;

import asgn2Exceptions.PizzaException;

/**
 * An abstract class that represents pizzas sold at the Pizza Palace restaurant. 
 * The Pizza class is used as a base class of VegetarianPizza, MargheritaPizza and Meatlovers Pizza. 
 * Each of these subclasses have a different set of toppings. A description of the class's fields
 * and their constraints is provided in Section 5.1 of the Assignment Specification. 
 * 
 * @author Person A
 *
 */
public abstract class Pizza  {
	
	/**
	 *  This class represents a pizza produced at the Pizza Palace restaurant.  A detailed description of the class's fields
	 *  and parameters is provided in the Assignment Specification, in particular in Section 5.1. 
	 *  A PizzaException is thrown if the any of the constraints listed in Section 5.1 of the Assignment Specification
	 *  are violated. 
     *
     *  PRE: TRUE
	 *  POST: All field values except cost per pizza are set
	 * 
	 * @param quantity - The number of pizzas ordered 
	 * @param orderTime - The time that the pizza order was made and sent to the kitchen 
	 * @param deliveryTime - The time that the pizza was delivered to the customer
	 * @param type -  A human understandable description of this Pizza type
	 * @param price - The price that the pizza is sold to the customer
	 * @throws PizzaException if supplied parameters are invalid 
	 * 
	 */

	private double pizzaPrice;
	private double pizzaCost;
	private int pizzaQuantity;
	private String pizzaType;
	private PizzaTopping[] Toppings;
	
	public Pizza(int quantity, LocalTime orderTime, LocalTime deliveryTime, String type, double price) throws PizzaException {
		if(orderTime.isAfter(LocalTime.of(23, 0)) || orderTime.isBefore(LocalTime.of(19, 0)) || deliveryTime.isBefore(orderTime)) {
			throw new PizzaException("The time is invalid");
		} else if (MINUTES.between(orderTime, deliveryTime) > 60) {
			throw new PizzaException("Delivery longer than an hour");
		} else if(quantity == 0 || quantity > 10) {
			throw new PizzaException("The number of pizzas is invalid");
		} else if(type != "Margherita" &&  type != "Vegetarian" && type != "Meat Lovers") {
			throw new PizzaException("The type of pizza is invalid");
		}
		
		pizzaQuantity = quantity;
		pizzaPrice = price;
		pizzaType = type;
		
		//Makes an array containing all the toppings for the pizza type
		if(this.pizzaType.equals("Margherita")) {
			Toppings = new PizzaTopping[2];
			Toppings[0] = PizzaTopping.TOMATO;
			Toppings[1] = PizzaTopping.CHEESE;
		} else if(this.pizzaType.equals("Vegetarian")) {
			Toppings = new PizzaTopping[5];
			Toppings[0] = PizzaTopping.TOMATO;
			Toppings[1] = PizzaTopping.CHEESE;
			Toppings[2] = PizzaTopping.EGGPLANT;
			Toppings[3] = PizzaTopping.MUSHROOM;
			Toppings[4] = PizzaTopping.CAPSICUM;
		} else if(this.pizzaType.equals("Meat Lovers")) {
			Toppings = new PizzaTopping[5];
			Toppings[0] = PizzaTopping.TOMATO;
			Toppings[1] = PizzaTopping.CHEESE;
			Toppings[2] = PizzaTopping.BACON;
			Toppings[3] = PizzaTopping.PEPPERONI;
			Toppings[4] = PizzaTopping.SALAMI;
		}
		calculateCostPerPizza();
	}

	/**
	 * Calculates how much a pizza would could to make calculated from its toppings.
	 *  
     * <P> PRE: TRUE
	 * <P> POST: The cost field is set to sum of the Pizzas's toppings
	 */
	public final void calculateCostPerPizza(){
		double cost = 0.0;
		for(int x = 0; x < this.Toppings.length; x++) {
			cost = cost + Toppings[x].getCost();
		}
		this.pizzaCost = cost;
	}
	
	/**
	 * Returns the amount that an individual pizza costs to make.
	 * @return The amount that an individual pizza costs to make.
	 */
	public final double getCostPerPizza(){
		return this.pizzaCost;
	}

	/**
	 * Returns the amount that an individual pizza is sold to the customer.
	 * @return The amount that an individual pizza is sold to the customer.
	 */
	public final double getPricePerPizza(){
		return this.pizzaPrice;
	}

	/**
	 * Returns the amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order costs to make, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderCost(){
		return this.pizzaQuantity * this.pizzaCost;
	}
	
	/**
	 * Returns the amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 * @return The amount that the entire order is sold to the customer, taking into account the type and quantity of pizzas. 
	 */
	public final double getOrderPrice(){
		return this.pizzaPrice * this.pizzaQuantity;
	}
	
	/**
	 * Returns the profit made by the restaurant on the order which is the order price minus the order cost. 
	 * @return  Returns the profit made by the restaurant on the order which is the order price minus the order cost.
	 */
	public final double getOrderProfit(){
		double profit;
		profit = (this.pizzaPrice * this.pizzaQuantity) - (this.pizzaCost * this.pizzaQuantity);
		return profit;
	}

	/**
	 * Indicates if the pizza contains the specified pizza topping or not. 
	 * @param topping -  A topping as specified in the enumeration PizzaTopping
	 * @return Returns  true if the instance of Pizza contains the specified topping and false otherwise.
	 */
	public final boolean containsTopping(PizzaTopping topping){
		for(int x = 0; x < this.Toppings.length; x++) {
			if(Toppings[x] == topping) {
				return true;
			}
		}
		return false;
	}
	
	/**
	 * Returns the quantity of pizzas ordered. 
	 * @return the quantity of pizzas ordered. 
	 */
	public final int getQuantity(){
		return this.pizzaQuantity;
	}

	/**
	 * Returns a human understandable description of the Pizza's type. 
	 * The valid alternatives are listed in Section 5.1 of the Assignment Specification. 
	 * @return A human understandable description of the Pizza's type.
	 */
	public final String getPizzaType(){
		return this.pizzaType;
	}

	/**
	 * Compares *this* Pizza object with an instance of an *other* Pizza object and returns true if  
	 * if the two objects are equivalent, that is, if the values exposed by public methods are equal.
	 * You do not need to test this method.
	 *  
	 * @return true if *this* Pizza object and the *other* Pizza object have the same values returned for 	
	 * getCostPerPizza(), getOrderCost(), getOrderPrice(), getOrderProfit(), getPizzaType(), getPricePerPizza() 
	 * and getQuantity().
	 *   
	 */
	@Override
	public boolean equals(Object other){
		Pizza otherPizza = (Pizza) other;
		return ((this.getCostPerPizza()) == (otherPizza.getCostPerPizza()) &&
			(this.getOrderCost()) == (otherPizza.getOrderCost())) &&				
			(this.getOrderPrice()) == (otherPizza.getOrderPrice()) &&
			(this.getOrderProfit()) == (otherPizza.getOrderProfit()) &&
			(this.getPizzaType() == (otherPizza.getPizzaType()) &&
			(this.getPricePerPizza()) == (otherPizza.getPricePerPizza()) &&
			(this.getQuantity()) == (otherPizza.getQuantity()));
	}
}
