package fr.unice.polytech.soa1.salle.services.driver;

import fr.unice.polytech.soa1.salle.business.model.ParcelStatus;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Produces({ "application/json", "application/xml" })
public interface Driver {

    @Path("/parcels/{id}/update")
    @PUT
    public Response updateParcelStatus(@PathParam("id") String parcelId);

    @Path("/{id}/next_address")
    @GET
    public Response getNextStepForDriver(@PathParam("id") String driverId);

}
