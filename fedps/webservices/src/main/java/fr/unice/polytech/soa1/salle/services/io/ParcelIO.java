package fr.unice.polytech.soa1.salle.services.io;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.*;
import fr.unice.polytech.soa1.salle.business.model.units.Currency;
import fr.unice.polytech.soa1.salle.business.model.units.UnitSize;
import fr.unice.polytech.soa1.salle.business.model.units.UnitWeight;
import fr.unice.polytech.soa1.salle.services.customer.inputs.BadJobFault;
import fr.unice.polytech.soa1.salle.services.customer.inputs.CustomerInput;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;
import fr.unice.polytech.soa1.salle.services.customer.outputs.Processed;

import javax.xml.bind.annotation.*;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;
import java.util.Random;

@XmlType(name = "parcel",
        propOrder = { "id", "status", "sender", "senderEmail", "cardNumber", "receiver", "from", "to", "shipping",
                "pickup", "eta", "cost", "currency", "width", "height", "depth", "unitSize", "weight", "unitWeight" })
@XmlRootElement(name = "parcel")
public class ParcelIO extends CustomerInput {

    private String id;
    private String cardNumber;
    private String status;
    private String sender;
    private String senderEmail;
    private String receiver;
    private AddressIO from;
    private AddressIO to;
    private String shipping;
    private Long pickup;
    private Long eta;
    private Double cost;
    private String currency;
    private Double width;
    private Double height;
    private Double depth;
    private String unitSize;
    private Double weight;
    private String unitWeight;

    public ParcelIO() {}

    public ParcelIO(Parcel parcel)
    {
        this.id = parcel.getId();
        this.status = parcel.getStatus().toString();
        this.sender = parcel.getTransportInformation().getSender();
        this.senderEmail = parcel.getTransportInformation().getSenderEmail();
        this.receiver = parcel.getTransportInformation().getReceiver();
        this.from = new AddressIO(parcel.getTransportInformation().getFromAddress());
        this.to = new AddressIO(parcel.getTransportInformation().getToAddress());
        this.shipping = parcel.getTransportInformation().getShipping().toString();
        this.pickup = parcel.getTransportInformation().getPickup().getTime();
        this.eta = parcel.getTransportInformation().getEta().getTime();
        this.cost = parcel.getTransportInformation().getCost();
        this.currency = parcel.getTransportInformation().getCurrency().toString();
        this.width = parcel.getTransportInformation().getWidth();
        this.height = parcel.getTransportInformation().getHeight();
        this.depth = parcel.getTransportInformation().getDepth();
        this.unitSize = parcel.getTransportInformation().getUnitSize().toString();
        this.weight = parcel.getTransportInformation().getWeight();
        this.unitWeight = parcel.getTransportInformation().getUnitWeight().toString();
    }

    // *******
    // Methods
    // *******

    @Override
    protected JobResult run(DataAccessObject dao) throws BadJobFault
    {
        Parcel parcel = new Parcel();
        TransportInformation transportInformation = new TransportInformation();

        // %% Process payment using cardNumber %% //

        Address fromAddress = new Address();
        fromAddress.setStreetNb(from.getStreetNb());
        fromAddress.setStreetName(from.getStreetName());
        fromAddress.setZipCode(from.getZipCode());
        fromAddress.setCountryCode(from.getCountryCode());
        fromAddress.fillCityWithZipCode();
        Address toAddress = new Address();
        toAddress.setStreetNb(to.getStreetNb());
        toAddress.setStreetName(to.getStreetName());
        toAddress.setZipCode(to.getZipCode());
        toAddress.setCountryCode(to.getCountryCode());
        toAddress.fillCityWithZipCode();

        transportInformation.setSender(sender);
        transportInformation.setSenderEmail(senderEmail);
        transportInformation.setReceiver(receiver);
        transportInformation.setFromAddress(fromAddress);
        transportInformation.setToAddress(toAddress);
        transportInformation.setPickup(new Date(pickup));
        transportInformation.setShipping(Shipping.valueOf(shipping));
        transportInformation.setCurrency(Currency.valueOf(currency));
        transportInformation.setWidth(width);
        transportInformation.setHeight(height);
        transportInformation.setDepth(depth);
        transportInformation.setUnitSize(UnitSize.valueOf(unitSize));
        transportInformation.setWeight(weight);
        transportInformation.setUnitWeight(UnitWeight.valueOf(unitWeight));
        transportInformation.computeETA();

        parcel.setTransportInformation(transportInformation);
        parcel.setStatus(ParcelStatus.AWAITING_PICK_UP);

        dao.getParcels().add(parcel);

        return new Processed(parcel);
    }

