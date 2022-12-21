package com.enterprise.rental.dao.jdbc;

import com.enterprise.rental.dao.UserDao;
import com.enterprise.rental.dao.mapper.UserMapper;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DataException;
import org.apache.log4j.Logger;

import javax.validation.constraints.NotNull;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static com.enterprise.rental.dao.jdbc.Constants.*;
import static com.enterprise.rental.dao.jdbc.DbManager.*;
import static com.enterprise.rental.service.UserService.addSalt;
import static com.enterprise.rental.service.UserService.saltedPassword;

/**
 * Java class that represent an implementing UserDao
 * Jdbc User Dao performing CRUD operations on instances of {@link User}.
 * JdbcUserDao the Java API that manages connecting to a database,
 * issuing queries and commands, and handling result sets obtained from the database.
 *
 * @author Pasha Pollack
 * @see UserDao
 */
public class JdbcUserDao implements UserDao {
    private static final UserMapper ROW_MAPPER = new UserMapper();

    private static final Logger log = Logger.getLogger(JdbcUserDao.class);

    /**
     * Find User by name.
     *
     * @param name the username
     * @return {@code Optional<User>}, if a value is present, otherwise {@code Optional.empty()}.
     */
    @Override
    public Optional<User> findByName(String name) {
        return name != null
                ? getUserSql(String.format("%s'%s'", FILTER_BY_NAME_SQL, name))
                : Optional.empty();
    }

    /**
     * <p>Retrieves all defined users
     * Is used to get all the objects from the database.
     * Default query (FROM Car)
     *
     * @return the collection of all Users
     */
    @Override
    public List<User> findAll() {
        return findAll(USERS_SQL);
    }

    /**
     * <p>Saves the entity User to the database
     * Is used to create the given user in the database.
     *
     * @param user The object to create.
     * @return boolean true, or false if the entity was not saved
     */
    @Override
    public boolean save(User user) {
        return setUserQuery(user);
    }

