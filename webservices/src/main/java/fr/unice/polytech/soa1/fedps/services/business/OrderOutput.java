package fr.unice.polytech.soa1.fedps.services.business;

import fr.unice.polytech.soa1.fedps.bdd.model.Parcel;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = { "id", "eta"})
@XmlRootElement(name = "order")
public class OrderOutput extends JobResult {

    private String id;
    private Long eta;

    public OrderOutput() {}

    public OrderOutput(Parcel parcel)
    {
        this.id = parcel.getId();
        this.eta = parcel.getTransportInformation().getEta().getTime();
    }

    @XmlElement
    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    @XmlElement
    public Long getEta()           { return eta; }
    public void setEta(Long anEta) { this.eta = anEta; }

}
