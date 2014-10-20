package fr.unice.polytech.soa1.entities;

public class Address {

	private String line1;
	private String line2;
	private String zipCode;
	private String city;
	
	public Address(String line1,String line2, String zipCode, String city){
		this.line1=line1;
		this.line2=line2;
		this.zipCode=zipCode;
		this.city=city;
	}
	
	public String toString(){
		return line1+"\n"+line2+" "+zipCode+" "+ city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getLine1() {
		return line1;
	}

	public void setLine1(String line1) {
		this.line1 = line1;
	}

	public String getLine2() {
		return line2;
	}

	public void setLine2(String line2) {
		this.line2 = line2;
	}
}
