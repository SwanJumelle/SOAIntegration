package fr.unice.polytech.soa1.salle.services.manager.outputs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "parcels")
public class ListOfParcels {

    private List<String> parcels;

    public ListOfParcels()
    {
        this.parcels = new ArrayList<>();
    }

    @XmlElement(name = "links")
    public List<String> getParcels()                 { return parcels; }
    public void setParcels(List<String> someParcels) { this.parcels = someParcels; }

}
