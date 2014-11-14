package fr.unice.polytech.soa1.fedps.bdd.model.units;

public enum UnitSize {

    CM("Centimeter"),
    INCH("Inch");

    private String label;

    UnitSize(String aLabel)
    {
        this.label = aLabel;
    }

    public String getLabel()
    {
        return label;
    }

}
