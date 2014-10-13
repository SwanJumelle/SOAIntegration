package fr.unice.polytech.soa1.salle.services.driver;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.Address;
import fr.unice.polytech.soa1.salle.business.model.Parcel;
import fr.unice.polytech.soa1.salle.business.model.ParcelStatus;
import fr.unice.polytech.soa1.salle.services.driver.output.KindOfStep;
import fr.unice.polytech.soa1.salle.services.driver.output.Step;
import fr.unice.polytech.soa1.salle.services.io.AddressIO;
import fr.unice.polytech.soa1.salle.services.manager.outputs.UnknownResource;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.Optional;

@Path("/driver")
public class DriverImpl implements Driver {

    @EJB DataAccessObject dao;

    @Override
    public Response updateParcelStatus(String parcelId)
    {
        Optional<Parcel> parcel = dao.findParcelById(parcelId);
        if (!parcel.isPresent())
        {
            UnknownResource error = new UnknownResource("parcel", parcelId);
            return Response.status(Response.Status.NOT_FOUND).entity(error).build();
        }

        ParcelStatus newStatus;
        switch (parcel.get().getStatus())
        {
            case IN_TRANSIT: {
                newStatus = ParcelStatus.DELIVERED;
                break;
            }
            default: {
                newStatus = ParcelStatus.PICKED_UP;
                break;
            }
        }
        parcel.get().setStatus(newStatus);

        return Response.status(Response.Status.OK).build();
    }

    @Override
    public Response getNextStepForDriver(String driverId)
    {
        for (Parcel p : dao.getParcels())
        {
            if (p.getStatus() == ParcelStatus.AWAITING_PICK_UP || p.getStatus() == ParcelStatus.IN_TRANSIT)
            {
                KindOfStep kind =
                        (p.getStatus() == ParcelStatus.AWAITING_PICK_UP)
                                ? KindOfStep.PICKUP
                                : KindOfStep.DELIVERY;
                Address address =
                        (p.getStatus() == ParcelStatus.AWAITING_PICK_UP)
                                ? p.getTransportInformation().getFromAddress()
                                : p.getTransportInformation().getToAddress();

                return Response.ok(new Step(p.getId(), kind, new AddressIO(address))).build();
            }
        }

        return Response.ok(new AddressIO()).build();
    }

}
