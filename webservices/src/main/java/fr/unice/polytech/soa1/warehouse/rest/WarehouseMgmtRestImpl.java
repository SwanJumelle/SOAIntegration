package fr.unice.polytech.soa1.warehouse.rest;

/**
 * Created by Hugo on 27/10/2014.
 */

import fr.unice.polytech.soa1.warehouse.business.DataAccessObject;
import fr.unice.polytech.soa1.warehouse.business.Event;
import fr.unice.polytech.soa1.warehouse.business.Product;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;


@Path("/warehouse/mgmt")
public class WarehouseMgmtRestImpl implements WarehouseMgmtRest {

    @EJB private DataAccessObject dao;

    @Override
    public Response addevent(String warehouseId, Event.Type eventype, String time, String product,int q){
        return null;
    }

    @Override
    public Response transferemployee(String employeeId,String fromwarehouseId,String towarehouseId){
        return null;
    }

    @Override
    public Response pickupproducts(String product,String warehouseId,String idbox,int quantity){
       if(dao.pickupProducts(warehouseId,idbox, product,quantity)){
           return Response.status(Response.Status.OK).build();
       }
       return Response.status(Response.Status.NOT_ACCEPTABLE).build();
    }

    @Override
    public Response storeproducts(String warehouseId,Product product,int quantity){
        return null;
    }

    @Override
    public Response assignevent(String eventid, String warehouseId,String employeeid){
        return null;
    }
}
