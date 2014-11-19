package fr.unice.polytech.soa1.misterdiscount.services;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlElementWrapper;

import fr.unice.polytech.soa1.misterdiscount.business.AddressWithName;
import fr.unice.polytech.soa1.misterdiscount.business.Customer;

@WebService(name="MisterDiscountCatalogue",
			targetNamespace = "http://informatique.polytech.unice.fr/soa1/MisterDiscount/catalogue",
			portName = "MisterDiscountCataloguePort", serviceName = "MisterDiscountCatalogueService")
public interface Catalogue {

	@WebMethod(operationName = "RegisterCatalogueExistingCustomer")
	public void registerCatalogueExistingCustomer(@WebParam(name="customerId") Integer customerId);

	@WebMethod(operationName = "RegisterCatalogueNewCustomer")
	public void registerCatalogueNewCustomer(@WebParam(name="customer") Customer newCustomer);
	
	@WebMethod(operationName = "GetAddressesToSend")
	@WebResult(name = "address")
	@XmlElementWrapper(name="addresses")
	public List<AddressWithName> getAddressesToSend();
	
}
