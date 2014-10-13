package fr.unice.polytech.soa1.rest;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.unice.polytech.soa1.dao.CustomerDAO;
import fr.unice.polytech.soa1.entities.Catalogue;

@Path("/rest/manager")
public class ManagerRestImpl implements ManagerRest{

	@EJB
	CustomerDAO Cdao;
	
	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.create();

	@Override
	public Response getCatalogue(String id) {
		Optional<Catalogue> opt = Cdao.findCatalogueById(id);
		if(opt.isPresent()){
			final String json = gson.toJson(opt.get());
			return Response.ok(json).build();
		}
		return Response.status(Status.NOT_FOUND).build();
	}

	@Override
	public Response addToCatalogue(String id, String catalogue) {
		return Response.ok().build();
	}

	@Override
	public Response createCatalogue(String id, String catalogue) {
		Optional<Catalogue> opt = Cdao.findCatalogueById(id);
		if(!opt.isPresent()){
			return Response.ok().build();
		}
		return Response.status(Status.BAD_REQUEST).build();
	}

	
}
