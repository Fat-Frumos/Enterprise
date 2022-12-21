package com.enterprise.rental.dao.mapper;

import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.UserNotFoundException;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Allows you to map Users
 * It used for mapping data from HttpServletRequest and ResultSet.
 * <p>
 * Class UserMapper extends Mapper(User).
 * Builds Entity User
 *
 * @author Pasha Pollack
 * @see Mapper
 */
public class UserMapper extends Mapper<User> {
    private static final Logger log = Logger.getLogger(UserMapper.class);
    private static final String[] userFields = {"id", "name", "password", "passport", "phone", "language", "email", "created", "salt", "active", "closed"};

    /**
     * Method mapper used for mapping data from request to generic Entity
     * Map (String, String) the map of object types you want to be fetched
     * <p>
     * Invocation of a <code>HttpServletRequest</code> method which requires a
     * current row will result in a <code>SQLException</code> being thrown.
     *
     * @see HttpServletRequest
     */
    @Override
    public User mapper(HttpServletRequest request) {

        Map<String, String> params = Arrays.stream(userFields)
                .filter(key -> !"".equals(request.getParameter(key))
                        && request.getParameter(key) != null)
                .collect(Collectors.toMap(
                        key -> key, request::getParameter, (a, b) -> b));

        User user = new User.Builder()
                .name(params.get(userFields[1]))
                .password(params.get(userFields[2]))
                .passport(params.get(userFields[3]))
                .phone(params.get(userFields[4]))
                .language(params.get(userFields[5]))
                .email(params.get(userFields[6]))
                .salt(params.get(userFields[8]))
//                .created(Timestamp.valueOf(params.get(userFields[7])))
                .active(true)
                .closed(false)
                .build();

        log.info(params);
        return user;
    }

    /**
     * Create an instance of the destination class {@link User}
     * <p>
     * ResultSet - pointer to your retrieved results
     * Set of User - a map of object types you want to be fetched
     * <p>
     *
     * @param resultSet the ResultSet returned by JDBC API
     * @return entity User Builder()
     */
    public User mapRow(ResultSet resultSet) {

        try {
            String role = resultSet.getString("role")
                    != null ? resultSet.getString("role") : "guest";

            return new User.Builder()
                    .userId(resultSet.getLong(userFields[0]))
                    .name(resultSet.getString(userFields[1]))
                    .password(resultSet.getString(userFields[2]))
                    .passport(resultSet.getString(userFields[3]))
                    .phone(resultSet.getString(userFields[4]))
                    .language(resultSet.getString(userFields[5]))
                    .email(resultSet.getString(userFields[6]))
                    .salt(resultSet.getString(userFields[8]))
//                    .created(new Timestamp(System.currentTimeMillis()))
                    .active(resultSet.getBoolean(userFields[9]))
                    .closed(resultSet.getBoolean(userFields[10]))
                    .role(role)
                    .build();

        } catch (SQLException exception) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                log.error(e.getMessage());
            }
            throw new UserNotFoundException("User not found", exception);
        }
    }
}
