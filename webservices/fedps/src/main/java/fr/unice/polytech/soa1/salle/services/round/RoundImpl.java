package fr.unice.polytech.soa1.salle.services.round;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.soa1.salle.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.bdd.model.Address;
import fr.unice.polytech.soa1.salle.bdd.model.Parcel;
import fr.unice.polytech.soa1.salle.bdd.model.ParcelStatus;
import fr.unice.polytech.soa1.salle.services.business.AddressIO;
import fr.unice.polytech.soa1.salle.services.business.KindOfRoundStep;
import fr.unice.polytech.soa1.salle.services.business.RoundStepIO;
import fr.unice.polytech.soa1.salle.services.business.UnknownResource;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/round")
public class RoundImpl implements Round {

    @EJB private DataAccessObject dao;
    private Gson gson = new GsonBuilder().create();

    @Override
    public Response updateParcelStatus(String parcelId, ParcelStatus status)
    {
        Response response;
        Optional<Parcel> parcel = dao.findParcelById(parcelId);

        if (parcel.isPresent())
        {
            parcel.get().setStatus(status);
            response = Response.status(Response.Status.OK).build();
        }
        else
        {
            UnknownResource error = new UnknownResource("parcel", parcelId);
            response = Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(error)).build();
        }

        return response;
    }

    @Override
    public Response getNextStepForDriver(String driverId)
    {
        for (Parcel p : dao.getParcels())
        {
            if (p.getStatus() == ParcelStatus.AWAITING_PICK_UP || p.getStatus() == ParcelStatus.IN_TRANSIT)
            {
                KindOfRoundStep kind =
                        (p.getStatus() == ParcelStatus.AWAITING_PICK_UP)
                                ? KindOfRoundStep.PICKUP
                                : KindOfRoundStep.DELIVERY;
                Address address =
                        (p.getStatus() == ParcelStatus.AWAITING_PICK_UP)
                                ? p.getTransportInformation().getFromAddress()
                                : p.getTransportInformation().getToAddress();

                RoundStepIO step = new RoundStepIO(p.getId(), kind, new AddressIO(address));

                return Response.ok(gson.toJson(step)).build();
            }
        }

        return Response.status(Response.Status.NOT_FOUND).build();
    }

}
