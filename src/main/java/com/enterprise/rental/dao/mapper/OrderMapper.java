package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderMapper extends Mapper<Order> {
    private static final Logger log = Logger.getLogger(OrderMapper.class);

    public Order mapRow(ResultSet resultSet) {

        try {
            long orderId = resultSet.getLong("order_id");
            long userId = resultSet.getLong("user_id");
            long carId = resultSet.getLong("car_id");
            boolean rejected = resultSet.getBoolean("rejected");
            boolean closed = resultSet.getBoolean("closed");
            Double payment = resultSet.getDouble("payment");
            String passport = resultSet.getString("passport");
            String damage = resultSet.getString("damage");
            String phone = resultSet.getString("phone");
            String reason = resultSet.getString("reason");
            String create = resultSet.getString("created");
            Timestamp created = create
                    != null
                    ? Timestamp.valueOf(create)
                    : new Timestamp(System.currentTimeMillis());


            String term = resultSet.getString("term");
            Timestamp timestamp = term
                    != null
                    ? Timestamp.valueOf(term)
                    : new Timestamp(System.currentTimeMillis());

            String driver = resultSet.getString("driver")
                    == null
                    ? "off"
                    : resultSet.getString("driver");

            return new Order.Builder()
                    .orderId(orderId)
                    .userId(userId)
                    .carId(carId)
                    .payment(payment)
                    .passport(passport)
                    .reason(reason)
                    .phone(phone)
                    .damage(damage)
                    .created(created)
                    .term(timestamp)
                    .driver(driver.equals("on"))
                    .rejected(rejected)
                    .closed(closed)
                    .build();

        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException(e.getMessage());
            }
            throw new OrderNotFoundException(exception.getMessage());
        }
    }

    public Order orderMapper(HttpServletRequest request) {

        String[] fields = {"orderId", "carId", "userId", "passport", "reason", "phone", "damage", "payment", "created", "term"};

        Map<String, String> params = new HashMap<>();
        for (String key : fields) {
            if (!"".equals(request.getParameter(key))
                    && request.getParameter(key) != null) {
                params.put(key, request.getParameter(key));
            }
        }

        long orderId = params.get("orderId") != null
                ? Long.parseLong(params.get("orderId"))
                : UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;

        long carId = Long.parseLong(params.get("carId"));
        long userId = Long.parseLong(params.get("userId"));

        String passport = params.get("passport");
        String reason = params.get("reason");
        String phone = params.get("phone");
        String damage = params.get("damage");
        String payment = params.get("payment");
        String createTimestamp = params.get("created");
        String timestamp = params.get("term");

        boolean closed = request.getParameter("closed") != null;
        boolean rejected = request.getParameter("rejected") != null;
        boolean driver = request.getParameter("driver") != null;

        double pay = payment != null
                ? Double.parseDouble(payment)
                : 0;

        Timestamp created = createTimestamp == null
                ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(createTimestamp);

        Timestamp term = createTimestamp == null
                ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(timestamp);

        return new Order.Builder()
                .orderId(orderId)
                .userId(userId)
                .carId(carId)
                .payment(pay)
                .passport(passport)
                .reason(reason)
                .phone(phone)
                .damage(damage)
                .created(created)
                .term(term)
                .driver(driver)
                .rejected(rejected)
                .closed(closed)
                .build();
    }
}