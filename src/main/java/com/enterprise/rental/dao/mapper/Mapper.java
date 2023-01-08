package com.enterprise.rental.dao.mapper;

import javax.servlet.http.HttpServletRequest;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Set;

/**
 * Abstract mapper that will manage most of the generic data for mappers from request to generic Entity.
 * List of objects - a map of object types you want to be fetched.
 * <p>
 * ResultSet, HttpServletRequest  - pointers to your retrieved results
 * When a call to the <code>next</code> method returns <code>false</code>,
 * the cursor is positioned after the last row. Any
 * invocation of a <code>ResultSet</code> method which requires a
 * current row will result in a <code>SQLException</code> being thrown.
 *
 * @author Pasha Polyak
 * @see ResultSet#next
 */
public abstract class Mapper<T> {

    /**
     * Abstract class Mapper used for mapping data from HttpServletRequest to generic Entity
     * Returns the value of a request parameter as a <code>String</code>,
     * or <code>null</code> if the parameter does not exist. Request parameters
     * are extra information sent with the request.
     *
     * <p>You should only use this method when you are sure the
     * parameter has only one value.
     *
     * @param request HttpServletRequest
     *                specifying the name of the parameter
     * @return T    <code>Entity</code> representing the single entity of the parameter
     * @see HttpServletRequest
     */
    abstract T mapper(HttpServletRequest request);

    /**
     * Creates a generic instance of the destination instance
     * Row Mapper that maps each row of a result set with the entity
     *
     * @param resultSet the ResultSet returned by JDBC API
     * @return an object of the destination type of class
     */
    abstract T mapRow(ResultSet resultSet);

    /**
     * Get All objects from until resultSet next equals true
     *
     * @param resultSet - a database result set, which generated
     *                  by executing a statement that queries the database.
     * @param elements  - data from ResultSet
     * @return Set of entities an objects of the destination type of generic class
     * @throws SQLException if an input or output error is
     *                      detected when the servlet handles the request
     */
    public Set<T> getAllFields(
            ResultSet resultSet,
            Set<T> elements)
            throws SQLException {

        if (!resultSet.next()) {
            return elements;
        } else {
            T t = mapRow(resultSet);
            elements.add(t);
            return getAllFields(resultSet, elements);
        }
    }
}
