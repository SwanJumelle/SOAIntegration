package fr.unice.polytech.soa1.teamforce.entities;

import java.util.List;

public class Company {

	private String id;
	private String Name;
	private Address address;
	private List<Catalogue> catalogs;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public Address getAddress() {
		return address;
	}
	public void setAddress(Address address) {
		this.address = address;
	}
	public List<Catalogue> getCatalogs() {
		return catalogs;
	}
	public void setCatalogs(List<Catalogue> catalogs) {
		this.catalogs = catalogs;
	}
}
