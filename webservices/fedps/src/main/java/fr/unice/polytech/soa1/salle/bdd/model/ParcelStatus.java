package fr.unice.polytech.soa1.salle.bdd.model;

/**
 * Enumeration of parcel status.
 *
 * @author victorsalle
 */
public enum ParcelStatus {

    CUSTOMS("Customs", -1), // particular state, can occur "whenever"
    AWAITING_PICK_UP("Awaiting pickup", 0),
    PICKED_UP("Picked up", 1),
    ARRIVAL_SCAN("Arrival scan", 2),
    STORAGE("Storage", 3),
    IN_TRANSIT("In transit", 4),
    DELIVERED("Delivered", 5);

    private String label;
    private Integer order;

    ParcelStatus(String aLabel, Integer anOrder)
    {
        this.label = aLabel;
        this.order = anOrder;
    }

    public boolean before(ParcelStatus status)
    {
        return this.getOrder() < status.getOrder();
    }

    public String getLabel() { return label; }
    public Integer getOrder() { return order; }

}
