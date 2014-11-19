package fr.unice.polytech.soa1.misterdiscount.business.exceptions;

public class UnknownOperationException extends Exception {

	public UnknownOperationException(String s) {
		super("Operation " + s + " doesn't exist");
	}
	
}
