package fr.unice.polytech.soa1.salle.services.order.output;

import fr.unice.polytech.soa1.salle.services.business.OrderOutput;
import fr.unice.polytech.soa1.salle.services.business.QuoteOutput;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ QuoteOutput.class, OrderOutput.class })
public abstract class JobResult {}
