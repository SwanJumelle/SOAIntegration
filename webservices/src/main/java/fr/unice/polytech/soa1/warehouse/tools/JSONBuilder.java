package fr.unice.polytech.soa1.warehouse.tools;

import fr.unice.polytech.soa1.warehouse.business.Product;
import fr.unice.polytech.soa1.warehouse.business.Warehouse;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * Created by Hugo on 19/10/2014.
 */
public abstract class JSONBuilder {

    public static JSONObject parse(Warehouse w) throws JSONException {
        JSONObject addressObject;

        addressObject = new JSONObject();
        addressObject.put("address",w.getAdress());

        return addressObject;
    }

    public static JSONObject parse(List<Warehouse> w) throws JSONException {
        JSONObject addressObject = new JSONObject();
        int i = 1;

        for(Warehouse e : w){
            addressObject.put("adress"+i, e.getAdress() );
        }

        return addressObject;
    }

    public static JSONArray parse(HashSet<Product> products ) throws JSONException {
        JSONObject productinfo;
        JSONArray productsinfo = new JSONArray();

        for (Product p : products ){
            productinfo = new JSONObject();
            productinfo.put("id",p.getId());
            productinfo.put("name",p.getName());
            productsinfo.put(productinfo);
        }

        return productsinfo;
    }

    public static JSONArray parseloc(ArrayList<String> infos){
        JSONArray ret = new JSONArray();

        for(String s : infos){
            JSONObject obj = new JSONObject();
            String[] inf = s.split(" ");
            char[] infloc = inf[0].toCharArray();
            obj.put("floor", infloc[0]);
            obj.put("corridor", infloc[1]);
            obj.put("rack",infloc[2]);
            obj.put("quantity", Integer.parseInt(inf[1]));

            ret.put(obj);
        }

        return ret;
    }

    public static JSONObject parse(Product p ) throws JSONException {
        JSONObject ret = new JSONObject();
        ret.put("id", p.getId());
        ret.put("name", p.getName());
        ret.put("width", p.getWidth());
        ret.put("height", p.getHeight());
        ret.put("depth", p.getDepth());

        return ret;
    }


}
