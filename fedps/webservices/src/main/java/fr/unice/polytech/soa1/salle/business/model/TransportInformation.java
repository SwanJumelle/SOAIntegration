package fr.unice.polytech.soa1.salle.business.model;

import fr.unice.polytech.soa1.salle.business.model.units.Currency;
import fr.unice.polytech.soa1.salle.business.model.units.UnitSize;
import fr.unice.polytech.soa1.salle.business.model.units.UnitWeight;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlID;
import javax.xml.bind.annotation.XmlType;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

/**
 * Model of a transport information.
 *
 * @author victorsalle
 */
public class TransportInformation {

    private static Integer INSTANCE_COUNTER = 0;

    private String id;
    private String sender;
    private String senderEmail;
    private String receiver;
    private Address fromAddress;
    private Address toAddress;
    private Date pickup;
    private Date eta;
    private Shipping shipping;
    private Double cost;
    private Currency currency;
    private Double width;
    private Double height;
    private Double depth;
    private UnitSize unitSize;
    private Double weight;
    private UnitWeight unitWeight;

    public TransportInformation()
    {
        this.id = INSTANCE_COUNTER.toString();
        INSTANCE_COUNTER++;
    }

    public TransportInformation(String aSender, String aSenderEmail, String aReceiver, Address aFromAddress,
                                Address aToAddress, Date aPickup, Date anEta, Shipping aShipping, Double aCost,
                                Currency aCurrency, Double aWidth, Double aHeight, Double aDepth, UnitSize anUnitSize,
                                Double aWeight, UnitWeight anUnitWeight)
    {
        this();
        this.sender = aSender;
        this.senderEmail = aSenderEmail;
        this.receiver = aReceiver;
        this.fromAddress = aFromAddress;
        this.toAddress = aToAddress;
        this.pickup = aPickup;
        this.eta = anEta;
        this.shipping = aShipping;
        this.cost = aCost;
        this.currency = aCurrency;
        this.width = aWidth;
        this.height = aHeight;
        this.depth = aDepth;
        this.unitSize = anUnitSize;
        this.weight = aWeight;
        this.unitWeight = anUnitWeight;
    }

    // *******
    // Methods
    // *******

    public void computeETA()
    {
        // Compute fake ETA
        int minDays = 1;
        int maxDays = (shipping.equals(Shipping.EXPRESS)) ? 4 : 8;
        Calendar c = Calendar.getInstance();
        c.setTime(pickup);
        c.add(Calendar.DATE, new Random().nextInt(maxDays - minDays + 1) + minDays);
        eta = c.getTime();
    }

    // ***************
    // Getter & setter
    // ***************

    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    public String getSender()            { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    public String getSenderEmail()                  { return senderEmail; }
    public void setSenderEmail(String aSenderEmail) { this.senderEmail = aSenderEmail; }

    public String getReceiver()              { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    public Address getFromAddress()                 { return fromAddress; }
    public void setFromAddress(Address fromAddress) { this.fromAddress = fromAddress; }

    public Address getToAddress()               { return toAddress; }
    public void setToAddress(Address toAddress) { this.toAddress = toAddress; }

    public Date getPickup()            { return pickup; }
    public void setPickup(Date pickup) { this.pickup = pickup; }

    public Date getEta()         { return eta; }
    public void setEta(Date eta) { this.eta = eta; }

    public Shipping getShipping()              { return shipping; }
    public void setShipping(Shipping shipping) { this.shipping = shipping; }

    public Double getCost()          { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    public Currency getCurrency()               { return currency; }
    public void setCurrency(Currency aCurrency) { this.currency = aCurrency; }

    public Double getWidth()           { return width; }
    public void setWidth(Double width) { this.width = width; }

    public Double getHeight()            { return height; }
    public void setHeight(Double height) { this.height = height; }

    public Double getDepth()           { return depth; }
    public void setDepth(Double depth) { this.depth = depth; }

    public UnitSize getUnitSize()                { return unitSize; }
    public void setUnitSize(UnitSize anUnitSize) { this.unitSize = anUnitSize; }

    public Double getWeight()            { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public UnitWeight getUnitWeight()                  { return unitWeight; }
    public void setUnitWeight(UnitWeight anUnitWeight) { this.unitWeight = anUnitWeight; }

}
