package fr.unice.polytech.soa1.salle.services.business;

public class RoundStepIO {

    private String parcelId;
    private KindOfRoundStep kind;
    private AddressIO address;

    public RoundStepIO() {}

    public RoundStepIO(String parcelId, KindOfRoundStep kind, AddressIO address)
    {
        this.parcelId = parcelId;
        this.kind = kind;
        this.address = address;
    }

    public String getParcelId()              { return parcelId; }
    public void setParcelId(String parcelId) { this.parcelId = parcelId; }

    public KindOfRoundStep getKind()          { return kind; }
    public void setKind(KindOfRoundStep kind) { this.kind = kind; }

    public AddressIO getAddress()             { return address; }
    public void setAddress(AddressIO address) { this.address = address; }

}
