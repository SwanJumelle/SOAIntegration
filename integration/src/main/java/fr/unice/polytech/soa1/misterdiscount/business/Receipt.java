package fr.unice.polytech.soa1.misterdiscount.business;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "receipt")
public class Receipt {

	private String orderId;
	private Long eta;
	private List<Item> items;

	@XmlElement(name="orderId")
	public String getOrderId() { return orderId; }
	public void setOrderId(String orderId) { this.orderId = orderId; }

	@XmlElement(name="eta")
	public Long getEta() { return eta; }
	public void setEta(Long eta) { this.eta = eta; }

	@XmlElementWrapper(name="items", required=true)
	@XmlElement(name="item", required=true)
	public List<Item> getItems() { return items; }
	public void setItems(List<Item> items) { this.items = items; }
		
}