    /**
     * <p>Update the User with changes to database
     * Is used to edit the given object.
     *
     * @param user The object to update
     * @return User changed user.
     */
    @Override
    public User edit(User user) {

        String query = getQuery(user);

        log.debug(String.format("%s%n%s", user, query));

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = configConnection();
            statement = connection.prepareStatement(query);

            boolean update = statement.executeUpdate() > 0;
            log.debug(String.format("updated: %b", update));
            connection.commit();
            return user;

        } catch (SQLException sqlException) {
            log.error(query);
            rollback(connection, sqlException);

        } finally {
            eventually(connection, statement);
        }
        return user;
    }

    /**
     * <p>Removes the specified user by ID</p>
     * Is used to delete the object with the given id from the database.
     *
     * @param id the resource ID.
     * @return false because method not implemented
     */
    @Override
    public boolean delete(long id) {
        return false;
    }

    /**
     * <p>Retrieves all defined User</p>
     * Is used to get objects from the database with query parameter.
     *
     * @param query the additional settings.
     * @return the collection of User.
     */
    @Override
    public List<User> findAll(String query) {

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = configConnection();
            statement = connection.prepareStatement(query);
            return getAllUsers(statement, new ArrayList<>());

        } catch (SQLException sqlException) {
            log.error(query, sqlException);
            rollback(connection, sqlException);

        } finally {
            eventually(connection, statement);
//            log.debug("Connection closed");
        }
        return new ArrayList<>();
    }


    /**
     * <p>Retrieves the User with the specified id</p>
     * Is used to return an User from the database by the given id,
     * otherwise {@code Optional.empty}.
     *
     * @param id of User
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    @Override
    public Optional<User> findById(Long id) {
        return getUserSql(String.format("%s%d", FILTER_USER_BY_ID_SQL, id));
    }

    /**
     * <p>Retrieves the entity with the specified query</p>
     * Is used to return an object from the database by the given query,
     * otherwise {@code Optional.empty}.
     *
     * @param query of object
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    private Optional<User> getUserSql(String query) {

        log.debug(String.format("%s%s%s", YELLOW, query, RESET));
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = configConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            connection.commit();

            return !resultSet.next()
                    ? Optional.empty()
                    : Optional.of(ROW_MAPPER.mapRow(resultSet));

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            log.debug(String.format("rollback (connection): %s", query));

        } finally {
            eventually(connection, statement, resultSet);
        }
        return Optional.empty();
    }

    /**
     * Insert user to the database using prepare Statement
     *
     * @param user the user
     */
    private boolean setUserQuery(@NotNull User user) throws DataException {

        Connection connection = null;
        PreparedStatement statement = null;
        boolean execute = false;

        if (user.getRole().equals("guest")) {
            return false;
        }

        try {
            connection = configConnection();
            statement = connection.prepareStatement(INSERT_USER_SQL);
            log.debug(String.format("%s%s%s", YELLOW, INSERT_USER_SQL, RESET));
            connection.commit();
            setUser(user, statement);
            execute = statement.execute();
            connection.commit();
            return execute;
        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            log.debug(String.format("%s User can`t added: %s", user.getName(), INSERT_USER_SQL));
        } finally {
            eventually(connection, statement);
        }
        return execute;
    }

    /**
     * Generates a SQL statement from user fields
     *
     * @param user the user
     * @return the SQL statement
     */
    private static String getQuery(User user) {

        return String.format(UPDATE_USER_SQL,
                user.getName(),
                user.getRole(),
                user.isActive(),
                user.isClosed(),
                user.getUserId());
    }

    /**
     * <p>Retrieves all defined Users</p>
     * Is used to get objects from the database with query parameter.
     *
     * @return the collection of Users.
     */
    private static List<User> getAllUsers(
            PreparedStatement statement,
            List<User> users) {

        ResultSet resultSet = null;
        Connection connection = null;
        try {
            connection = configConnection();
            statement = connection.prepareStatement(USERS_SQL);
            log.debug(String.format("%s", USERS_SQL));
            resultSet = statement.executeQuery();
            connection.commit();
            return getResultSetUser(users, resultSet);

        } catch (SQLException sqlException) {
            rollback(connection, sqlException);
            log.debug(String.format("Customer not found %s: %s",
                    sqlException.getMessage(), USERS_SQL));
        } finally {
            eventually(connection, statement, resultSet);
        }
        return users;
    }

    /**
     * Get user from the ResultSet
     *
     * @param users     List of users
     * @param resultSet ResultSet
     * @return the collection of Users
     * @throws SQLException if something error
     */
    private static List<User> getResultSetUser(
            List<User> users,
            ResultSet resultSet) throws SQLException {
        if (resultSet.next()) {
            while (resultSet.next()) {
                users.add(ROW_MAPPER.mapRow(resultSet));
            }
        }
        return users;
    }

    /**
     * Set the user data to Prepared Statement
     *
     * @param statement a PreparedStatement
     * @param user      the user
     * @throws SQLException if an error occurs
     */
    private static void setUser(
            @NotNull User user,
            PreparedStatement statement) throws SQLException {
        String salt = addSalt(user);
        log.debug(String.format("RawPassword: %s%n salt:%s", user.getPassword(), user.getSalt()));
        log.debug(String.format("%s%n%s", INSERT_USER_SQL, user.getName()));
        statement.setLong(1, UUID.randomUUID().getMostSignificantBits() & 0x7fffL);
        statement.setString(2, user.getName());
        statement.setString(3, user.getEmail());
        statement.setString(4, saltedPassword(user.getPassword(), salt));
        statement.setString(5, user.getPassport());
        statement.setString(6, user.getPhone());
        statement.setString(7, user.getRole());
        statement.setBoolean(8, true);
        statement.setBoolean(9, false);
        statement.setString(10, user.getLanguage() == null
                || user.getLanguage().equals("")
                ? "en" : user.getLanguage());
        statement.setString(11, salt);
    }
}
