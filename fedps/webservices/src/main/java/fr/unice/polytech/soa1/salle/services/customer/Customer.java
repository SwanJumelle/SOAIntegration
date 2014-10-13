package fr.unice.polytech.soa1.salle.services.customer;

import fr.unice.polytech.soa1.salle.services.customer.inputs.BadJobFault;
import fr.unice.polytech.soa1.salle.services.customer.inputs.CustomerInput;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/salle/services/customer")
public interface Customer {

    @WebMethod(operationName = "submit")
    @WebResult(name = "result")
    public JobResult dispatch(@WebParam(name = "request") CustomerInput input) throws BadJobFault;

}
