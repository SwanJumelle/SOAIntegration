package fr.unice.polytech.soa1.entities;

import java.util.List;

public class Customer extends Person {
	
	private Address invoiceAddresses;
	private Address deliveryAddresses;
	private List<Order> orders;

	public Customer(String id, String lastName, String firstName, String phoneNumber, String email, 
			Address invoiceAddresses, Address deliveryAddresses, List<Order> orders) {
		super(id, lastName, firstName, phoneNumber, email);
		this.invoiceAddresses = invoiceAddresses;
		this.deliveryAddresses = deliveryAddresses;
		this.orders = orders;
	}
	
	public Address getInvoiceAddresses() {
		return invoiceAddresses;
	}
	public void setInvoiceAddresses(Address invoiceAddresses) {
		this.invoiceAddresses = invoiceAddresses;
	}
	public Address getDeliveryAddresses() {
		return deliveryAddresses;
	}
	public void setDeliveryAddresses(Address deliveryAddresses) {
		this.deliveryAddresses = deliveryAddresses;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
}
