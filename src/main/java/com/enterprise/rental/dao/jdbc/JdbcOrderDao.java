package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.OrderDao;
import com.enterprise.rental.dao.mapper.OrderMapper;
import com.enterprise.rental.entity.Order;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.enterprise.rental.dao.factory.DbManager.getInstance;
import static com.enterprise.rental.dao.jdbc.Constants.*;

public class JdbcOrderDao implements OrderDao {

    private static final OrderMapper ORDER_ROW_MAPPER = new OrderMapper();

    private static final Logger log = Logger.getLogger(JdbcOrderDao.class);

    @Override
    public List<Order> findAll() {
        return getOrderQuery(FIND_ALL_ORDERS_SQL);
    }

    @Override
    public Optional<Order> findById(Long id) {
        return getOrderById(id);
    }

    @Override
    public Optional<Order> findByName(String name) {
        return Optional.empty();
    }

    private Optional<Order> getOrderById(Long id) {
        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(FILTER_ORDER_BY_ID_SQL)) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ORDER_ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
        } finally {
            //TODO
        }
    }

    private Optional<User> getOrderByUserName(String name) {
        return Optional.empty();
    }

    @Override
    public boolean save(Order order) {

        return setOrderQuery(order);
    }

    @Override
    public Order edit(Order order) {
        return new Order();
    }

    @Override
    public boolean delete(long id) {
        return false;
    }

    @Override
    public List<Order> findAll(String params) {
        String sql = String.format("%sWHERE %s;", FILTER_ORDER_BY_SQL, params);
        return getOrderQuery(sql);
    }

    private List<Order> getOrderQuery(String sql) {
        return new ArrayList<>();
    }

    private boolean setOrderQuery(Order order) {
        return false;
    }
}


