package fr.unice.polytech.soa1.fedps.services.business;

import fr.unice.polytech.soa1.fedps.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.fedps.bdd.model.Parcel;
import fr.unice.polytech.soa1.fedps.bdd.model.ParcelStatus;
import fr.unice.polytech.soa1.fedps.bdd.model.Quote;
import fr.unice.polytech.soa1.fedps.services.order.output.BadJobFault;
import fr.unice.polytech.soa1.fedps.services.order.output.JobResult;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Optional;

@XmlType(name = "parcel_from_quote")
public class ParcelFromQuoteInput {

    private String quoteId;
    private String cardNumber;
    private String sender;
    private String senderEmail;
    private String receiver;
    private Long pickup;
    private String fromStreetNb;
    private String fromStreetName;
    private String toStreetNb;
    private String toStreetName;

    public ParcelFromQuoteInput() {}

    // ******
    // Method
    // ******

    public void check(DataAccessObject dao) throws BadJobFault
    {
        if (quoteId == null || cardNumber == null || sender == null || senderEmail == null || receiver == null
                || fromStreetNb == null || fromStreetName == null || toStreetNb == null || toStreetName == null)
        {
            throw new BadJobFault("Null data provided!");
        }

        if (pickup != null && new Date(pickup).before(new Date()))
        {
            throw new BadJobFault("The pickup date must be after now.");
        }

        Optional<Quote> quote = dao.findQuoteById(quoteId);
        if (!quote.isPresent())
        {
            throw new BadJobFault("Quote not found.");
        }

        if ((pickup == null && quote.get().getTransportInformation().getPickup().before(new Date()))
                || (pickup != null && new Date(pickup).before(new Date())))
        {
            throw new BadJobFault("The pickup date must be after now.");
        }

        if (quote.get().isTooOldToBeConsidered())
        {
            throw new BadJobFault("Quote is too old to be considered.");
        }
    }

    public OrderOutput run(DataAccessObject dao) throws BadJobFault
    {
        Parcel parcel = new Parcel();

        Quote quote = dao.findQuoteById(quoteId).get();

        quote.getTransportInformation().setSender(sender);
        quote.getTransportInformation().setSenderEmail(sender);
        quote.getTransportInformation().setReceiver(receiver);
        quote.getTransportInformation().getFromAddress().setStreetNb(fromStreetNb);
        quote.getTransportInformation().getFromAddress().setStreetName(fromStreetName);
        quote.getTransportInformation().getToAddress().setStreetNb(toStreetNb);
        quote.getTransportInformation().getToAddress().setStreetName(toStreetName);

        quote.getTransportInformation().setPickup(new Date(pickup));
        quote.getTransportInformation().computeETA();

        parcel.setStatus(ParcelStatus.AWAITING_PICK_UP);
        parcel.setTransportInformation(quote.getTransportInformation());

        dao.getParcels().add(parcel);

        return new OrderOutput(parcel);
    }

    // *****************
    // Getters & setters
    // *****************

    @XmlElement
    public String getQuoteId()             { return quoteId; }
    public void setQuoteId(String quoteId) { this.quoteId = quoteId; }

    @XmlElement
    public String getCardNumber()                { return cardNumber; }
    public void setCardNumber(String cardNumber) { this.cardNumber = cardNumber; }

    @XmlElement
    public String getSender()            { return sender; }
    public void setSender(String sender) { this.sender = sender; }

    @XmlElement
    public String getSenderEmail()                 { return senderEmail; }
    public void setSenderEmail(String senderEmail) { this.senderEmail = senderEmail; }

    @XmlElement
    public String getReceiver()              { return receiver; }
    public void setReceiver(String receiver) { this.receiver = receiver; }

    @XmlElement
    public Long getPickup()            { return pickup; }
    public void setPickup(Long pickup) { this.pickup = pickup; }

    @XmlElement
    public String getFromStreetNb()                  { return fromStreetNb; }
    public void setFromStreetNb(String fromStreetNb) { this.fromStreetNb = fromStreetNb; }

    @XmlElement
    public String getFromStreetName()                    { return fromStreetName; }
    public void setFromStreetName(String fromStreetName) { this.fromStreetName = fromStreetName; }

    @XmlElement
    public String getToStreetNb()                { return toStreetNb; }
    public void setToStreetNb(String toStreetNb) { this.toStreetNb = toStreetNb; }

    @XmlElement
    public String getToStreetName()                  { return toStreetName; }
    public void setToStreetName(String toStreetName) { this.toStreetName = toStreetName; }

}
