package fr.unice.polytech.soa1.misterdiscount.business;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "full_address")
public class AddressWithName {

	private String firstName;
	private String lastName;
	private String streetNb;
	private String streetName;
	private String zipCode;
	private String city;
	private String countryCode;
	
	@XmlElement(name="firstName", required=true)
	public String getFirstName() { return firstName; }
	public void setFirstName(String firstName) { this.firstName = firstName; }
	
	@XmlElement(name="lastName", required=true)
	public String getLastName() { return lastName; }
	public void setLastName(String lastName) { this.lastName = lastName; }
	
	@XmlElement(name="streetNb", required=true)
	public String getStreetNb() { return streetNb; }
	public void setStreetNb(String streetNb) { this.streetNb = streetNb; }
	
	@XmlElement(name="streetName", required=true)
	public String getStreetName() { return streetName; }
	public void setStreetName(String streetName) { this.streetName = streetName; }
	
	@XmlElement(name="zipCode", required=true)
	public String getZipCode() { return zipCode; }
	public void setZipCode(String zipCode) { this.zipCode = zipCode; }
	
	@XmlElement(name="city", required=true)
	public String getCity() { return city; }
	public void setCity(String city) { this.city = city; }
	
	@XmlElement(name="countryCode", required=true)
	public String getCountryCode() { return countryCode; }
	public void setCountryCode(String countryCode) { this.countryCode = countryCode; }
	
}