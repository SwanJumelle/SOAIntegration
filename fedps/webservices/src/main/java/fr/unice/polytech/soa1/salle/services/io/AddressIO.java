package fr.unice.polytech.soa1.salle.services.io;

import fr.unice.polytech.soa1.salle.business.model.Address;

import javax.xml.bind.annotation.*;


@XmlRootElement(name = "address")
@XmlType(propOrder = { "id", "streetNb", "streetName", "zipCode", "cityName", "countryCode" })
public class AddressIO {

    private String id;
    private String streetNb;
    private String streetName;
    private String zipCode;
    private String cityName;
    private String countryCode;

    public AddressIO() {}

    public AddressIO(Address address)
    {
        this.id = address.getId();
        this.streetNb = address.getStreetNb();
        this.streetName = address.getStreetName();
        this.zipCode = address.getZipCode();
        this.cityName = address.getCityName();
        this.countryCode = address.getCountryCode();
    }

    // *******
    // Methods
    // *******

    public boolean isOkForParcel()
    {
        return streetNb != null && streetName != null
                && zipCode != null && cityName == null && countryCode != null;
    }

    public boolean isOkForQuote()
    {
        return zipCode != null && countryCode != null && cityName == null;
    }

    public boolean isOkForParcelFromQuote()
    {
        return streetNb != null && streetName != null
                && zipCode == null && cityName == null && countryCode == null;
    }

    // *****************
    // Getters & setters
    // *****************

    @XmlAttribute
    @XmlID
    public String getId()        { return id; }
    public void setId(String id) { this.id = id; }

    @XmlElement
    public String getStreetNb()              { return streetNb; }
    public void setStreetNb(String streetNb) { this.streetNb = streetNb; }

    @XmlElement
    public String getStreetName()                { return streetName; }
    public void setStreetName(String streetName) { this.streetName = streetName; }

    @XmlElement
    public String getZipCode()             { return zipCode; }
    public void setZipCode(String zipCode) { this.zipCode = zipCode; }

    @XmlElement
    public String getCityName()              { return cityName; }
    public void setCityName(String cityName) { this.cityName = cityName; }

    @XmlElement
    public String getCountryCode()                 { return countryCode; }
    public void setCountryCode(String countryCode) { this.countryCode = countryCode; }

}
