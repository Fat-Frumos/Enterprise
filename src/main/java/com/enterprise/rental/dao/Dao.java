package com.enterprise.rental.dao;

import java.util.List;
import java.util.Optional;

/**
 * <p>Resource collection API</p>
 *
 * <code>Dao</code> defines a base interface for all DAO interfaces.
 * Defines a type for performing CRUD operations on instances.
 * Generic version of the Dao class.
 */
public interface Dao<T> {

    /**
     * <p>Retrieves all defined used
     *
     * @return the collection of all Generic entities
     */
    List<T> findAll();

    /**
     * <p>Retrieves the entity with the specified id
     *
     * @param id the id
     * @return the Optional, or <code>Optional.empty</code> if the id was invalid
     */
    Optional<T> findById(Long id);

    /**
     * <p>Retrieves the entity with the specified name</p>
     *
     * @param name of instance
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    Optional<T> findByName(String name);

    /**
     * <p>Saves the entity</p>
     *
     * @param t the type of the value being boxed
     * @return boolean.
     */
    boolean save(T t);

    /**
     * <p>Update the entity with changes</p>
     *
     * @param t the type of the value being boxed
     * @return changed Generic entity.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    T edit(T t);

    /**
     * <p>Removes the specified entity</p>
     *
     * @param id the resource ID.
     * @return boolean.
     * @throws RuntimeException all other wrapped checked exceptions if the request fails to be sent.
     */
    boolean delete(long id);

    /**
     * <p>Retrieves all defined used</p>
     *
     * @param params the additional settings.
     * @return the collection of all Generic entities.
     */
    List<T> findAll(String params);
}
