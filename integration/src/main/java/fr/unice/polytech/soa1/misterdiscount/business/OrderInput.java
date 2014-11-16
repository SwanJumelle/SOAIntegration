package fr.unice.polytech.soa1.misterdiscount.business;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "orderInput")
public class OrderInput {

	private String customerId;
	private Long cardNb;
	private List<Item> items;
	private Address shippingAddress;
	
	@XmlElement(name="customerId", required=true)
	public String getCustomerId() { return customerId; }
	public void setCustomerId(String customerId) { this.customerId = customerId; }
	
	@XmlElement(name="cardNb", required=true)
	public Long getCardNb() { return cardNb; }
	public void setCardNb(Long cardNb) { this.cardNb = cardNb; }
	
	@XmlElementWrapper(name="items", required=true)
	@XmlElement(name="item", required=true)
	public List<Item> getItems() { return items; }
	public void setItems(List<Item> items) { this.items = items; }
	
	@XmlElement(name="shippingAddress")
	public Address getShippingAddress() { return shippingAddress; }
	public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }
	
}
