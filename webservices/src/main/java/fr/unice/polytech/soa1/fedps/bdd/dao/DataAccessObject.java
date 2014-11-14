package fr.unice.polytech.soa1.fedps.bdd.dao;

import fr.unice.polytech.soa1.fedps.bdd.model.*;
import fr.unice.polytech.soa1.fedps.bdd.model.units.Currency;
import fr.unice.polytech.soa1.fedps.bdd.model.units.UnitSize;
import fr.unice.polytech.soa1.fedps.bdd.model.units.UnitWeight;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ejb.Singleton;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Singleton(name = "FedPS-DB-Mock")
public class DataAccessObject {

    List<Parcel> parcels;
    List<Quote> quotes;

    public List<Parcel> getParcels()                 { return parcels; }
    public void setParcels(List<Parcel> someParcels) { this.parcels = someParcels; }

    public List<Quote> getQuotes()                { return quotes; }
    public void setQuotes(List<Quote> someQuotes) { this.quotes = someQuotes; }

    public DataAccessObject() throws IOException, ParseException
    {
        init();
    }

    public Optional<Parcel> findParcelById(String id)
    {
        return getParcels().stream().filter(p -> p.getId().equals(id)).findFirst();
    }

    public Optional<Quote> findQuoteById(String id)
    {
        return getQuotes().stream().filter(q -> q.getId().equals(id)).findFirst();
    }

    private void init() throws IOException, ParseException
    {
        parcels = new ArrayList<>();
        quotes = new ArrayList<>();

        String quotesFile = "quotes.csv";
        String parcelStatusFile = "parcel_status.csv";
        String roundFile = "round.csv";

        String line = "";
        BufferedReader bufferedReader = null;
        Logger logger = LogManager.getLogger(getClass());
        SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-ddkkmm");
        SimpleDateFormat dateFormatterDot = new SimpleDateFormat("dd.MM.yykkmm");

        bufferedReader = new BufferedReader(new InputStreamReader(getClass().getResourceAsStream(File.separator + quotesFile)));

        boolean firstLine = true;
        int i = 0;
        while ((line = bufferedReader.readLine()) != null)
        {
            if (firstLine)
            {
                firstLine = false;
                continue;
            }

            String[] quote = line.split("\\|");
            //   0: quoteId     1: sender        2: from_street_nb  3: from_street_name   4: from_zip
            //   5: from_city   6: receiver      7: to_street_nb    8: to_street_name     9: to_zip
            //  10: to_city    11: width        12: height         13: depth             14: weight
            //  15: cost       16: pickup_date  17: pickup_hour    18: pickup_minute     19: eta
            //  20: eta_hour   21: eta_minute


            quotes.add(
                new Quote(
                    quote[0], // id
                    new TransportInformation(
                        quote[1], quote[1].replaceAll(" ", "_").toLowerCase() + "@mail.com", quote[6],
                        new Address(quote[2], quote[3], quote[4], quote[5], "US"),
                        new Address(quote[7], quote[8], quote[9], quote[10], "US"),
                        dateFormatter.parse(quote[16] + (quote[17].length() == 1 ? "0" : "") + quote[17] + quote[18]),
                        dateFormatterDot.parse(quote[19] + (quote[20].length() == 1 ? "0" : "") + quote[20] + quote[21]),
                        (i%2 == 0) ? Shipping.STANDARD : Shipping.EXPRESS, Double.parseDouble(quote[15].substring(1)),
                        (i%3 == 0) ? Currency.USD : ((i%3 == 1) ? Currency.EUR : Currency.GBP),
                        Double.parseDouble(quote[11]), Double.parseDouble(quote[12]), Double.parseDouble(quote[13]),
                        UnitSize.CM, Double.parseDouble(quote[14]), (i%2 == 0) ? UnitWeight.KG : UnitWeight.LBS
                    )
                )
            );

            parcels.add(
                new Parcel(
                    quote[0], // id
                    ParcelStatus.values()[i++ % (ParcelStatus.values().length-1)],
                    new TransportInformation(
                            quote[1], quote[1].replaceAll(" ", "_").toLowerCase() + "@mail.com", quote[6],
                            new Address(quote[2], quote[3], quote[4], quote[5], "US"),
                            new Address(quote[7], quote[8], quote[9], quote[10], "US"),
                            dateFormatter.parse(quote[16] + (quote[17].length() == 1 ? "0" : "") + quote[17] + quote[18]),
                            dateFormatterDot.parse(quote[19] + (quote[20].length() == 1 ? "0" : "") + quote[20] + quote[21]),
                            (i%2 == 0) ? Shipping.STANDARD : Shipping.EXPRESS, Double.parseDouble(quote[15].substring(1)),
                            (i%3 == 0) ? Currency.USD : ((i%3 == 1) ? Currency.EUR : Currency.GBP),
                            Double.parseDouble(quote[11]), Double.parseDouble(quote[12]), Double.parseDouble(quote[13]),
                            UnitSize.CM, Double.parseDouble(quote[14]), (i%2 == 0) ? UnitWeight.KG : UnitWeight.LBS
                    )
                )
            );
        }


    }

}
