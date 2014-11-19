package fr.unice.polytech.soa1.misterdiscount.business.exceptions;

public class OrderNotFoundException extends Exception {

	public OrderNotFoundException(String s) {
		super("Order with id #" + s + " was not found.");
	}
	
}
