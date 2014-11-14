package fr.unice.polytech.soa1.warehouse.business;

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;


/**
 * Created by Hugo on 19/10/2014.
 */

@XmlType
@XmlRootElement(name = "event")
public class Event {
    private String id;
    private Type type;
    private String time;
    private Employee assignedEmp;
    private Product product;
    private int quantity;


    public Event() {}

    public Event(String id,Type type, String time, Product p, int q) {
        this.id = id;
        this.type = type;
        this.time = time;
        this.product = p;
        this.quantity = q;

    }

    public Event(Event that) {  // copy constructor
        this(that.id,that.type, that.time, that.product, that.quantity);
    }

    @XmlAttribute(name="type")
    @XmlID
    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    @XmlAttribute(name="type")
    public Type getType() {
        return type;
    }
    public void setType(Type type) {
        this.type = type;
    }

    @XmlAttribute(name="product")
    public Product getProduct() {
        return product;
    }
    public void setProduct(Product p) {
        this.product = p;
    }

    @XmlAttribute(name="quantity")
    public int getQuantity() {
        return quantity;
    }
    public void setQuantity(int q) {
        this.quantity = q;
    }


    @XmlAttribute(name="time")
    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }

    @XmlAttribute(name="assignedemp")
    public Employee getAssignedEmp() {
        return assignedEmp;
    }
    public void setAssignedEmp(Employee emp) {
        this.assignedEmp = emp;
    }

    @Override
    public boolean equals (Object that) {  // Necessary for object stored in collections ( => find, remove, ...)
        return  (this == that || (that instanceof Event && this.id.equals(((Event) that).id)));
    }

    @Override
    public int hashCode(){ return this.id.hashCode(); }

    public enum Type{
        PICK,STORE;
    }
}
