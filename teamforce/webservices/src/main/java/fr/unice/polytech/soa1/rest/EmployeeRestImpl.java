package fr.unice.polytech.soa1.rest;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.unice.polytech.soa1.dao.CustomerDAO;
import fr.unice.polytech.soa1.entities.Customer;
import fr.unice.polytech.soa1.entities.Good;
import fr.unice.polytech.soa1.entities.GoodOrder;
import fr.unice.polytech.soa1.entities.Order;

@Path("/rest/employee")
public class EmployeeRestImpl implements EmployeeRest {
	
	@EJB
	CustomerDAO Cdao;

	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.create();
	
	@Override
	public Response customerInfo(String id) {
		Optional<Customer> opt = Cdao.findCustomerById(id);
		if(opt.isPresent()){
			final String json = gson.toJson(opt.get());
			return Response.ok(json).build();
		}else
			return Response.status(Status.NOT_FOUND).build();
	}

	@Override
	public Response addToOrder(String id, String OrderId, String goodOrder) {
		Optional<Order> optO = Cdao.findOrderById(OrderId);
		if(optO.isPresent()){
			Order o = optO.get();
			GoodRef g = gson.fromJson(goodOrder, GoodRef.class);
			Optional<Good> optG = Cdao.findGoodById(g.id);
			if(optG.isPresent()){
				GoodOrder go = new GoodOrder(g.quantity,optG.get());
				o.getGoods().add(go);
				return Response.ok("{ \"id\":"+go.getGood().getId()+" }").build();
			}
		}
		return Response.status(Status.BAD_REQUEST).build();
	}
	

}
