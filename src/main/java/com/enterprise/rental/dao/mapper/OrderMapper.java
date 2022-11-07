package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.dao.jdbc.JdbcOrderDao;
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
            String reason = resultSet.getString("reason")
                    == null
                    ? ""
                    : resultSet.getString("reason");


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

//            String rejected = resultSet.getString("rejected") == null
//                    ? "off" : resultSet.getString("rejected");

//            String closed = resultSet.getString("driver") == null
//                    ? "off" : resultSet.getString("driver");

            Order order = new Order.Builder()
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
//            log.info("Order" + order);
            return order;
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

        String[] fields = {"orderId", "carId", "userId", "passport", "reason", "phone", "damage", "driver", "rejected", "closed"};
        Map<String, String> params = new HashMap<>();
        for (String key : fields) {
            if (!"".equals(request.getParameter(key))
                    && request.getParameter(key) != null) {
                params.put(key, request.getParameter(key));
            }
        }

        String orderId = params.get("orderId");
        String carId = params.get("carId");
        String userId = params.get("userId");
        String passport = params.get("passport");
        String reason = params.get("reason");
        String phone = params.get("phone");
        String damage = params.get("damage");

        String rejected = request.getParameter("rejected") == null
                ? "off" : request.getParameter("rejected");
        String closed = request.getParameter("closed") == null
                ? "off" : request.getParameter("closed");
//        String driver = request.getParameter("driver") == null
//                ? "off" : request.getParameter("driver");
        boolean driver = Boolean.parseBoolean(request.getParameter("driver"));
        Double payment = Double.valueOf(request.getParameter("payment"));
        Timestamp term = Timestamp.valueOf(request.getParameter("term"));
        Timestamp created = Timestamp.valueOf(request.getParameter("created"));

        return new Order.Builder()
                .orderId(Long.parseLong(orderId))
                .userId(Long.parseLong(userId))
                .carId(Long.parseLong(carId))
                .payment(payment)
                .passport(passport)
                .reason(reason)
                .phone(phone)
                .damage(damage)
                .created(created)
                .term(term)
                .driver(driver)
                .rejected(rejected.equals("on"))
                .closed(closed.equals("on"))
                .build();
    }
}