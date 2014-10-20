package fr.unice.polytech.soa1.salle.services.order;

import fr.unice.polytech.soa1.salle.bdd.dao.DataAccessObject;
import fr.unice.polytech.soa1.salle.bdd.model.*;
import fr.unice.polytech.soa1.salle.services.business.*;
import fr.unice.polytech.soa1.salle.services.order.input.OrderInput;
import fr.unice.polytech.soa1.salle.services.order.output.BadJobFault;
import fr.unice.polytech.soa1.salle.services.order.output.JobResult;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebService;
import java.util.Date;

@Stateless(name = "FedPS-Order")
@WebService(targetNamespace = "http://informatique.polytech.unice.fr/soa1/salle/services/order",
        portName = "FedPSOrderPort",
        serviceName = "FedPSOrderService",
        endpointInterface = "fr.unice.polytech.soa1.salle.services.order.Order")
public class OrderImpl implements Order {

    @EJB private DataAccessObject dao;

    @Override
    public JobResult dispatch(OrderInput input) throws BadJobFault
    {
        return input.process(dao);
    }
/*
    @Override
    public QuoteOutput processQuote(QuoteInput input) throws BadJobFault
    {
        input.check();

        Quote quote = new Quote();
        TransportInformation transportInformation = new TransportInformation();

        Address fromAddress = new Address();
        fromAddress.setZipCode(input.getFromZipCode());
        fromAddress.setCountryCode(input.getFromCountryCode());
        fromAddress.fillCityWithZipCode();
        Address toAddress = new Address();
        toAddress.setZipCode(input.getToZipCode());
        toAddress.setCountryCode(input.getToCountryCode());
        toAddress.fillCityWithZipCode();

        transportInformation.setFromAddress(fromAddress);
        transportInformation.setToAddress(toAddress);
        transportInformation.setPickup(new Date(input.getPickup()));
        transportInformation.setShipping(input.getShipping());
        transportInformation.setCurrency(input.getCurrency());
        transportInformation.setWidth(input.getWidth());
        transportInformation.setHeight(input.getHeight());
        transportInformation.setDepth(input.getHeight());
        transportInformation.setUnitSize(input.getUnitSize());
        transportInformation.setWeight(input.getWeight());
        transportInformation.setUnitWeight(input.getUnitWeight());
        transportInformation.computeETA();

        double fakeCost = (input.getWeight() + input.getHeight() + input.getDepth() + input.getWeight()) / 5;
        transportInformation.setCost(fakeCost);

        quote.setTransportInformation(transportInformation);

        dao.getQuotes().add(quote);

        return new QuoteOutput(quote);
    }*/

/*
    @Override
    public OrderOutput processOrderFromQuote(ParcelFromQuoteInput input) throws BadJobFault
    {
        input.check(dao);

        Parcel parcel = new Parcel();

        Quote quote = dao.findQuoteById(input.getQuoteId()).get();

        quote.getTransportInformation().setSender(input.getSender());
        quote.getTransportInformation().setSenderEmail(input.getSenderEmail());
        quote.getTransportInformation().setReceiver(input.getReceiver());
        quote.getTransportInformation().getFromAddress().setStreetNb(input.getFromStreetNb());
        quote.getTransportInformation().getFromAddress().setStreetName(input.getFromStreetName());
        quote.getTransportInformation().getToAddress().setStreetNb(input.getToStreetNb());
        quote.getTransportInformation().getToAddress().setStreetName(input.getToStreetName());

        quote.getTransportInformation().setPickup(new Date(input.getPickup()));
        quote.getTransportInformation().computeETA();

        parcel.setStatus(ParcelStatus.AWAITING_PICK_UP);
        parcel.setTransportInformation(quote.getTransportInformation());

        dao.getParcels().add(parcel);

        return new OrderOutput(parcel);
    }
*/

/*    @Override
    public OrderOutput processOrder(ParcelIO parcelIO) throws BadJobFault
    {
        Parcel parcel = new Parcel();
        TransportInformation transportInformation = new TransportInformation();

        // %% Process payment using cardNumber %% //

        Address fromAddress = new Address();
        fromAddress.setStreetNb(parcelIO.getFrom().getStreetNb());
        fromAddress.setStreetName(parcelIO.getFrom().getStreetName());
        fromAddress.setZipCode(parcelIO.getFrom().getZipCode());
        fromAddress.setCountryCode(parcelIO.getFrom().getCountryCode());
        fromAddress.fillCityWithZipCode();
        Address toAddress = new Address();
        toAddress.setStreetNb(parcelIO.getTo().getStreetNb());
        toAddress.setStreetName(parcelIO.getTo().getStreetName());
        toAddress.setZipCode(parcelIO.getTo().getZipCode());
        toAddress.setCountryCode(parcelIO.getTo().getCountryCode());
        toAddress.fillCityWithZipCode();

        transportInformation.setSender(parcelIO.getSender());
        transportInformation.setSenderEmail(parcelIO.getSenderEmail());
        transportInformation.setReceiver(parcelIO.getReceiver());
        transportInformation.setFromAddress(fromAddress);
        transportInformation.setToAddress(toAddress);
        transportInformation.setPickup(new Date(parcelIO.getPickup()));
        transportInformation.setShipping(parcelIO.getShipping());
        transportInformation.setCurrency(parcelIO.getCurrency());
        transportInformation.setWidth(parcelIO.getWidth());
        transportInformation.setHeight(parcelIO.getHeight());
        transportInformation.setDepth(parcelIO.getDepth());
        transportInformation.setUnitSize(parcelIO.getUnitSize());
        transportInformation.setWeight(parcelIO.getWeight());
        transportInformation.setUnitWeight(parcelIO.getUnitWeight());
        transportInformation.computeETA();

        parcel.setTransportInformation(transportInformation);
        parcel.setStatus(ParcelStatus.AWAITING_PICK_UP);

        dao.getParcels().add(parcel);

        return new OrderOutput(parcel);
    }*/

}
