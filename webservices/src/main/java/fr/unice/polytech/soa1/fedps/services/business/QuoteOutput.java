package fr.unice.polytech.soa1.fedps.services.business;

import fr.unice.polytech.soa1.fedps.bdd.model.Quote;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.xml.bind.annotation.*;

@XmlType(propOrder = { "id", "eta", "cost"})
@XmlRootElement(name = "quote")
public class QuoteOutput extends JobResult {

    private String id;
    private Double cost;
    private Long eta;

    public QuoteOutput() {}

    public QuoteOutput(Quote quote)
    {
        this.id = quote.getId();
        this.cost = quote.getTransportInformation().getCost();
        this.eta = quote.getTransportInformation().getEta().getTime();
    }

    @XmlElement
    public String getId()          { return id; }
    public void setId(String anId) { this.id = anId; }

    @XmlElement
    public Double getCost()           { return cost; }
    public void setCost(Double aCost) { this.cost = aCost; }

    @XmlElement
    public Long getEta()           { return eta; }
    public void setEta(Long anEta) { this.eta = anEta; }

}
