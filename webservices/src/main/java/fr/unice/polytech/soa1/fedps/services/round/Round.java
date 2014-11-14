package fr.unice.polytech.soa1.fedps.services.round;

import fr.unice.polytech.soa1.fedps.bdd.model.ParcelStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces("application/json")
public interface Round {

    @Path("/parcels/{id}/{status}")
    @PUT
    public Response updateParcelStatus(@PathParam("id") String parcelId,
                                       @PathParam("status") ParcelStatus status);

    @Path("/{id}/next_address")
    @GET
    public Response getNextStepForDriver(@PathParam("id") String driverId);

}
