package fr.unice.polytech.soa1.salle.services.customer.outputs;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlType;
import java.util.ArrayList;
import java.util.List;

@XmlType
public class ListOfFollow extends JobResult {

    private List<Follow> data;

    public ListOfFollow() {}

    public void addData(String status, Long eta)
    {
        if (data == null)
        {
            data = new ArrayList<>();
        }
        data.add(new Follow(status, eta));
    }

    @XmlElementWrapper(name = "data")
    @XmlElement(name = "information")
    public List<Follow> getData()              { return data; }
    public void setData(List<Follow> someData) { this.data = someData; }

}

class Follow {

    private String status;
    private Long eta;

    public Follow() {}

    public Follow(String aStatus, Long anEta)
    {
        this.status = aStatus;
        this.eta = anEta;
    }

    @XmlElement
    public String getStatus()            { return status; }
    public void setStatus(String status) { this.status = status; }

    @XmlElement
    public Long getEta()         { return eta; }
    public void setEta(Long eta) { this.eta = eta; }

}
