package fr.unice.polytech.soa1.teamforce.rest;

import java.util.Optional;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import fr.unice.polytech.soa1.teamforce.dao.CustomerDAO;
import fr.unice.polytech.soa1.teamforce.entities.Catalogue;
import fr.unice.polytech.soa1.teamforce.entities.Good;

@Path("/teamforce/shop")
public class ShopRestImpl implements ShopRest {

	@EJB
	CustomerDAO Cdao;
	
	final GsonBuilder builder = new GsonBuilder();
	final Gson gson = builder.create();
	
	@Override
	public Response goods(String id) {
		Optional<Good> optO = Cdao.findGoodById(id);
		if(optO.isPresent()){
			final String json = gson.toJson(optO.get());
			return Response.ok(json).build();
		}else
			return Response.status(Status.NOT_FOUND).build();
	}


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
	public Response createCatalogue(String catalogue) {
		return Response.status(Status.BAD_REQUEST).build();
	}


	@Override
	public Response deleteCatalogue(String id) {
		// TODO Auto-generated method stub
		return null;
	}

}
