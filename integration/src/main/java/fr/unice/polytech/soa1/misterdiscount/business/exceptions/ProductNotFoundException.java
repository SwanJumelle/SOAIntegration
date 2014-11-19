package fr.unice.polytech.soa1.misterdiscount.business.exceptions;

public class ProductNotFoundException extends Exception {

	public ProductNotFoundException(String s) {
		super("Product with reference #" + s + " was not found.");
	}
}
