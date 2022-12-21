package com.enterprise.rental.dao;

import com.enterprise.rental.entity.User;

import java.util.List;
import java.util.Optional;

/**
 * <p>Resource collection API of Users
 * <code>UserDao</code> extends <code>Dao</code> interface
 * for performing CRUD operations on instances of {@link User}.
 *
 * @author Pasha Pollack
 */
public interface UserDao extends Dao<User> {

    /**
     * <p>Gets the details of the user specified by its name.
     *
     * @param name the User name.
     * @return the <code>Optional instance</code> of the user
     * or <code>Optional.empty</code> if the id was invalid or not found.
     */
    Optional<User> findByName(String name);

    /**
     * <p>Retrieves all defined users.
     *
     * @return the collection of all users
     */
    List<User> findAll();
}
