package fr.unice.polytech.soa1.fedps.services.order;

import fr.unice.polytech.soa1.fedps.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.fedps.services.business.*;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;

@Stateless(name = "FedPS-Order")
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/fedps/order",
        portName = "FedPSOrderPort",
        serviceName = "FedPSOrderService",
        endpointInterface = "fr.unice.polytech.soa1.fedps.services.order.Order")
public class OrderImpl implements Order {

    @EJB private DataAccessObject dao;

    @Override
    public QuoteOutput processQuote(QuoteInput input) throws BadJobFault
    {
        input.check(dao);
        return input.run(dao);
    }

    @Override
    public OrderOutput processOrderFromQuote(ParcelFromQuoteInput input) throws BadJobFault
    {
        input.check(dao);
        return input.run(dao);
    }

    @Override
    public OrderOutput processOrder(ParcelIO input) throws BadJobFault
    {
        input.check(dao);
        return input.run(dao);
    }

}
