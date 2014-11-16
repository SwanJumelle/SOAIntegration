package fr.unice.polytech.soa1.misterdiscount.business;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "orderInformation")
public class OrderInformation {

	private String status;
	private Receipt receipt;
	private DeliveryTracking deliveryTracking;
	
	@XmlElement(name="status")
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }
	
	@XmlElement(name="receipt")
	public Receipt getReceipt() { return receipt; }
	public void setReceipt(Receipt receipt) { this.receipt = receipt; }
	
	@XmlElement(name="deliveryTracking")
	public DeliveryTracking getDeliveryTracking() { return deliveryTracking; }
	public void setDeliveryTracking(DeliveryTracking deliveryTracking) { this.deliveryTracking = deliveryTracking; }
	
}
