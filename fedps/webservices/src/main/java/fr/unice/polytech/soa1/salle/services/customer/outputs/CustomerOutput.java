package fr.unice.polytech.soa1.salle.services.customer.outputs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType
public class CustomerOutput {

    private List<JobResult> results;

    public CustomerOutput() {}

    @XmlElementWrapper(name = "results")
    @XmlElement(name = "item")
    public List<JobResult> getResults()                 { return results; }
    public void setResults(List<JobResult> someResults) { this.results = someResults; }

}
