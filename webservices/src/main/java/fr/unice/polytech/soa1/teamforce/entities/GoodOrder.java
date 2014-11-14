package fr.unice.polytech.soa1.teamforce.entities;

public class GoodOrder {
	
	private int quantity;
	private Good good;
	
	public GoodOrder(int quantity, Good good) {
		super();
		this.quantity = quantity;
		this.good = good;
	}
	
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}

}
