package fr.unice.polytech.soa1.entities;

public class Person {
	
	private String id;
	private String lastName;
	private String firstName;
	private String phoneNumber;
	private String email;

	public Person(String id, String firstName, String lastName
			, String email,String phoneNumber) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.phoneNumber = phoneNumber;
		this.email = email;
	}

	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getName() {
		return firstName+" "+lastName;
	}
}
