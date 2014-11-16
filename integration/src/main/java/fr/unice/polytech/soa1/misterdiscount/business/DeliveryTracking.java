package fr.unice.polytech.soa1.misterdiscount.business;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(name = "deliveryTracking")
public class DeliveryTracking {

	private String carrier;
	private String trackingNb;
	private String url;
	
	@XmlElement(name="carrier")
	public String getCarrier() { return carrier; }
	public void setCarrier(String carrier) { this.carrier = carrier; }
	
	@XmlElement(name="trackingNb")
	public String getTrackingNb() { return trackingNb; }
	public void setTrackingNb(String trackingNb) { this.trackingNb = trackingNb; }
	
	@XmlElement(name="url")
	public String getUrl() { return url; }
	public void setUrl(String url) { this.url = url; }
	
}
