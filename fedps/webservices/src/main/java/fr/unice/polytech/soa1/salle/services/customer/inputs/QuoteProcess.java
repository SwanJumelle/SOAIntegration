package fr.unice.polytech.soa1.salle.services.customer.inputs;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.Address;
import fr.unice.polytech.soa1.salle.business.model.Quote;
import fr.unice.polytech.soa1.salle.business.model.Shipping;
import fr.unice.polytech.soa1.salle.business.model.TransportInformation;
import fr.unice.polytech.soa1.salle.business.model.units.Currency;
import fr.unice.polytech.soa1.salle.business.model.units.UnitSize;
import fr.unice.polytech.soa1.salle.business.model.units.UnitWeight;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;
import fr.unice.polytech.soa1.salle.services.customer.outputs.QuoteResult;
import fr.unice.polytech.soa1.salle.services.io.AddressIO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;

@XmlType(name = "quote")
public class QuoteProcess extends CustomerInput {

    private AddressIO from;
    private AddressIO to;
    private Long pickup;
    private String shipping;
    private String currency;
    private Double width;
    private Double height;
    private Double depth;
    private String unitSize;
    private Double weight;
    private String unitWeight;

    public QuoteProcess() {}

    // *******
    // Methods
    // *******

    @Override
    protected JobResult run(DataAccessObject dao) throws BadJobFault
    {
        Quote quote = new Quote();
        TransportInformation transportInformation = new TransportInformation();

        Address fromAddress = new Address();
        fromAddress.setZipCode(from.getZipCode());
        fromAddress.setCountryCode(from.getCountryCode());
        fromAddress.fillCityWithZipCode();
        Address toAddress = new Address();
        toAddress.setZipCode(to.getZipCode());
        toAddress.setCountryCode(to.getCountryCode());
        toAddress.fillCityWithZipCode();

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

        // Compute a fake price
        double fakeCost = (width + height + depth + weight) / 5;
        transportInformation.setCost(fakeCost);

        quote.setTransportInformation(transportInformation);

        dao.getQuotes().add(quote);

        return new QuoteResult(quote);
    }

    @Override
    protected void check(DataAccessObject dao) throws BadJobFault
    {
        if ( !from.isOkForQuote() || !to.isOkForQuote() || shipping == null || currency == null
                || width == null || height == null || depth == null || unitSize == null
                || weight == null || unitWeight == null)
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

    // *****************
    // Getters & setters
    // *****************

    @XmlElement
    public AddressIO getFrom()          { return from; }
    public void setFrom(AddressIO from) { this.from = from; }

    @XmlElement
    public AddressIO getTo()        { return to; }
    public void setTo(AddressIO to) { this.to = to; }

    @XmlElement
    public Long getPickup()            { return pickup; }
    public void setPickup(Long pickup) { this.pickup = pickup; }

    @XmlElement
    public String getShipping()              { return shipping; }
    public void setShipping(String shipping) { this.shipping = shipping; }

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