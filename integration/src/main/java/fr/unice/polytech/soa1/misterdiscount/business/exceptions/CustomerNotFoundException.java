package fr.unice.polytech.soa1.misterdiscount.business.exceptions;

public class CustomerNotFoundException extends Exception {

	public CustomerNotFoundException(String s) {
		super("Customer with id #" + s + " was not found.");
	}
	
}