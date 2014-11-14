package fr.unice.polytech.soa1.fedps.services.order.input;

import fr.unice.polytech.soa1.fedps.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.fedps.services.business.ParcelFromQuoteInput;
import fr.unice.polytech.soa1.fedps.services.business.ParcelIO;
import fr.unice.polytech.soa1.fedps.services.business.QuoteInput;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ ParcelIO.class, QuoteInput.class, ParcelFromQuoteInput.class})
public abstract class OrderInput {

    public final JobResult process(DataAccessObject dao) throws BadJobFault
    {
        check(dao);
        return run(dao);
    }

    abstract protected JobResult run(DataAccessObject dao) throws BadJobFault;

    abstract protected void check(DataAccessObject dao) throws BadJobFault;

}
