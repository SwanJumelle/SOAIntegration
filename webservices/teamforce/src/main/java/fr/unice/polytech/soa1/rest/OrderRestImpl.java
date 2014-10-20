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

@Path("/order")
public class OrderRestImpl implements OrderRest {

	@EJB
	CustomerDAO Cdao;

	private int id = 22;
	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.create();
	
	@Override
	public Response createOrder(String id, String order) {
		Optional<Customer> opt = Cdao.findCustomerById(id);
		if(opt.isPresent()){
			Order o = gson.fromJson(order, Order.class);
			String newId = setOrderId(o);
			opt.get().getOrders().add(o);
			Cdao.getOrders().add(o);
			return Response.ok("{ \"id\":"+newId+" }").build();
		}else
			return Response.status(Status.BAD_REQUEST).build();
	}

	@Override
	public Response addToOrder(String OrderId, String goodOrder) {
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

	@Override
	public Response getOrder(String OrderId) {
		Optional<Customer> optC ;
		Optional<Order> opt = Cdao.findOrderById(OrderId);
		Order ord = null;
		if(opt.isPresent()){
			ord = opt.get();
			optC = Cdao.findCustomerById(ord.getCustomerId());
			if(!optC.isPresent()){
				return Response.status(Status.NOT_FOUND).build();
			}
		}else{
			return Response.status(Status.NOT_FOUND).build();
		}
		
		optC.get().getOrders().remove(ord);
		final String json = gson.toJson(ord);
		return Response.ok(json).build();
	}

	@Override
	public Response deleteOrder(String OrderId) {
		Optional<Order> opt = Cdao.findOrderById(OrderId);
		Order ord = null;
		if(opt.isPresent())
			ord = opt.get();
		else
			return Response.status(Status.NOT_FOUND).build();

		Cdao.getOrders().remove(opt.get());
		return Response.ok("{ \"id\":"+ord.getId()+" }").build();
	}
	
	private String setOrderId(Order o) {
		if(o!=null){
			String newId = String.valueOf(id++);
			o.setId(newId);
			return newId;
		}else{
			return "-1";
		}
	}

}