    @Override
    protected void check(DataAccessObject dao) throws BadJobFault
    {
        if (sender == null || senderEmail == null || receiver == null || cardNumber == null
                || !from.isOkForParcel() || !to.isOkForParcel() || shipping == null
                || currency == null || width == null || height == null || depth == null
                || unitSize == null || weight == null || unitWeight == null)
        {
            throw new BadJobFault("Null data provided!");
        }

        if (width <= 0 || height <= 0 || depth <= 0 || weight <= 0)
        {
            throw new BadJobFault("Bad dimensions!");
        }

        if (new Date(pickup).before(new Date()))
        {
            throw new BadJobFault("The pickup date must be after now.");
        }

        try
        {
            Shipping.valueOf(shipping);
        }
        catch (IllegalArgumentException e)
        {
            throw new BadJobFault("Non-existing shipping provided!");
        }

        try
        {
            Currency.valueOf(currency);
        }
        catch (IllegalArgumentException e)
        {
            throw new BadJobFault("Non-existing currency provided!");
        }

        try
        {
            UnitSize.valueOf(unitSize);
        }
        catch (IllegalArgumentException e)
        {
            throw new BadJobFault("Non-existing unit size provided!");
        }

        try
        {
            UnitWeight.valueOf(unitWeight);
        }
        catch (IllegalArgumentException e)
        {
            throw new BadJobFault("Non-existing unit weight provided!");
        }
    }

    // ***************
    // Getter & setter
    // ***************

    @XmlAttribute
    @XmlID
    public String getId()        { return id; }
    public void setId(String id) { this.id = id; }

    @XmlElement
    public String getCardNumber()                { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    @XmlElement
    public String getStatus()            { return status; }
    public void setStatus(String status) { this.status = status; }

    @XmlElement
    public String getSender()            { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    @XmlElement
    public String getSenderEmail()                 { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    @XmlElement
    public String getReceiver()              { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    @XmlElement
    public AddressIO getFrom()          { return from; }
    public void setFrom(AddressIO from) { this.from = from; }

    @XmlElement
    public AddressIO getTo()        { return to; }
    public void setTo(AddressIO to) { this.to = to; }

    @XmlElement
    public String getShipping()              { return shipping; }
    public void setShipping(String shipping) { this.shipping = shipping; }

    @XmlElement
    public Long getPickup()            { return pickup; }
    public void setPickup(Long pickup) { this.pickup = pickup; }

    @XmlElement
    public Long getEta()         { return eta; }
    public void setEta(Long eta) { this.eta = eta; }

    @XmlElement
    public Double getCost()          { return cost; }
    public void setCost(Double cost) { this.cost = cost; }

    @XmlElement
    public String getCurrency()              { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    @XmlElement
    public Double getWidth()           { return width; }
    public void setWidth(Double width) { this.width = width; }

    @XmlElement
    public Double getHeight()            { return height; }
    public void setHeight(Double height) { this.height = height; }

    @XmlElement
    public Double getDepth()           { return depth; }
    public void setDepth(Double depth) { this.depth = depth; }

    @XmlElement
    public String getUnitSize()              { return unitSize; }
    public void setUnitSize(String unitSize) { this.unitSize = unitSize; }

    @XmlElement
    public Double getWeight()            { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    @XmlElement
    public String getUnitWeight()                { return unitWeight; }
    public void setUnitWeight(String unitWeight) { this.unitWeight = unitWeight; }

}
