package fr.unice.polytech.soa1.fedps.services.order;


import fr.unice.polytech.soa1.fedps.services.business.*;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Produces;

@Produces("application/xml")
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/fedps/services/order")
public interface Order {

    @WebMethod(operationName = "processQuote")
    @WebResult(name = "quoteOutput")
    public QuoteOutput processQuote(QuoteInput input) throws BadJobFault;

    @WebMethod(operationName = "processOrderFromQuote")
    @WebResult(name = "orderOutput")
    public OrderOutput processOrderFromQuote(ParcelFromQuoteInput input) throws BadJobFault;

    @WebMethod(operationName = "processOrder")
    @WebResult(name = "orderOutput")
    public OrderOutput processOrder(ParcelIO input) throws BadJobFault;


    //ParcelIO
    //QuoteInput/Output
    //OrderOutput
    //ParcelFromQuoteInput
}
