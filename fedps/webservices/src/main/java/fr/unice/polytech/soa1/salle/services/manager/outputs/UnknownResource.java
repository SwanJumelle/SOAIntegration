package fr.unice.polytech.soa1.salle.services.manager.outputs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UnknownResource {

    private String type;
    private String key;

    public UnknownResource() {}

    public UnknownResource(String aType, String aKey)
    {
        this.type = aType;
        this.key = aKey;
    }

    @XmlElement
    public String getType()           { return type; }
    public void setType(String aType) { this.type = aType; }

    @XmlElement
    public String getKey()          { return key; }
    public void setKey(String aKey) { this.key = aKey; }

}
