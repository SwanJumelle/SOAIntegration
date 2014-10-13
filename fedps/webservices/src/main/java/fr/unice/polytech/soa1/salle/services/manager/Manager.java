package fr.unice.polytech.soa1.salle.services.manager;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

@Produces({ "application/json", "application/xml" })
public interface Manager {

    @Path("/stats")
    @GET
    public Response getStats();

    @Path("/stats/{status}")
    @GET
    public Response getParcelsWithStatus(@PathParam("status") String status);

    @Path("/parcels/{id}")
    @GET
    public Response getParcelInformation(@PathParam("id") String parcelId);

    @Path("/delayed_parcels")
    @GET
    public Response getDelayedParcels();

}
