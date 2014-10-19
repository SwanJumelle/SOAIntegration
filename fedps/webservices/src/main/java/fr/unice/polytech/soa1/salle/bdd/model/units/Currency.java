package fr.unice.polytech.soa1.salle.bdd.model.units;

/**
 * Enumeration of currencies.
 *
 * @author victorsalle
 */
public enum Currency {

    USD("US dollars"),
    EUR("Euros"),
    GBP("Pounds sterling");

    private String label;

    Currency(String aLabel)
    {
        this.label = aLabel;
    }

    public String getLabel()
    {
        return label;
    }

}
