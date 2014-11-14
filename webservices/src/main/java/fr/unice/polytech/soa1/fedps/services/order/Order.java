package fr.unice.polytech.soa1.fedps.services.order;


import fr.unice.polytech.soa1.fedps.services.order.input.OrderInput;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.ws.rs.Produces;

@Produces("application/xml")
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/fedps/services/order")
public interface Order {

    @WebMethod(operationName = "submit")
    @WebResult(name = "result")
    public JobResult dispatch(@WebParam(name = "request") OrderInput input) throws BadJobFault;


    //ParcelIO
    //QuoteInput/Output
    //OrderOutput
    //ParcelFromQuoteInput
}
