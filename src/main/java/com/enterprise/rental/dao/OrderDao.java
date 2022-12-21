package com.enterprise.rental.dao;

import com.enterprise.rental.entity.Invoice;
import com.enterprise.rental.entity.Order;

/**
 * The <code>OrderDao</code> extends <code>Dao</code> interface
 * for performing CRUD operations on instances of {@link Order}.
 *
 * @author Pasha Pollack
 */
public interface OrderDao extends Dao<Order> {
    /**
     * Is used to update order.
     *
     * @param order The order to be updated.
     */
    Order edit(Order order);

    /**
     * <p>Saves the Invoice</p>
     * Is used to create the given entity in the database.
     *
     * @param invoice The entity to create.
     * @return boolean true, or false if the entity was not saved
     */
    boolean save(Invoice invoice);
}
