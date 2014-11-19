package fr.unice.polytech.soa1.misterdiscount.business;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "orderInformation")
public class OrderInformation {

	private String status;
	private Integer orderId;
	private Double totalCost;
	private Long eta;
	private List<ItemWithPrice> items;
	private Address shippingAddress;
	private DeliveryTracking deliveryTracking;
	
	@XmlElement(name="status")
	public String getStatus() { return status; }
	public void setStatus(String status) { this.status = status; }

	@XmlElement(name="orderId")
	public Integer getOrderId() { return orderId; }
	public void setOrderId(Integer orderId) { this.orderId = orderId; }
	
	@XmlElement(name="totalCost")
	public Double getTotalCost() { return totalCost; }
	public void setTotalCost(Double totalCost) { this.totalCost = totalCost; }

	@XmlElement(name="eta")
	public Long getEta() { return eta; }
	public void setEta(Long eta) { this.eta = eta; }

	@XmlElementWrapper(name="items", required=true)
	@XmlElement(name="item", required=true)
	public List<ItemWithPrice> getItems() { return items; }
	public void setItems(List<ItemWithPrice> items) { this.items = items; }
	
	@XmlElement(name="shippingAddress")
	public Address getShippingAddress() { return shippingAddress; }
	public void setShippingAddress(Address shippingAddress) { this.shippingAddress = shippingAddress; }
	
	@XmlElement(name="deliveryTracking")
	public DeliveryTracking getDeliveryTracking() { return deliveryTracking; }
	public void setDeliveryTracking(DeliveryTracking deliveryTracking) { this.deliveryTracking = deliveryTracking; }
	
}
