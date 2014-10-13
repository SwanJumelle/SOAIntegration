package fr.unice.polytech.soa1.salle.services.customer.outputs;

import fr.unice.polytech.soa1.salle.business.model.Quote;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlType(propOrder = { "id", "cost", "eta" })
public class QuoteResult extends JobResult {

    private String id;
    private Double cost;
    private Long eta;

    public QuoteResult() {}

    public QuoteResult(Quote quote)
    {
        this.id = quote.getId();
        this.cost = quote.getTransportInformation().getCost();
        this.eta = quote.getTransportInformation().getEta().getTime();
    }

    @XmlElement(name = "quoteId")
    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    @XmlElement
    public Double getCost()           { return cost; }
    public void setCost(Double aCost) { this.cost = aCost; }

    @XmlElement
    public Long getEta()           { return eta; }
    public void setEta(Long anEta) { this.eta = anEta; }

}
