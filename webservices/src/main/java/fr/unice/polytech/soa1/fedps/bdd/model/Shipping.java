package fr.unice.polytech.soa1.fedps.bdd.model;

/**
 * Enumeration of parcel status.
 *
 * @author victorsalle
 */
public enum Shipping {

    STANDARD("Standard"),
    EXPRESS("Express");

    private String label;

    Shipping(String aLabel)
    {
        this.label = aLabel;
    }

    public String getLabel()
    {
        return label;
    }

}
