package com.enterprise.rental.service;

import com.enterprise.rental.exception.ServiceException;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The service interface for generic entity
 *
 * @author Pasha Polyak
 */
public interface Service<T> {

    /**
     * Save the entity to Database.
     *
     * @param t the type of entity
     * @return true if method was executed and vice-versa
     */
    boolean save(T t) throws ServiceException;

    /**
     * Find all entities
     *
     * @return the list of entities
     */
    List<T> findAllBy() throws ServiceException;

    /**
     * Find all entities with String query parameter.
     *
     * @param query the parameter
     * @return the list of entities
     */
    List<T> findAllBy(String query) throws ServiceException;

    /**
     * Find all entities.
     *
     * @param params Map (String key, String value)
     * @return the list of entities
     */
    List<T> findAllBy(Map<String, String> params) throws ServiceException;

    /**
     * Find the entity by id.
     *
     * @param id the entity id
     * @return Optional T
     */
    Optional<T> findBy(Long id);

    /**
     * Update the entity by object.
     *
     * @param t object
     * @return T type of the entity
     */
    T edit(T t) throws ServiceException;

    /**
     * Delete the entity.
     *
     * @param id the id
     * @return true if method was executed and vice-versa
     */
    boolean delete(Long id) throws ServiceException;
}
