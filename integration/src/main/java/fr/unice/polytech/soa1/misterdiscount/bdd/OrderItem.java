package fr.unice.polytech.soa1.misterdiscount.bdd;

public class OrderItem {
	
	private String productRef;
	private int quantity;
	private double unitPrice;
		
	public String getProductRef() { return productRef; }
	public void setProductRef(String productRef) { this.productRef = productRef; }
	
	public int getQuantity() { return quantity; }
	public void setQuantity(int quantity) { this.quantity = quantity; }
	
	public double getUnitPrice() { return unitPrice; }
	public void setUnitPrice(double unitPrice) { this.unitPrice = unitPrice; }
	
}
