package com.enterprise.rental.service.locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Converter Locale exchange rate to US dollars,
 * and vice versa at the real exchange rate in json format
 *
 * @author Pasha Pollack
 */
public class CurrencyConvector implements ExchangeService {
    private static final Logger log = Logger.getLogger(CurrencyConvector.class);
    private static final String NBU_URL = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Map<String, Double> concurrentHashMap = new ConcurrentHashMap<>();
    public static double exchangeRate;

    /**
     * Default constructor call update Exchange
     * Filled data to concurrent Hash Map
     *
     * @see #updateExchange
     */
    public CurrencyConvector() {
        updateExchange();
        exchangeRate = exchangeMultiply(1D, "USD");
    }

    /**
     * Multiply current exchange by primary Price
     * Read data from concurrentHashMap,
     * Return the multiplied exchange values
     *
     * @param exchange     current exchange
     * @param primaryPrice current primary price of Car
     * @return rounded result values
     */
    public double exchangeMultiply(Double primaryPrice, String exchange) {

        for (Exchange currency : Exchange.values()) {
            if (currency.get().equalsIgnoreCase(exchange)) {
                exchangeRate = concurrentHashMap.get(exchange);
                return round(primaryPrice * exchangeRate);
            }
        }
        return primaryPrice;
    }

    /**
     * Divide current exchange by primary Price
     * Return the divided exchange values
     *
     * @param exchange     current exchange
     * @param primaryPrice current primary price of Car
     * @return rounded result values
     */
    @Override
    public double exchangeDivide(Double primaryPrice, String exchange) {

        for (Exchange currency : Exchange.values()) {
            if (currency.get().equalsIgnoreCase(exchange)) {
                return round(primaryPrice / concurrentHashMap.get(exchange));
            }
        }
        return primaryPrice;
    }

    /**
     * Current exchange round by {@link Math#round}
     * Return the formatted string using the specified locale,
     * represented rounded by 2 digits after decimal point in java
     *
     * @param exchange current exchange
     * @return rounded result values
     */
    private static Double round(double exchange) {
        return Math.round((exchange) * 100.0) / 100.0;
    }

    /**
     * Updated course from NBUSStatService used by URLConnection
     * This represents a connection to the remote object referred to by the URL.
     * Returns the input data stream that is read from the open connection creating an instance of the class.
     * Created List NbuRate from object
     * Parses ObjectMapper as NbuRate instance
     * Puts data in Concurrent Hash Map {key:String, value:Double}
     * If NBUSStatService not available caught IOException using logger
     *
     * @see NbuRate the String representation
     */
    @Override
    public void updateExchange() {
        try {
            log.info("Update exchange");
            ObjectMapper objectMapper = new ObjectMapper();
            URL rates = new URL(NBU_URL);

            List<NbuRate> nbuRates = objectMapper.readValue(
                    rates.openConnection().getInputStream(),
                    new TypeReference<>() {
                    });
            nbuRates.forEach(nbuRate -> concurrentHashMap.put(nbuRate.getCc(), nbuRate.getRate()));
        } catch (IOException exception) {
            log.error("Could not updating currency rates: ", exception);
        }
    }
}