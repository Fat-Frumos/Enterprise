package com.enterprise.rental.service.locale;

/**
 * The service interface for Currency Exchange Service.
 * A subclass of <code>HttpServlet</code> must override three methods:
 * <ul>
 * <li> <code>updateExchange</code>, Updating currency rates from the NBU rate
 * <li> <code>exchangeMultiply</code>, Multiply exchange by currency
 * <li> <code>exchangeDivide</code>, Divide currency by exchange
 * </ul>
 *
 * @author Pasha Polyak
 * @see CurrencyConvector
 */
public interface ExchangeService {
    /**
     * Multiply exchange by currency
     *
     * @param primaryPrice the primary rate
     * @param exchange     the exchange
     * @return new exchange price
     */
    double exchangeMultiply(Double primaryPrice, String exchange);

    /**
     * Divide exchange by currency
     *
     * @param primaryPrice the primary rate
     * @param exchange     the exchange
     * @return new exchange price
     */
    double exchangeDivide(Double primaryPrice, String exchange);

    /**
     * Fetches the exchange data from Nbu Rate server
     * Update the exchange into the map
     *
     * @see CurrencyConvector
     */
    void updateExchange();
}