package fr.unice.polytech.soa1.misterdiscount.bdd;

import java.sql.Date;
import java.util.List;

import fr.unice.polytech.soa1.misterdiscount.business.Address;
import fr.unice.polytech.soa1.misterdiscount.business.Customer;

public class Order {

	private Integer id;
	private Customer customer;
	private Address address;
	private List<OrderItem> items;
	private Date date;
	private String carrier;
	private String trackingNb;
	
	public Integer getId() { return id; }
	public void setId(Integer id) { this.id = id; }
	
	public Customer getCustomer() { return customer; }
	public void setCustomer(Customer customer) { this.customer = customer; }
	
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
	
	public List<OrderItem> getItems() { return items; }
	public void setItems(List<OrderItem> items) { this.items = items; }
	
	public Date getDate() { return date; }
	public void setDate(Date date) { this.date = date; }
	
	public String getCarrier() { return carrier; }
	public void setCarrier(String carrier) { this.carrier = carrier; }
	
	public String getTrackingNb() { return trackingNb; }
	public void setTrackingNb(String trackingNb) { this.trackingNb = trackingNb; }
	
}
