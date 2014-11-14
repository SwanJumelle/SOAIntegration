package fr.unice.polytech.soa1.warehouse.business;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;


/**
 * Created by Hugo on 19/10/2014.
 */

@XmlType
@XmlRootElement(name = "product")
public class Product {

    private String id;
    private String name;
    private double height;
    private double width;
    private double depth;

    public Product() {}

    public Product(String id,String name, double height, double width, double depth) {
        this.id = id;
        this.name = name;
        this.height = height;
        this.width = width;
        this.depth = depth;
    }

    public Product(Product that) {  // copy constructor
        this(that.id,that.name, that.height, that.width, that.depth);
    }

    @XmlAttribute(name="id")
    @XmlID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="name")
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }


    @XmlAttribute(name="height")
    public double getHeight() {
        return height;
    }
    public void setHeight(double height) {
        this.height = height;
    }

    @XmlAttribute(name="width")
    public double getWidth() {
        return width;
    }
    public void setWidth(double width) {
        this.width = width;
    }

    @XmlAttribute(name="depth")
    public double getDepth() {
        return depth;
    }
    public void setDepth(double depth) {
        this.depth = depth;
    }


    @Override
    public boolean equals (Object that) {  // Necessary for object stored in collections ( => find, remove, ...)
        return  (this == that || (that instanceof Product && this.id.equals(((Product) that).id)));
    }

    @Override
    public int hashCode(){ return this.id.hashCode(); }

}
