package fr.unice.polytech.soa1.salle.services.customer.inputs;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.Parcel;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;
import fr.unice.polytech.soa1.salle.services.customer.outputs.ListOfFollow;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;
import java.util.Optional;

@XmlType(name = "follow_parcels")
public class FollowParcels extends CustomerInput {

    private List<String> parcelIds;

    public FollowParcels() {}

    // *******
    // Methods
    // *******

    @Override
    protected JobResult run(DataAccessObject dao) throws BadJobFault
    {
        ListOfFollow result = new ListOfFollow();
        for (String id : parcelIds)
        {
            Optional<Parcel> parcel = dao.findParcelById(id);
            if (!parcel.isPresent())
            {
                throw new BadJobFault(id);
            }
            result.addData(parcel.get().getStatus().getLabel(), parcel.get().getTransportInformation().getEta().getTime());
        }
        return result;
    }

    @Override
    protected void check(DataAccessObject dao) throws BadJobFault
    {
        // nothing to check
    }

    // ***************
    // Getter & setter
    // ***************

    @XmlElementWrapper(name = "parcels")
    @XmlElement(name = "identifier")
    public List<String> getParcelIds()                   { return parcelIds; }
    public void setParcelIds(List<String> someParcelIds) { this.parcelIds = someParcelIds; }

}
