package fr.unice.polytech.soa1.salle.business.model.units;

/**
 * Enumeration of units weight.
 *
 * @author victorsalle
 */
public enum UnitWeight {

    KG("Kilogram"),
    LBS("Pound");

    private String label;

    UnitWeight(String aLabel)
    {
        this.label = aLabel;
    }

    public String getLabel()
    {
        return label;
    }


}
