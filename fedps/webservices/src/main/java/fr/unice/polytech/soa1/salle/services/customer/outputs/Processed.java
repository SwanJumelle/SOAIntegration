package fr.unice.polytech.soa1.salle.services.customer.outputs;

import fr.unice.polytech.soa1.salle.business.model.Parcel;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType
public class Processed extends JobResult {

    private String id;
    private Long eta;

    public Processed() {}

    public Processed(Parcel parcel)
    {
        this.id = parcel.getId();
        this.eta = parcel.getTransportInformation().getEta().getTime();
    }

    @XmlElement(name = "parcelId")
    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    @XmlElement
    public Long getEta()           { return eta; }
    public void setEta(Long anEta) { this.eta = anEta; }
}
