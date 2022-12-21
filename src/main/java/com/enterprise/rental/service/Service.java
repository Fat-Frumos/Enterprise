package com.enterprise.rental.service;

import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * The service interface for generic entity
 *
 * @author Pasha Pollack
 */
public interface Service<T> {

    /**
     * Save the entity to Database.
     *
     * @param t the type of entity
     * @return true if method was executed and vice-versa
     */
    boolean save(T t);

    /**
     * Find all entities
     *
     * @return the list of entities
     */
    List<T> getAll();

    /**
     * Find all entities with String query parameter.
     *
     * @param query the parameter
     * @return the list of entities
     */
    List<T> getAll(String query);

    /**
     * Find all entities.
     *
     * @param params Map (String key, String value)
     * @return the list of entities
     */
    List<T> getAll(Map<String, String> params);

    /**
     * Find the entity by id.
     *
     * @param id the entity id
     * @return Optional T
     */
    Optional<T> getById(long id);

    /**
     * Update the entity by object.
     *
     * @param t object
     * @return T type of the entity
     */
    T edit(T t);

    /**
     * Delete the entity.
     *
     * @param id the id
     * @return true if method was executed and vice-versa
     */
    boolean delete(long id);
}
