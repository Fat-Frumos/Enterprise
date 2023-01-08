package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.enterprise.rental.service.locale.CurrencyConvector.exchange;

/**
 * Class OrderMapper extends Mapper(User).
 * <p>
 * It used for mapping data from HttpServletRequest and ResultSet.
 * Builds Entity Order
 *
 * @author Pasha Pollack
 * @see Mapper#mapRow(ResultSet)
 */
public class OrderMapper extends Mapper<Order> {
    private static final Logger LOGGER = LogManager.getLogger();
    private static final String[] orderFields = {"orderId", "carId", "userId", "passport", "reason", "phone", "damage", "payment", "created", "driver"};

    /**
     * <p>Get a parameters to the servlet container <code>HttpServletRequest</code>
     * set to instance <code>Order</code>
     *
     * @param request HttpServletRequest
     * @return an instance of the type of class Order by Builder
     */
    @Override
    public Order mapper(HttpServletRequest request) {

        String language = request.getParameter("language");

        double pay = language == null || "en".equals(language)
                ? Double.parseDouble(request.getParameter(orderFields[7]))
                : Math.round((Double.parseDouble(request.getParameter(orderFields[7])) / exchange) * 100) / 100.0;

        Map<String, String> params = new HashMap<>();
        for (String key : orderFields) {
            if (!"".equals(request.getParameter(key))
                    && request.getParameter(key) != null) {
                params.put(key, request.getParameter(key));
            }
        }

        String timestamp = request.getParameter("term");
        timestamp += timestamp.length() == 10 ? " 00:00:00.0" : "";


        return new Order.Builder()
                .orderId(params.get(orderFields[0]) != null
                        ? Long.parseLong(params.get(orderFields[0]))
                        : UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL)
                .carId(Long.parseLong(params.get(orderFields[1])))
                .userId(Long.parseLong(params.get(orderFields[2])))
                .payment(pay)
                .passport(params.get(orderFields[3]))
                .reason(params.get(orderFields[4]))
                .phone(params.get(orderFields[5]))
                .damage(params.get(orderFields[6]))
                .created(params.get(orderFields[8]) == null
                        ? new Timestamp(System.currentTimeMillis())
                        : Timestamp.valueOf(params.get(orderFields[8])))
                .term(Timestamp.valueOf(timestamp))
                .driver(request.getParameter(orderFields[9]) != null)
                .rejected(request.getParameter("rejected") != null)
                .closed(request.getParameter("closed") != null)
                .build();
    }

    /**
     * Create an instance of the destination class {@link Order}
     *
     * @param resultSet the ResultSet returned by JDBC API
     * @return an instance of the destination type of class
     */
    public Order mapRow(ResultSet resultSet) {
        try {
            return new Order.Builder()
                    .orderId(resultSet.getLong("order_id"))
                    .userId(resultSet.getLong("user_id"))
                    .carId(resultSet.getLong("car_id"))
                    .passport(resultSet.getString(orderFields[3]))
                    .reason(resultSet.getString(orderFields[4]))
                    .phone(resultSet.getString(orderFields[5]))
                    .damage(resultSet.getString(orderFields[6]))
                    .payment(resultSet.getDouble(orderFields[7]))
                    .created(resultSet.getString(orderFields[8])
                            != null
                            ? Timestamp.valueOf(resultSet.getString(orderFields[8]))
                            : new Timestamp(System.currentTimeMillis()))
                    .term(resultSet.getString("term") != null
                            ? Timestamp.valueOf(resultSet.getString("term"))
                            : null)

                    .driver((resultSet.getString(orderFields[9]) == null
                            ? "off"
                            : resultSet.getString(orderFields[9]))
                            .equals("on"))
                    .rejected(resultSet.getBoolean("rejected"))
                    .closed(resultSet.getBoolean("closed"))
                    .build();

        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                LOGGER.log(Level.ERROR, e.getMessage());
            }
            throw new OrderNotFoundException(exception);
        }
    }
}
