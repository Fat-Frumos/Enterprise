package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.Order;
import com.enterprise.rental.exception.DataException;
import com.enterprise.rental.exception.OrderNotFoundException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

public class OrderMapper extends Mapper<Order> {
    public Order mapRow(ResultSet resultSet) {
        try {
            long orderId = resultSet.getLong("order_id");
            long userId = resultSet.getLong("user_id");
            long carId = resultSet.getLong("car_id");
            Double payment = resultSet.getDouble("payment");
            String passport = resultSet.getString("passport");
            String damage = resultSet.getString("damage");
            String card = resultSet.getString("card");
            Timestamp term = Timestamp.valueOf(resultSet.getString("term"));
            Timestamp created = Timestamp.valueOf(resultSet.getString("created"));
            boolean driver = Boolean.parseBoolean((resultSet.getString("driver")));

            return new Order.Builder()
                    .orderId(orderId)
                    .userId(userId)
                    .carId(carId)
                    .payment(payment)
                    .passport(passport)
                    .card(card)
                    .damage(damage)
                    .driver(driver)
                    .created(created)
                    .term(term)
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
}