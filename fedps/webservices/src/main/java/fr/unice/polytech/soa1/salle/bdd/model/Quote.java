package fr.unice.polytech.soa1.salle.bdd.model;

import java.util.Calendar;
import java.util.Date;

/**
 * Model of a quote.
 *
 * @author victorsalle
 */
public class Quote {

    private static Integer INSTANCE_COUNTER = 0;
    private static Integer LIFETIME = 5;

    private String id;
    private Date created;
    private TransportInformation transportInformation;

    public Quote()
    {
        this.id = INSTANCE_COUNTER.toString();
        INSTANCE_COUNTER++;
        this.created = new Date();
    }

    public Quote(String anId, TransportInformation aTransportInformation)
    {
        this.id = anId;
        this.created = new Date();
        this.transportInformation = aTransportInformation;
    }

    // *******
    // Methods
    // *******

    public boolean isTooOldToBeConsidered()
    {
        Calendar c = Calendar.getInstance();
        c.setTime(created);
        c.add(Calendar.DATE, LIFETIME);
        return c.before(new Date());
    }

    // *****************
    // Getters & setters
    // *****************

    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    public TransportInformation getTransportInformation()                           { return transportInformation; }
    public void setTransportInformation(TransportInformation aTransportInformation) { this.transportInformation = aTransportInformation; }

}
