package fr.unice.polytech.soa1.teamforce.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Produces({"application/json"})
public interface OrderRest {
	
	@Path("/create")
	@POST
	public Response createOrder(@PathParam("id") String id, @QueryParam("order") String order);
	
	@Path("/{id}")
	@POST
	public Response addToOrder(@PathParam("id") String id, @QueryParam("good") String good);
	
	@Path("/{id}")
	@GET
	public Response getOrder(@PathParam("id") String id);
	
	@Path("/{id}")
	@DELETE
	public Response deleteOrder(@PathParam("id") String id);
}
