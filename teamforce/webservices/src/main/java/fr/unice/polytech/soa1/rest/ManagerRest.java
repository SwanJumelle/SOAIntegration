package fr.unice.polytech.soa1.rest;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Produces({"text/json"})
public interface ManagerRest {

	@Path("/catalogue/{id}")
	@POST
	public Response addToCatalogue(@PathParam("id") String id, @QueryParam("catalogue") String catalogue);
	
	@Path("/catalogue/{id}")
	@GET
	public Response getCatalogue(@PathParam("id") String id);

	@Path("/catalogue")
	@POST
	public Response createCatalogue(@PathParam("id") String id, @QueryParam("catalogue") String catalogue);
	
	
}
