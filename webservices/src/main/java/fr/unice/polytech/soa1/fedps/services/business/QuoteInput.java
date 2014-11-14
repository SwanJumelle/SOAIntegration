package fr.unice.polytech.soa1.fedps.services.business;

import fr.unice.polytech.soa1.fedps.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.fedps.bdd.model.Address;
import fr.unice.polytech.soa1.fedps.bdd.model.Quote;
import fr.unice.polytech.soa1.fedps.bdd.model.Shipping;
import fr.unice.polytech.soa1.fedps.bdd.model.TransportInformation;
import fr.unice.polytech.soa1.fedps.bdd.model.units.Currency;
import fr.unice.polytech.soa1.fedps.bdd.model.units.UnitSize;
import fr.unice.polytech.soa1.fedps.bdd.model.units.UnitWeight;
import fr.unice.polytech.soa1.fedps.services.order.input.OrderInput;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name = "quote")
public class QuoteInput extends OrderInput {

    private String fromZipCode;
    private String fromCountryCode;
    private String toZipCode;
    private String toCountryCode;
    private Long pickup;
    private Shipping shipping;
    private Currency currency;
    private Double width;
    private Double height;
    private Double depth;
    private UnitSize unitSize;
    private Double weight;
    private UnitWeight unitWeight;

    public QuoteInput() {}

    // ******
    // Method
    // ******

    @Override
    public void check(DataAccessObject dao) throws BadJobFault
    {
        if ( fromZipCode== null || fromCountryCode == null || toZipCode== null || toCountryCode == null
                || shipping == null || currency == null || width == null || height == null || depth == null
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
    }

    @Override
    public JobResult run(DataAccessObject dao) throws BadJobFault
    {
        Quote quote = new Quote();
        TransportInformation transportInformation = new TransportInformation();

        Address fromAddress = new Address();
        fromAddress.setZipCode(fromZipCode);
        fromAddress.setCountryCode(fromCountryCode);
        fromAddress.fillCityWithZipCode();
        Address toAddress = new Address();
        toAddress.setZipCode(toZipCode);
        toAddress.setCountryCode(toCountryCode);
        toAddress.fillCityWithZipCode();

        transportInformation.setFromAddress(fromAddress);
        transportInformation.setToAddress(toAddress);
        transportInformation.setPickup(new Date(pickup));
        transportInformation.setShipping(shipping);
        transportInformation.setCurrency(currency);
        transportInformation.setWidth(width);
        transportInformation.setHeight(height);
        transportInformation.setDepth(depth);
        transportInformation.setUnitSize(unitSize);
        transportInformation.setWeight(weight);
        transportInformation.setUnitWeight(unitWeight);
        transportInformation.computeETA();

        double fakeCost = (width + height + depth + weight) / 5;
        transportInformation.setCost(fakeCost);

        quote.setTransportInformation(transportInformation);

        dao.getQuotes().add(quote);

        return new QuoteOutput(quote);
    }

    // *****************
    // Getters & setters
    // *****************

    @XmlElement
    public String getFromZipCode()             { return fromZipCode; }
    public void setFromZipCode(String zipCode) { this.fromZipCode = zipCode; }

    @XmlElement
    public String getFromCountryCode()                 { return fromCountryCode; }
    public void setFromCountryCode(String countryCode) { this.fromCountryCode = countryCode; }

    @XmlElement
    public String getToZipCode()             { return toZipCode; }
    public void setToZipCode(String zipCode) { this.toZipCode = zipCode; }

    @XmlElement
    public String getToCountryCode()                 { return toCountryCode; }
    public void setToCountryCode(String countryCode) { this.toCountryCode = countryCode; }

    @XmlElement
    public Long getPickup()            { return pickup; }
    public void setPickup(Long pickup) { this.pickup = pickup; }

    @XmlElement
    public Shipping getShipping()              { return shipping; }
    public void setShipping(Shipping shipping) { this.shipping = shipping; }

    @XmlElement
    public Currency getCurrency()              { return currency; }
    public void setCurrency(Currency currency) { this.currency = currency; }

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
    public UnitSize getUnitSize()              { return unitSize; }
    public void setUnitSize(UnitSize unitSize) { this.unitSize = unitSize; }

    @XmlElement
    public Double getWeight()            { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    @XmlElement
    public UnitWeight getUnitWeight()                { return unitWeight; }
    public void setUnitWeight(UnitWeight unitWeight) { this.unitWeight = unitWeight; }

}
