package fr.unice.polytech.soa1.warehouse.business;

/**
 * Created by Hugo on 19/10/2014.
 */

import javax.ejb.*;
import java.lang.reflect.Array;
import java.util.*;

@Singleton(name = "RedWarehouse-DB-Mock")
public class DataAccessObject {
    private List<Warehouse> contents;

    public List<Warehouse> getContents() { return contents; }
    public void setContents(List<Warehouse> warehouses) { this.contents = warehouses; }

    public DataAccessObject() { init(); }

    public HashSet<Product> getProducts(){
       HashSet<Product> ret = new HashSet<Product>();


        for (Warehouse w : contents){
            for(Box b:w.getBoxes()){
                for(Product p: b.getProduct()){
                    ret.add(p);
                }
            }
        }
    return ret;
    }

    public ArrayList<String> getProductLocalisation(String idw, String idp){
        Warehouse ware = null;
        ArrayList<String> infos = new ArrayList<String>();
        Product eqp = new Product(idp,"",0,0,0);

        for(Warehouse w : contents){
            if(idw.equals(w.getAdress())){
                ware = w;
            }
        }
        if(ware ==null){

        }

        for(Box b : ware.getBoxes()){
            if(b.getProduct().contains(eqp)){
               String prod = new String();
               prod.concat(b.getId() + " " + Collections.frequency(b.getProduct(), eqp));
               infos.add(prod);
            }
        }
        return infos;
    }

    public Product getProductById(String idp){
        Product eqp = new Product(idp,"",0,0,0);

        for (Warehouse w : contents){
            for(Box b:w.getBoxes()){
                List<Product> products = b.getProduct();
                if(products.contains(eqp)){
                    return products.get(products.indexOf(eqp));
                }
            }
        }
        return null;
    }

    public Boolean pickupProducts(String idwarehouse,String idbox, String idproduct, int quantity){
        Warehouse get = contents.get(contents.indexOf(new Warehouse(idwarehouse)));
        Box b = get.getBoxes().get(get.getBoxes().indexOf(new Box("idbox",0,0)));
        Product eqp = new Product(idproduct,"",0,0,0);

        for(int i = 0; i < quantity; i++){
            b.getProduct().remove(eqp);
        }

        return true;
    }


    private void init() {
        // Warehouses
        this.contents = new ArrayList<Warehouse>();
        Warehouse w1 = new Warehouse("Antibes");

        Box box1 = new Box("110", 100, 50);
        Box box2 = new Box("100", 100, 50);

        List<Product> products1 = new ArrayList<Product>(Arrays.asList(
                new Product("1000","clou", 10,10,10),
        new Product("1000","clou", 10,10,10),
        new Product("1001","marteau", 2,2,2)
        ));

        List<Product> products2 = new ArrayList<Product>(Arrays.asList(
                new Product("1000","clou", 10,10,10),
        new Product("1000","clou", 10,10,10),
        new Product("1001","marteau", 2,2,2)
        ));

        box1.setProducts(products1);
        box2.setProducts(products2);

        List<Box> boxes = new ArrayList<Box>(Arrays.asList(
                box1,
                box2
        ));

        w1.setBoxes(boxes);
        contents.add(w1);

    }
}
