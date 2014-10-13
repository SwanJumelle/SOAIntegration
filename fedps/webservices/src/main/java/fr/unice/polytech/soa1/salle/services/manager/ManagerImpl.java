package fr.unice.polytech.soa1.salle.services.manager;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.Parcel;
import fr.unice.polytech.soa1.salle.business.model.ParcelStatus;
import fr.unice.polytech.soa1.salle.services.manager.outputs.ListOfParcels;
import fr.unice.polytech.soa1.salle.services.io.ParcelIO;
import fr.unice.polytech.soa1.salle.services.manager.outputs.Stats;
import fr.unice.polytech.soa1.salle.services.manager.outputs.UnknownResource;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.Optional;

@Path("/manager")
public class ManagerImpl implements Manager {

    @EJB private DataAccessObject dao;
    @Context private UriInfo uriInfo;

    @Override
    public Response getStats()
    {
        Stats result = new Stats();
        for (Parcel parcel : dao.getParcels())
        {
            int nb = result.getNbOfEach().containsKey(parcel.getStatus()) ? result.getNbOfEach().get(parcel.getStatus()) : 0;
            result.getNbOfEach().put(parcel.getStatus(), nb + 1);
        }
        return Response.ok(result).build();
    }

    @Override
    public Response getParcelsWithStatus(String status)
    {
        ParcelStatus parcelStatus;
        try
        {
            parcelStatus = ParcelStatus.valueOf(status);
        }
        catch (IllegalArgumentException e)
        {
            UnknownResource error = new UnknownResource("status", status);
            return Response.status(Response.Status.BAD_REQUEST).entity(error).build();
        }

        ListOfParcels result = new ListOfParcels();
        for (Parcel parcel : dao.getParcels())
        {
            if (parcel.getStatus().equals(parcelStatus))
            {
                result.getParcels().add(uriInfo.getBaseUri().toString() + "manager/parcels/" + parcel.getId());
            }
        }
        return Response.ok(result).build();
    }

    @Override
    public Response getParcelInformation(String parcelId)
    {
        Optional<Parcel> parcel = dao.findParcelById(parcelId);
        if (!parcel.isPresent())
        {
            UnknownResource error = new UnknownResource("parcel", parcelId);
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }
        return Response.ok(new ParcelIO(parcel.get())).build();
    }

    @Override
    public Response getDelayedParcels()
    {
        ListOfParcels result = new ListOfParcels();
        for (Parcel parcel : dao.getParcels())
        {
            if (parcel.isDelayed())
            {
                result.getParcels().add(uriInfo.getBaseUri().toString() + "manager/parcels/" + parcel.getId());
            }
        }
        return Response.ok(result).build();
    }

}
