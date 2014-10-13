package fr.unice.polytech.soa1.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

import com.google.gson.JsonObject;

@Produces({"text/json"})
public interface CustomerRest {

	@Path("/{id}")
	@GET
	public Response customer(@PathParam("id") String id);
	
	@Path("/{id}/orders")
	@GET
	public Response orders(@PathParam("id") String id);
	
	@Path("/goods/{id}")
	@GET
	public Response goods(@PathParam("id") String id);
	
	@Path("/{id}/order")
	@POST
	public Response createOrder(@PathParam("id") String id, @QueryParam("order") String order);
	
	@Path("/{id}/order/{OrderId}")
	@POST
	public Response addToOrder(@PathParam("id") String id,@PathParam("OrderId") String OrderId, @QueryParam("good") String good);
	
	@Path("/{id}/order/{OrderId}")
	@GET
	public Response getOrder(@PathParam("id") String id,@PathParam("OrderId") String OrderId);
	
	@Path("/{id}/recommend")
	@GET
	public Response recommendOrder(@PathParam("id") String id);
	
	@Path("/{id}/order/{OrderId}")
	@DELETE
	public Response deleteOrder(@PathParam("id") String id,@PathParam("OrderId") String OrderId);
}
