package fr.unice.polytech.soa1.warehouse.business;

/**
 * Created by Hugo on 19/10/2014.
 */

import javax.xml.bind.annotation.*;
import javax.xml.bind.annotation.XmlAttribute;
import java.util.ArrayList;
import java.util.List;

@XmlType
@XmlRootElement(name = "warehouse")
public class Warehouse {
    private String adress;
    private List<Employee> employees;
    private List<Box> boxes;
    private List<Event> events;


    public Warehouse() {}

    public Warehouse(String adress) {
        this.adress = adress;

    }

    public Warehouse(Warehouse that) {  // copy constructor
        this(that.adress);
        for(Employee e: that.employees) {
            Employee clone = new Employee(e);
            this.getEmployees().add(clone);
        }
        for(Box b: that.boxes) {
            Box clone = new Box(b);
            this.getBoxes().add(clone);
        }
        for(Event e: that.events) {
            Event clone = new Event(e);
            this.getEvents().add(clone);
        }

    }

    @XmlAttribute(name="adress")
    @XmlID
    public String getAdress() {
        return adress;
    }
    public void setAdress(String adress) {
        this.adress = adress;
    }

    @XmlElementWrapper(name = "employees")
    @XmlElement(name = "employees")
    public List<Employee> getEmployees() {
        return employees;
    }
    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }

    @XmlElementWrapper(name = "boxes")
    @XmlElement(name = "boxes")
    public List<Box> getBoxes() {
        return boxes;
    }
    public void setBoxes(List<Box> boxes) {
        this.boxes = boxes;
    }

    @XmlElementWrapper(name = "events")
    @XmlElement(name = "events")
    public List<Event> getEvents() {
        return events;
    }
    public void setEvents(List<Event> events) {
        this.events = events;
    }

    @Override
    public boolean equals (Object that) {  // Necessary for object stored in collections ( => find, remove, ...)
        return  (this == that || (that instanceof Warehouse && this.adress.equals(((Warehouse) that).adress)));
    }

    @Override
    public int hashCode(){ return this.adress.hashCode(); }

}
