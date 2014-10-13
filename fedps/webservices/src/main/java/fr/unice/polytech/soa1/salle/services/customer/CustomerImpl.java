package fr.unice.polytech.soa1.salle.services.customer;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.services.customer.inputs.BadJobFault;
import fr.unice.polytech.soa1.salle.services.customer.inputs.CustomerInput;
import fr.unice.polytech.soa1.salle.services.customer.outputs.CustomerOutput;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;

@Stateless(name = "FedPS-Customer")
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/salle/services/customer",
        portName = "FedPSCustomerPort",
        serviceName = "FedPSCustomerService",
        endpointInterface = "fr.unice.polytech.soa1.salle.services.customer.Customer")
public class CustomerImpl implements Customer {

    @EJB DataAccessObject dao;

    @Override
    public JobResult dispatch(CustomerInput input) throws BadJobFault
    {
        return input.process(dao);
    }

}
