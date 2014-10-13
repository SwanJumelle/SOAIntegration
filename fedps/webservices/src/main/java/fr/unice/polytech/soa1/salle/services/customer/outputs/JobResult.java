package fr.unice.polytech.soa1.salle.services.customer.outputs;

import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({ QuoteResult.class, ListOfFollow.class, Processed.class })
public abstract class JobResult {}
