package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Map;

public class OrderMapper extends Mapper<Order> {
    public Order mapRow(ResultSet resultSet) {
        try {
            long orderId = resultSet.getLong("order_id");
            long userId = resultSet.getLong("user_id");
            long carId = resultSet.getLong("car_id");

            Double payment = resultSet.getDouble("payment");
            String passport = resultSet.getString("passport");
            String damage = resultSet.getString("damage");
            String phone = resultSet.getString("phone");
            Timestamp term = Timestamp.valueOf(resultSet.getString("term"));
            Timestamp created = Timestamp.valueOf(resultSet.getString("created"));

            String rejected = resultSet.getString("rejected") == null
                    ? "off" : resultSet.getString("rejected");

            String closed = resultSet.getString("driver") == null
                    ? "off" : resultSet.getString("driver");

            String driver = resultSet.getString("driver") == null
                    ? "off" : resultSet.getString("driver");

            return new Order.Builder()
                    .orderId(orderId)
                    .userId(userId)
                    .carId(carId)
                    .payment(payment)
                    .passport(passport)
                    .phone(phone)
                    .damage(damage)
                    .created(created)
                    .term(term)
                    .driver(driver.equals("on"))
                    .rejected(rejected.equals("on"))
                    .closed(closed.equals("on"))
                    .build();
        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                throw new DataException(e);
            }
            throw new OrderNotFoundException(exception.getMessage());
        }
    }

    public Order orderMapper(HttpServletRequest request) {

        String[] fields = {"orderId", "carId", "userId", "passport", "phone", "damage", "driver", "rejected", "closed"};
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
        String phone = params.get("phone");
        String damage = params.get("damage");

        String rejected = request.getParameter("rejected") == null
                ? "off" : request.getParameter("rejected");
        String closed = request.getParameter("closed") == null
                ? "off" : request.getParameter("closed");
        String driver = request.getParameter("driver") == null
                ? "off" : request.getParameter("driver");

        Double payment = Double.valueOf(request.getParameter("payment"));
        Timestamp term = Timestamp.valueOf(request.getParameter("term"));
        Timestamp created = Timestamp.valueOf(request.getParameter("created"));

        return new Order.Builder()
                .orderId(Long.parseLong(orderId))
                .userId(Long.parseLong(userId))
                .carId(Long.parseLong(carId))
                .payment(payment)
                .passport(passport)
                .phone(phone)
                .damage(damage)
                .created(created)
                .term(term)
                .driver(driver.equals("on"))
                .rejected(rejected.equals("on"))
                .closed(closed.equals("on"))
                .build();
    }
}