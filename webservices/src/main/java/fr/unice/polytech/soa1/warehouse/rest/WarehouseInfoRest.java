package fr.unice.polytech.soa1.warehouse.rest;

/**
 * Created by Hugo on 19/10/2014.
 */
import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@Produces({"application/json"})
public interface WarehouseInfoRest {

    @Path("/warehouse")
    @GET
    public Response warehouses();

    @Path("/products")
    @GET
    public Response warehouseproducts();

    @Path("/products/{id}")
    @GET
    public Response productinfo(@PathParam("id")   String productId);

    @Path("/warehouse/{idw}/products/{id}")
    @GET
    public Response warehouseproductinfo(@PathParam("id")   String productId,
                                         @PathParam("idw")  String warehouseId);

    @Path("/employee")
    @GET
    public Response warehouseemployees(@QueryParam("idw")    String warehouseId);

    @Path("/event/{id}")
    @GET
    public Response employeeevents(@PathParam("id")     String employeeId,
                                   @QueryParam("idw") 	String warehouseId);

    @Path("/box/{id}")
    @GET
    public Response boxinfo(@PathParam("id")    String boxId,
                            @QueryParam("idw")   String warehouseId);

    @Path("/box")
    @GET
    public Response boxes(@QueryParam("id")   String warehouseId);

    @Path("/event")
    @GET
    public Response unassignedevents(@QueryParam("id") String warehouseId);
}



