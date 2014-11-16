package fr.unice.polytech.soa1.misterdiscount.business;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "customer")
public class Customer {
	
	private String firstName;
	private String lastName;
	private String email;
	private Address address;
	
	@XmlElement(name="firstName", required=true)
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	@XmlElement(name="lastName", required=true)
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	@XmlElement(name="email")
	public String getEmail() { return email; }
	public void setEmail(String email) { this.email = email; }
	
	@XmlElement(name="address", required=true)
	public Address getAddress() { return address; }
	public void setAddress(Address address) { this.address = address; }
	
}
