package com.enterprise.rental.service;

import com.enterprise.rental.entity.Car;
import com.enterprise.rental.entity.User;
import com.enterprise.rental.exception.DaoException;
import com.enterprise.rental.exception.ServiceException;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingListener;
import javax.validation.constraints.NotNull;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

/**
 * The service interface for User
 * extends abstract Service {@link User}
 *
 * @author Pasha Polyak
 */
public interface UserService extends Service<User> {


    /**
     * Set generated UUID and salted Password in the User
     *
     * @param user the entity of type User
     * @return generated salt
     */
    static String addSalt(User user) {
        String salt = generateUUID();
        user.setSalt(salt);
        return salt;
    }

    /**
     * Encodes this salted password into a sequence of bytes using the given charset,
     * storing the result into a new byte array.
     * <p>
     * Calculates the SHA-256 digest and returns the value as a hex string.
     *
     * @param rawPassword user password data to digest
     * @param salt        generated Data to digest
     * @return SHA-256 digest as a hex string
     */
    static String saltedPassword(String rawPassword, String salt) {
        return DigestUtils.sha256Hex(getBytes(
                String.format("%s%s", rawPassword, salt)));
    }

    /**
     * Encodes {@code salted password} into a sequence of bytes using the given
     * {@linkplain java.nio.charset.Charset charset}, storing the result into a
     * new byte array.
     * {@link java.nio.charset.CharsetEncoder} class should be used when more
     * control over the encoding process is required.
     *
     * @param saltedPassword The {@linkplain java.nio.charset.StandardCharsets} to be used to encode
     *                       the {@code String}
     * @return The resultant byte array
     */
    static byte[] getBytes(String saltedPassword) {
        return saltedPassword.getBytes(StandardCharsets.UTF_8);
    }

    /**
     * The {@code UUID} is generated using a cryptographically
     * strong pseudo random number generator.
     *
     * @return A randomly generated string {@code UUID}
     */
    static String generateUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * Save the User to Database.
     *
     * @param user the type of User
     * @return true if method was executed and vice-versa
     */
    boolean save(User user) throws ServiceException;

    /**
     * Get all Users from Database.
     *
     * @return the list of entities
     */
    List<User> findAllBy() throws ServiceException;

    /**
     * Find all Users with String query parameter.
     *
     * @param query the parameter
     * @return the list of Users
     */
    List<User> findAllBy(String query) throws ServiceException;

    /**
     * Find all Users with parameters.
     *
     * @param params map: pagination, sort, price, cost, name
     * @return the list of Users
     */
    List<User> findAllBy(Map<String, String> params) throws ServiceException;

    /**
     * Find the User by name.
     * The annotated element must not be null. Accepts any type.
     *
     * @param name the Username
     * @return Optional User
     * @see javax.validation.constraints.NotNull
     */
    Optional<User> findByName(@NotNull String name);

    /**
     * Find the User by id.
     *
     * @param id the User id
     * @return Optional User
     */
    Optional<User> findBy(Long id);

    /**
     * Update the User by object.
     *
     * @param user type of User
     * @return User type of the new User
     */
    User edit(User user) throws ServiceException;

    /**
     * Delete the User by ID.
     *
     * @param id the User id
     * @return true if method was executed and vice-versa
     */
    boolean delete(Long id) throws ServiceException;

    /**
     * Add car to User profiler.
     * The annotated element must not be null. Accepts any type.
     *
     * @param user the User
     * @param car  the rental Car
     * @return User with booked car
     * @see javax.validation.constraints.NotNull
     */
    User toCart(@NotNull Car car, @NotNull User user);

    /**
     * Sends email message fo user.
     *
     * @param name a username
     * @return boolean true if executed else false
     */
    boolean sendEmail(String name) throws DaoException;

    /**
     * Provides a way to identify a user across more than one of the page
     * request or visit to a site and to store information about that user
     *
     * @param request  an {@link HttpServletRequest} object that
     *                 contains the request the client has made
     *                 of the servlet
     * @param response an {@link HttpServletResponse} object that
     *                 contains the response the servlet sends
     *                 to the client
     * @param session  an {@link HttpSession} Session information
     *                 is scoped only to the current web application,
     *                 so information stored in one context
     *                 will not be directly visible in another.
     * @see HttpSessionBindingListener
     */
    void setUserToken(HttpServletRequest request,
                      HttpServletResponse response, HttpSession session);
}

