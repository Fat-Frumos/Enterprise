package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class OrderMapper extends Mapper<Order> {
    private static final Logger log = Logger.getLogger(OrderMapper.class);

    public Order mapRow(ResultSet resultSet) {
//        String[] mapRow = {"orderId", "carId", "userId", "payment", "passport", "reason", "phone", "damage", "created", "term", "driver"};
        try {
            long orderId = resultSet.getLong("order_id");
            long carId = resultSet.getLong("car_id");
            long userId = resultSet.getLong("user_id");
            boolean rejected = resultSet.getBoolean("rejected");
            boolean closed = resultSet.getBoolean("closed");
            Double payment = resultSet.getDouble("payment");

            String passport = resultSet.getString("passport");
            String reason = resultSet.getString("reason");
            String phone = resultSet.getString("phone");
            String damage = resultSet.getString("damage");
            String create = resultSet.getString("created");
            String term = resultSet.getString("term");

            Timestamp created = create
                    != null
                    ? Timestamp.valueOf(create)
                    : new Timestamp(System.currentTimeMillis());

            Timestamp timestamp = term != null ? Timestamp.valueOf(term) : null;

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

        String[] fields = {"orderId", "carId", "userId", "passport", "reason", "phone", "damage", "payment", "created"};

        Map<String, String> params = new HashMap<>();
        for (String key : fields) {
            if (!"".equals(request.getParameter(key))
                    && request.getParameter(key) != null) {
                params.put(key, request.getParameter(key));
            }
        }

        long orderId = params.get(fields[0]) != null
                ? Long.parseLong(params.get(fields[0]))
                : UUID.randomUUID().getMostSignificantBits() & 0x7ffffffL;

        long carId = Long.parseLong(params.get(fields[1]));
        long userId = Long.parseLong(params.get(fields[2]));

        String passport = params.get(fields[3]);
        String reason = params.get(fields[4]);
        String phone = params.get(fields[5]);
        String damage = params.get(fields[6]);
        String payment = params.get(fields[7]);
        String createTimestamp = params.get(fields[8]);
        String timestamp = request.getParameter("term");

        if (timestamp.length() == 10) {
            timestamp += " 00:00:00.0";
        }

        Timestamp term = Timestamp.valueOf(timestamp);

        boolean closed = request.getParameter("closed") != null;
        boolean rejected = request.getParameter("rejected") != null;
        boolean driver = request.getParameter("driver") != null;

        double pay = payment != null
                ? Double.parseDouble(payment)
                : 0;

        Timestamp created = createTimestamp == null
                ? new Timestamp(System.currentTimeMillis())
                : Timestamp.valueOf(createTimestamp);

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