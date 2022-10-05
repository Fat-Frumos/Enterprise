package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;
import java.util.logging.Logger;

public class JdbcUserTemplate extends PropsReader {

    private static final UserMapper ROW_MAPPER = new UserMapper();
    private static final Logger log = Logger.getLogger(JdbcUserTemplate.class.getName());
    private static final String FILTER_BY_NAME_SQL = "SELECT id, email, nickname, password FROM users WHERE nickname=";

    protected static Optional<User> getUserByName(String name) {

        String sql = String.format("%s'%s'", FILTER_BY_NAME_SQL, name);

        log.info(sql);

        try (Connection connection = getInstance().getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                return !resultSet.next()
                        ? Optional.empty()
                        : Optional.of(ROW_MAPPER.mapRow(resultSet));
            }
        } catch (SQLException e) {
            throw new DataException(e);
            //TODO Optional<User>
        }
    }
}
