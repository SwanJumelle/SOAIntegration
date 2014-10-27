package fr.unice.polytech.soa1.warehouse.business;

/**
 * Created by Hugo on 19/10/2014.
 */

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;

@XmlType
@XmlRootElement(name = "employee")
public class Employee {

    private String id;
    private String name;


    public Employee() {}

    public Employee(String id, String name) {
        this.id = id;
        this.name = name;

    }

    public Employee(Employee that) {  // copy constructor
        this(that.id, that.name);
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

    @Override
    public boolean equals (Object that) {  // Necessary for object stored in collections ( => find, remove, ...)
        return  (this == that || (that instanceof Employee && this.id.equals(((Employee) that).id)));
    }

    @Override
    public int hashCode(){ return this.id.hashCode(); }

}
