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

@Produces({"application/json"})
public interface CustomerRest {

	@Path("/{id}")
	@GET
	public Response customer(@PathParam("id") String id);
	
	@Path("/{id}/orders")
	@GET
	public Response orders(@PathParam("id") String id);
	
	@Path("/{id}/recommend")
	@GET
	public Response recommendOrder(@PathParam("id") String id);
	
}
