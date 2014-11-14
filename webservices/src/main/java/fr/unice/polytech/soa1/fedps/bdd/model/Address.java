package fr.unice.polytech.soa1.fedps.bdd.model;

import java.util.Random;

/**
 * Model of an address.
 *
 * @author victorsalle
 */
public class Address {

    private static Integer INSTANCE_COUNTER = 0;

    private String id;
    private String streetNb;
    private String streetName;
    private String zipCode;
    private String cityName;
    private String countryCode;

    public Address()
    {
        this.id = INSTANCE_COUNTER.toString();
        INSTANCE_COUNTER++;
    }

    public Address(String aStreetNb, String aStreetName, String aZipCode, String aCityName, String aCountryCode)
    {
        this();
        this.streetNb = aStreetNb;
        this.streetName = aStreetName;
        this.zipCode = aZipCode;
        this.cityName = aCityName;
        this.countryCode = aCountryCode;
    }

    // *******
    // Methods
    // *******

    public void fillCityWithZipCode()
    {
        String[] fakeCitiesName = { "Pitt Meadows", "San Lorenzo", "Patna", "Güssing", "Doel", "Jandrain-Jandrenouille",
                "Melton Mowbray", "Frankfort", "Galzignano Terme", "Juazeiro do Norte", "Palmerston", "Maidstone",
                "San Leucio del Sannio", "Reggio nell'Emilia", "Hannut", "Idar-Oberstei", "Surbo", "Soye",
                "Crowsnest Pass", "Fort Good Hope", "Pembroke", "Louisville", "Dunoon", "Georgia", "Vegreville",
                "Chelsea", "Buxton", "Sète", "Morhet", "Denver", "San Juan de Dios", "Spresianoi", "Impe", "Oyen" };

        int i = new Random().nextInt(fakeCitiesName.length);
        cityName = fakeCitiesName[i];
    }

    // *****************
    // Getters & setters
    // *****************

    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    public String getStreetNb()               { return streetNb; }
    public void setStreetNb(String aStreetNb) { this.streetNb = aStreetNb; }

    public String getStreetName()                 { return streetName; }
    public void setStreetName(String aStreetName) { this.streetName = aStreetName; }

    public String getZipCode()              { return zipCode; }
    public void setZipCode(String aZipCode) { this.zipCode = aZipCode; }

    public String getCityName()               { return cityName; }
    public void setCityName(String aCityName) { this.cityName = aCityName; }

    public String getCountryCode()                  { return countryCode; }
    public void setCountryCode(String aCountryCode) { this.countryCode = aCountryCode; }

}
