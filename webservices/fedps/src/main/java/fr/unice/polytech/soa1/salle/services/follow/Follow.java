package fr.unice.polytech.soa1.salle.services.follow;

import fr.unice.polytech.soa1.salle.bdd.model.ParcelStatus;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces("application/json")
public interface Follow {

    @Path("/parcels")
    @GET
    public Response getParcels();

    @Path("/parcels/{id}")
    @GET
    public Response getParcelInformation(@PathParam("id") String parcelId);

    @Path("/parcels/delayed")
    @GET
    public Response getDelayedParcels();

    @Path("/stats")
    @GET
    public Response getStats();

    @Path("/stats/{status}")
    @GET
    public Response getParcelsWithStatus(@PathParam("status") ParcelStatus status);

}
