package fr.unice.polytech.soa1.misterdiscount.business;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "item")
public class Item {
	
	private String productRef;
	private Integer quantity;
	private Double unitPrice;
	
	@XmlElement(name="productRef", required=true)
	public String getProductRef() { return productRef; }
	public void setProductRef(String productRef) { this.productRef = productRef; }
	
	@XmlElement(name="quantity", required=true)
	public Integer getQuantity() { return quantity; }
	public void setQuantity(Integer quantity) { this.quantity = quantity; }
	
	@XmlElement(name="unitPrice", required=true)
	public Double getUnitPrice() { return unitPrice; }
	public void setUnitPrice(Double unitPrice) { this.unitPrice = unitPrice; }
	
}
