package fr.unice.polytech.soa1.salle.services.customer.inputs;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;
import fr.unice.polytech.soa1.salle.services.io.ParcelIO;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ FollowParcels.class, QuoteProcess.class, ParcelFromQuote.class, ParcelIO.class })
public abstract class CustomerInput {

    public final JobResult process(DataAccessObject dao) throws BadJobFault
    {
        check(dao);
        return run(dao);
    }

    abstract protected JobResult run(DataAccessObject dao) throws BadJobFault;

    abstract protected void check(DataAccessObject dao) throws BadJobFault;

}
