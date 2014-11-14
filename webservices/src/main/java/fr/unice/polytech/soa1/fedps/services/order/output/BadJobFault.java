package fr.unice.polytech.soa1.fedps.services.order.output;

import javax.xml.ws.WebFault;

@WebFault(targetNamespace = "http://informatique.polytech.unice.fr/soa1/salle/services/order")
public class BadJobFault extends Exception {

    private static final long serialVersionUID = 6872704651136075560L;

    public BadJobFault(String value)
    {
        super("Bad Job: [" + value + "]");
    }

}