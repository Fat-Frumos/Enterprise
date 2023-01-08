package com.enterprise.rental.dao;

import com.enterprise.rental.exception.DaoException;

import java.util.List;
import java.util.Optional;

/**
 * <p>Resource collection API
 * <code>Dao</code> defines a base interface for all DAO interfaces.
 * <p>Defines a type for performing CRUD operations on instances.
 * The provides methods to work with database and required entities.
 * Every dao class should instantiate this interface.
 *
 * @author Pasha Polyak
 */
public interface Dao<T> {

    /**
     * <p>Saves the entity</p>
     * Is used to create the given object in the database.
     *
     * @param t The object to create.
     * @return boolean true, or false if the entity was not saved
     */
    boolean save(T t) throws DaoException;

    /**
     * <p>Retrieves the entity with the specified id
     * Is used to return an object from the database by the given id,
     * otherwise Returns an empty {@code Optional} instance.
     * No value is present for this {@code Optional}.
     *
     * @param id The id of the object to be returned.
     * @return the  <code>Optional entity</code>, or <code>Optional.empty</code> if the car was not found
     * @see Optional#empty
     */
    Optional<T> findById(Long id);

    /**
     * <p>Retrieves the entity with the specified name</p>
     * Is used to return an object from the database by the given name,
     * otherwise {@code Optional.empty}.
     *
     * @param name of object
     * @return the Optional, or <code>Optional.empty</code> if the name was not found
     */
    Optional<T> findByName(String name) throws DaoException;

    /**
     * <p>Retrieves all defined entities
     * Is used to get all the objects from the database.
     *
     * @return the collection of all Generic entities
     */
    List<T> findAll() throws DaoException;

    /**
     * <p>Retrieves all defined entities</p>
     * Is used to get objects from the database with query parameter.
     *
     * @param params the additional settings.
     * @return the collection of all Generic entities.
     */
    List<T> findAll(String params) throws DaoException;

    /**
     * <p>Update the entity with changes</p>
     * Is used to update the given object.
     *
     * @param t The object to update
     * @return T changed Generic entity.
     */
    T edit(T t) throws DaoException;

    /**
     * <p>Removes the specified entity by ID</p>
     * Is used to delete the object with the given id from the database.
     *
     * @param id the resource ID.
     * @return boolean true, or false if the entity was not deleted
     */
    boolean delete(Long id) throws DaoException;
}
