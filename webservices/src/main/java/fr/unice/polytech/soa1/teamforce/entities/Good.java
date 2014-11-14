package fr.unice.polytech.soa1.teamforce.entities;

public class Good {

	private String id;
	private String priceString;
	private double price;
	private String categorie;
	
	public Good(String id, String priceString, String categorie) {
		super();
		this.id = id;
		this.priceString = priceString;
		this.categorie = categorie;
		this.price = this.getPrice();
	}
	
	public String getCategorie() {
		return categorie;
	}
	
	public void setCategorie(String categorie) {
		this.categorie = categorie;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public String getPriceString() {
		return priceString;
	}

	public void setPriceString(String priceString) {
		this.priceString = priceString;
	}

	public double getPrice() {
		String tab = priceString.replace("$","");
		price = Double.valueOf(tab);
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
		this.setPriceString("$"+price);
	}
	
	public String toString(){
		return id;
	}
}