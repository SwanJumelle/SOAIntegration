package fr.unice.polytech.soa1.creditgeneral.doc.outputs;

import fr.unice.polytech.soa1.creditgeneral.business.Retailer;

import javax.xml.bind.annotation.XmlSeeAlso;

// Support polymorphism at the XML serialization level
@XmlSeeAlso({	Information.class, Retailer.class,
				ListOfRetailers.class, ListOfInformation.class, ListOfTransactions.class	})
public abstract class JobResult {}
