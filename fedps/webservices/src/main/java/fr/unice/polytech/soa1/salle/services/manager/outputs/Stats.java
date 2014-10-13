package fr.unice.polytech.soa1.salle.services.manager.outputs;

import fr.unice.polytech.soa1.salle.business.model.ParcelStatus;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

@XmlRootElement
public class Stats {

    private Map<ParcelStatus, Integer> nbOfEach;

    public Stats()
    {
        this.nbOfEach = new HashMap<>();
    }

    @XmlElement
    public Map<ParcelStatus, Integer> getNbOfEach()               { return nbOfEach; }
    public void setNbOfEach(Map<ParcelStatus, Integer> aNbOfEach) { this.nbOfEach = aNbOfEach; }

}
