package fr.unice.polytech.soa1.salle.services.customer.inputs;

import fr.unice.polytech.soa1.salle.business.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.business.model.Parcel;
import fr.unice.polytech.soa1.salle.business.model.ParcelStatus;
import fr.unice.polytech.soa1.salle.business.model.Quote;
import fr.unice.polytech.soa1.salle.services.customer.outputs.JobResult;
import fr.unice.polytech.soa1.salle.services.customer.outputs.Processed;
import fr.unice.polytech.soa1.salle.services.io.AddressIO;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.util.Date;
import java.util.Optional;

@XmlType(name = "parcel-from-quote")
public class ParcelFromQuote extends CustomerInput {

    private String quoteId;
    private String cardNumber;
    private String sender;
    private String senderEmail;
    private String receiver;
    private Long pickup;
    private AddressIO from;
    private AddressIO to;

    public ParcelFromQuote() {}

    // *******
    // Methods
    // *******

    @Override
    protected JobResult run(DataAccessObject dao) throws BadJobFault
    {
        Parcel parcel = new Parcel();

        Quote quote = dao.findQuoteById(quoteId).get();

        quote.getTransportInformation().setSender(sender);
        quote.getTransportInformation().setSenderEmail(senderEmail);
        quote.getTransportInformation().setReceiver(receiver);
        quote.getTransportInformation().getFromAddress().setStreetNb(from.getStreetNb());
        quote.getTransportInformation().getFromAddress().setStreetName(from.getStreetName());
        quote.getTransportInformation().getToAddress().setStreetNb(to.getStreetNb());
        quote.getTransportInformation().getToAddress().setStreetName(to.getStreetName());

        quote.getTransportInformation().setPickup(new Date(pickup));
        quote.getTransportInformation().computeETA();

        parcel.setStatus(ParcelStatus.AWAITING_PICK_UP);
        parcel.setTransportInformation(quote.getTransportInformation());

        dao.getParcels().add(parcel);

        return new Processed(parcel);
    }

    @Override
    protected void check(DataAccessObject dao) throws BadJobFault
    {
        if (quoteId == null || cardNumber == null || sender == null || senderEmail == null || receiver == null
                || !from.isOkForParcelFromQuote() || !to.isOkForParcelFromQuote())
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
    public AddressIO getFrom()          { return from; }
    public void setFrom(AddressIO from) { this.from = from; }

    @XmlElement
    public AddressIO getTo()        { return to; }
    public void setTo(AddressIO to) { this.to = to; }

}
