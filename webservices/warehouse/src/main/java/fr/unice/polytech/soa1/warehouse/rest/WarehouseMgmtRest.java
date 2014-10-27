package fr.unice.polytech.soa1.warehouse.rest;

import fr.unice.polytech.soa1.warehouse.business.Event;
import fr.unice.polytech.soa1.warehouse.business.Product;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 20/10/2014.
 */
public interface WarehouseMgmtRest {

    @Path("/event")
    @POST
    public Response addevent(@QueryParam("id") 	String warehouseId,
                             @QueryParam("type") Event.Type eventype,
                             @QueryParam("date") String time,
                             @QueryParam("product") String product,
                             @QueryParam("quantity") int q);
                             

    @Path("/employee/{id}")
    @PUT
    public Response transferemployee(@PathParam("id")     String employeeId,
                                     @QueryParam("idfw") 	String fromwarehouseId,
                                     @QueryParam("idtw") 	String towarehouseId);

    @Path ("/product/{id}")
    @DELETE
    public Response pickupproducts(@PathParam("id")     String product,
                                   @QueryParam("idw") 	String warehouseId,
                                   @QueryParam("idb") 	String boxId,
                                   @QueryParam("quantity") 	int quantity);

    @Path("/product")
    @PUT
    public Response storeproducts(@QueryParam("idw") 	String warehouseId,
                                  @QueryParam("product")Product product,
                                  @QueryParam("quantity")int quantity);

    @Path("/event/{id}")
    @PUT
    public Response assignevent(@PathParam("id")     String eventid,
                                @QueryParam("idw") 	String warehouseId,
                                @QueryParam("employee")String employeeid);




}
