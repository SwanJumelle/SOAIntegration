package fr.unice.polytech.soa1.teamforce.rest;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.unice.polytech.soa1.teamforce.dao.CustomerDAO;
import fr.unice.polytech.soa1.teamforce.entities.Customer;
import fr.unice.polytech.soa1.teamforce.entities.Good;

@Path("/teamforce/customer")
public class CustomerRestImpl implements CustomerRest {

	@EJB
	CustomerDAO Cdao;

	private int id = 22;
	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.create();

	@Override
	public Response orders(String id) {
		Optional<Customer> opt = Cdao.findCustomerById(id);
		if(opt.isPresent()){
			final String json = gson.toJson(opt.get().getOrders());
			return Response.ok(json).build();
		}else
			return Response.status(Status.NOT_FOUND).build();
	}

	@Override
	public Response recommendOrder(String id) {
		List<Good> goods = new ArrayList<Good>();
		goods.add(Cdao.findGoodById("U4P3YK6SD74JV7T").get());
		goods.add(Cdao.findGoodById("F2D1DR4HI48SS7F").get());
		goods.add(Cdao.findGoodById("Y6Z3MU5YI89PI1K").get());
		final String json = gson.toJson(goods);
		return Response.ok(json).build();
	}


	@Override
	public Response customer(String id) {
		Optional<Customer> opt = Cdao.findCustomerById(id);
		if(opt.isPresent()){
			final String json = gson.toJson(opt.get());
			return Response.ok(json).build();
		}else
			return Response.status(Status.NOT_FOUND).build();
	}

}
