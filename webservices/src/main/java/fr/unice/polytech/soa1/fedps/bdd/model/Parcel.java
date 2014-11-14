package fr.unice.polytech.soa1.fedps.bdd.model;

import java.util.Date;

/**
 * Model of a parcel.
 *
 * @author victorsalle
 */
public class Parcel {

    private static Integer INSTANCE_COUNTER = 0;

    private String id;
    private ParcelStatus status;
    private TransportInformation transportInformation;

    public Parcel()
    {
        this.id = INSTANCE_COUNTER.toString();
        INSTANCE_COUNTER++;
    }

    public Parcel(String anId, Quote aQuote, String aSender, String aSenderEmail, String aReceiver)
    {
        this.id = anId;
        this.status = ParcelStatus.STORAGE;
        this.transportInformation = aQuote.getTransportInformation();

        this.transportInformation.setSender(aSender);
        this.transportInformation.setSenderEmail(aSenderEmail);
        this.transportInformation.setReceiver(aReceiver);
    }

    public Parcel(String anId, ParcelStatus aStatus, TransportInformation aTransportInformation)
    {
        this.id = anId;
        this.status = aStatus;
        this.transportInformation = aTransportInformation;
    }

    public boolean isDelayed()
    {
        return status.before(ParcelStatus.DELIVERED) && transportInformation.getEta().before(new Date());
    }

    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    public ParcelStatus getStatus()                   { return status; }
    public void setStatus(ParcelStatus aParcelStatus) { this.status = aParcelStatus; }

    public TransportInformation getTransportInformation()                           { return transportInformation; }
    public void setTransportInformation(TransportInformation aTransportInformation) { this.transportInformation = aTransportInformation; }

    @Override
    public String toString()
    {
        return id + " - " + status.getLabel();
    }

}
