package com.enterprise.rental.utils.locale;

public interface ExchangeService {
    double exchangeFrom(Double primaryPrice, String exchange);

    double exchangeTo(Double primaryPrice, String exchange);

    void updateExchange();
}