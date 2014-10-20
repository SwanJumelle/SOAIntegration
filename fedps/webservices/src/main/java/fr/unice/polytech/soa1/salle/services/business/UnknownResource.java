package fr.unice.polytech.soa1.salle.services.business;

public class UnknownResource {

    private String type;
    private String key;

    public UnknownResource() {}

    public UnknownResource(String aType, String aKey)
    {
        this.type = aType;
        this.key = aKey;
    }

    public String getType()           { return type; }
    public void setType(String aType) { this.type = aType; }

    public String getKey()          { return key; }
    public void setKey(String aKey) { this.key = aKey; }

}
