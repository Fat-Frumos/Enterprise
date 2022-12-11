package com.enterprise.rental.utils.locale;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.log4j.Logger;

import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class CurrencyConvector implements ExchangeService {
    private static final Logger log = Logger.getLogger(CurrencyConvector.class);
    private static final String nbuUrl = "https://bank.gov.ua/NBUStatService/v1/statdirectory/exchange?json";
    private static final Map<String, Double> map = new ConcurrentHashMap<>();


    @Override
    public double exchangeFrom(Double primaryPrice, String exchange) {

        for (Exchange currency : Exchange.values()) {
            if (currency.get().equalsIgnoreCase(exchange)) {
                return round(primaryPrice * map.get(exchange));
            }
        }
        return primaryPrice;
    }

    public CurrencyConvector() {
        updateExchange();
    }

    @Override
    public double exchangeTo(Double primaryPrice, String exchange) {

        for (Exchange currency : Exchange.values()) {
            if (currency.get().equalsIgnoreCase(exchange)) {
                return round(primaryPrice / map.get(exchange));
            }
        }
        return primaryPrice;
    }

    private static Double round(double exchange) {
        return Math.round((exchange) * 100.0) / 100.0;
    }

    @Override
    public void updateExchange() {
        try {
            log.info("Update exchange");
            ObjectMapper objectMapper = new ObjectMapper();
            URL rates = new URL(nbuUrl);

            List<NbuRate> nbuRates = objectMapper.readValue(
                    rates.openConnection().getInputStream(),
                    new TypeReference<>() {
                    });

            nbuRates.forEach(nbuRate -> map.put(nbuRate.getCc(), nbuRate.getRate()));
        } catch (Exception exception) {
            log.error("Could not updating currency rates: ", exception);
        }
    }
}