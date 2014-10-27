package fr.unice.polytech.soa1.warehouse.business;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Hugo on 19/10/2014.
 */

@XmlType
@XmlRootElement(name = "box")
public class Box {
    private String id;
    private double volume;
    private int occupancy;
    private List<Product> products;


    public Box() {}

    public Box(String id, double volume, int occupancy) {
        this.id = id;
        this.volume = volume;
        this.occupancy = occupancy;
        this.products = new ArrayList<Product>();
    }

    public Box(Box that) {  // copy constructor
        this(that.id, that.volume, that.occupancy);
        for(Product p: that.products) {
            Product clone = new Product(p);
            this.getProduct().add(clone);
        }
    }

    @XmlAttribute(name="id")
    @XmlID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="volume")
    public double getVolume() {
        return volume;
    }
    public void setVolume(double volume) {
        this.volume = volume;
    }

    @XmlAttribute(name="occupancy")
    public int getOccupancy() {
        return occupancy;
    }
    public void setOccupancy(int occupancy) {
        this.occupancy = occupancy;
    }

    @XmlElementWrapper(name = "product")
    @XmlElement(name = "product")
    public List<Product> getProduct() {
        return products;
    }
    public void setProducts(List<Product> products) {
        this.products = products;
    }

    @Override
    public boolean equals (Object that) {  // Necessary for object stored in collections ( => find, remove, ...)
        return  (this == that || (that instanceof Box && this.id.equals(((Box) that).id)));
    }

    @Override
    public int hashCode(){ return this.id.hashCode(); }

}
