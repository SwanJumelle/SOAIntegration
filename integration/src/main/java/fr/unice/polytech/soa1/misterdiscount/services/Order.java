package fr.unice.polytech.soa1.misterdiscount.services;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

import fr.unice.polytech.soa1.misterdiscount.business.OrderInformation;
import fr.unice.polytech.soa1.misterdiscount.business.OrderInput;
import fr.unice.polytech.soa1.misterdiscount.business.Receipt;

@WebService(name="MisterDiscountOrder",
			targetNamespace = "http://informatique.polytech.unice.fr/soa1/MisterDiscount/order",
			portName = "MisterDiscountOrderPort", serviceName = "MisterDiscountOrderService")
public interface Order {

	@WebMethod(operationName = "ProcessOrder")
	@WebResult(name = "receipt")
	public Receipt processOrder(@WebParam(name="orderinput") OrderInput input);
	
	@WebMethod(operationName = "GetOrderInformation")
	@WebResult(name = "orderInformation")
	public OrderInformation getOrderInformation(@WebParam(name="orderId") Integer orderId);
	
}
