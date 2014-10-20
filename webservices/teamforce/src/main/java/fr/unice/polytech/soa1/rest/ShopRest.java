package fr.unice.polytech.soa1.rest;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

public interface ShopRest {
	
	@Path("/goods/{id}")
	@GET
	public Response goods(@PathParam("id") String id);
	
	@Path("/catalogue/{id}")
	@GET
	public Response getCatalogue(@PathParam("id") String id);
	
	@Path("/catalogue/{id}")
	@POST
	public Response addToCatalogue(@PathParam("id") String id, @QueryParam("good") String good);
	
	@Path("/catalogues")
	@POST
	public Response createCatalogue(@QueryParam("catalogue") String catalogue);
	
	@Path("/catalogue/{id}")
	@DELETE
	public Response deleteCatalogue(@PathParam("id") String id);

}
