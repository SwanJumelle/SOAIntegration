package fr.unice.polytech.soa1.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Produces({"text/json"})
public interface EmployeeRest {
	
	@Path("/customer/{id}")
	@GET
	public Response customerInfo(@PathParam("id") String id);
	
	@Path("/customer/{id}/order/{OrderId}")
	@POST
	public Response addToOrder(@PathParam("id") String id,@PathParam("OrderId") String OrderId, @QueryParam("good") String good);

	
}
