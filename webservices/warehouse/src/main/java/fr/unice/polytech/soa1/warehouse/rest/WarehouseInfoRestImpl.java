package fr.unice.polytech.soa1.warehouse.rest;

/**
 * Created by Hugo on 26/10/2014.
 */

import fr.unice.polytech.soa1.warehouse.business.DataAccessObject;
import fr.unice.polytech.soa1.warehouse.business.Product;
import fr.unice.polytech.soa1.warehouse.business.Warehouse;
import fr.unice.polytech.soa1.warehouse.tools.JSONBuilder;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.ejb.EJB;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.lang.reflect.Array;
import java.net.URI;
import java.util.*;


@Path("/rest/info")
public class WarehouseInfoRestImpl implements WarehouseInfoRest {

    @EJB private DataAccessObject dao;

    @Override
    public Response warehouses() {
        List<Warehouse> warehouses = dao.getContents();
        JSONObject jObject = JSONBuilder.parse(warehouses);
        return Response.status(Response.Status.OK).entity(jObject.toString()).build();
    }

    @Override
    public Response warehouseproducts(){

        HashSet<Product> products = dao.getProducts();
        JSONArray productsinfo = JSONBuilder.parse(products);
        return Response.status(Response.Status.OK).entity(productsinfo.toString()).build();

    }

    @Override
    public Response warehouseproductinfo(String productId, String warehouseId){
        ArrayList<String> infos = dao.getProductLocalisation(warehouseId, productId);
        JSONArray ret = JSONBuilder.parseloc(infos);
        return Response.status(Response.Status.OK).entity(ret.toString()).build();
    }

    @Override
    public Response productinfo(String productId){
        JSONObject ret;
        Product p = dao.getProductById(productId);
        if(p == null){
            return Response.status(Response.Status.NOT_FOUND).entity(null).build();
        }
        ret = JSONBuilder.parse(p);

        return Response.status(Response.Status.OK).entity(ret.toString()).build();
    }

    @Override
    public Response warehouseemployees(String warehouseId){
        return null;
    }

    @Override
    public Response employeeevents(String employeeId, String warehouseId){
        return null;
    }

    @Override
    public Response boxinfo(String boxId,String warehouseId){
        return null;
    }

    @Override
    public Response boxes(String warehouseId){
        return null;
    }

    @Override
    public Response unassignedevents(String warehouseId){
        return null;
    }
}
