package fr.unice.polytech.soa1.salle.services.driver.output;

import fr.unice.polytech.soa1.salle.services.io.AddressIO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Step {

    private String parcelId;
    private String kind;
    private AddressIO address;

    public Step() {}

    public Step(String parcelId, KindOfStep kind, AddressIO address)
    {
        this.parcelId = parcelId;
        this.kind = kind.toString();
        this.address = address;
    }

    @XmlElement
    public String getParcelId()              { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }

    @XmlElement
    public String getKind()          { return kind; }
    public void setKind(String kind) { this.kind = kind; }

    @XmlElement
    public AddressIO getAddress()             { return address; }
    public void setAddress(AddressIO address) { this.address = address; }

}
