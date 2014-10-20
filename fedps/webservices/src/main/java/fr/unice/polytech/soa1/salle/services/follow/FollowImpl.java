package fr.unice.polytech.soa1.salle.services.follow;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import fr.unice.polytech.soa1.salle.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.bdd.model.Parcel;
import fr.unice.polytech.soa1.salle.bdd.model.ParcelStatus;
import fr.unice.polytech.soa1.salle.services.business.ParcelIO;
import fr.unice.polytech.soa1.salle.services.business.UnknownResource;
import org.json.simple.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Path("/follow")
public class FollowImpl implements Follow {

    @EJB private DataAccessObject dao;
    @Context private UriInfo uriInfo;
    private Gson gson = new GsonBuilder().create();

    private String urlForParcelId(String parcelId)
    {
        return uriInfo.getBaseUri().toString() + "follow/parcels/" + parcelId;
    }

    @Override
    public Response getParcels()
    {
        List<String> parcels = new ArrayList<>();
        for (Parcel parcel : dao.getParcels())
        {
            parcels.add(urlForParcelId(parcel.getId()));
        }

        JSONObject result = new JSONObject();
        result.put("parcels", parcels);

        return Response.ok(gson.toJson(result)).build();
    }

    @Override
    public Response getParcelInformation(String parcelId)
    {
        Response response;
        Optional<Parcel> parcel = dao.findParcelById(parcelId);

        if (parcel.isPresent())
        {
            ParcelIO result = new ParcelIO(parcel.get());
            response = Response.ok(gson.toJson(result)).build();
        }
        else
        {
            UnknownResource error = new UnknownResource("parcel", parcelId);
            response = Response.status(Response.Status.NOT_FOUND).entity(gson.toJson(error)).build();
        }

        return response;
    }

    @Override
    public Response getDelayedParcels()
    {
        List<String> parcels = new ArrayList<>();
        for (Parcel parcel : dao.getParcels())
        {
            if (parcel.isDelayed())
            {
                parcels.add(urlForParcelId(parcel.getId()));
            }
        }

        JSONObject result = new JSONObject();
        result.put("parcels", parcels);

        return Response.ok(gson.toJson(result)).build();
    }

    @Override
    public Response getStats()
    {
        JSONObject result = new JSONObject();
        for (ParcelStatus status : ParcelStatus.values())
        {
            result.put(status,
                    dao.getParcels().stream().filter(p -> p.getStatus() == status).count());
        }
        return Response.ok(gson.toJson(result)).build();
    }

    @Override
    public Response getParcelsWithStatus(ParcelStatus status)
    {
        List<String> parcels = new ArrayList<>();
        for (Parcel parcel : dao.getParcels())
        {
            if (parcel.getStatus().equals(status))
            {
                parcels.add(urlForParcelId(parcel.getId()));
            }
        }

        JSONObject result = new JSONObject();
        result.put("parcels", parcels);

        return Response.ok(gson.toJson(result)).build();
    }

}
