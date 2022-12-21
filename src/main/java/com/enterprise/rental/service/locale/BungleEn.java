package com.enterprise.rental.service.locale;

import java.util.ListResourceBundle;

/**
 * BungleEn extends ListResourceBundle that manages resources for a session locale in a convenient and easy to use list.
 * <p>
 * BungleEn must override getContents and provide an array,
 * where each item in the array is a pair of objects.
 * <p>
 * The first element of each pair is the key, which must be a String,
 * and the second element is the value associated with that key.
 *
 * @author Pasha Pollack
 * @see java.util.ResourceBundle
 */
public class BungleEn extends ListResourceBundle {

    /**
     * Returns an array in which each item is a pair of objects in an
     * <code>Object</code> array. The first element of each pair is
     * the key, which must be a <code>String</code>, and the second
     * element is the value associated with that key.
     *
     * @return an array of an <code>Object</code> array representing a
     * key-value pair.
     */
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {

            {"span.card", "Card"},
            {"month.September", "September"},
            {"month.October", "October"},
            {"month.November", "November"},
            {"month.December", "December"},
            {"month.January", "January"},
            {"month.February", "February"},
            {"month.March", "March"},
            {"month.April", "April"},
            {"month.May", "May"},
            {"month.June", "June"},
            {"month.July", "July"},
            {"month.August", "August"},

// <html lang="en">
            {"exchange", "1"},
            {"exchange.sign", "$"},
            {"title.login", "Login"},
            {"title.cars", "Cars"},
            {"title.user", "User"},
            {"title.orders", "Orders Review"},
            {"title.main", "Main"},
//login
            {"h3.sign", "Sign In"},
            {"button.signup", "Sign Up"},
            {"button.price", "Price"},
            {"button.cost", "Cost"},
            {"button.search", "Search"},
            {"button.submit", "Submit"},

            {"placeholder.username", "Enter Username"},
            {"placeholder.email", "Enter Email"},
            {"placeholder.password", "Enter Password"},

            {"a.sign", "Sign In"},
            {"a.cancel", "Cancel"},
            {"a.forgot", "Forgot password"},
//nav
            {"a.cars", "Cars"},
            {"a.cards", "Cards"},
            {"a.cabinet", "Cabinet"},
//main
            {"label.brand", "Brand"},
            {"label.price", "Price"},
            {"label.cost", "Cost"},
            {"span.rent", "Rent"},
            {"span.rental", "Rental Car"},
            {"span.price", "Price"},
            {"option.choose", "Choose auto"},
//car
            {"span.passport", "Passport"},
            {"span.term", "Term"},
            {"span.phone", "Phone"},
            {"span.driver", "Driver"},
            {"span.payment", "Payment"},
            {"input.purchase", "Purchase"},
//contract
            {"h5.orders", "List of Orders"},
            {"th.user", "User#"},
            {"th.order", "Order#"},
            {"th.car", "Car#"},
            {"th.create", "Created"},
            {"th.term", "Term"},
            {"th.damage", "Damage"},
            {"th.reason", "Reason"},
            {"th.payment", "Payment"},
            {"th.reject", "Rejected"},
            {"th.close", "Closed"},
            {"th.confirm", "Confirm"},
            {"th.invoice", "Invoice"},
            {"th.remove", "Remove"},
//label
            {"p.model", "Model"},
            {"p.self", "Self-Drive"},
            {"p.cost", "Cost"},
            {"p.price", "Price"},
            {"p.driver", "Driver"},
            {"h4.upload", "Upload Form"},
            {"input.upload", "Upload"},
            {"placeholder.brand", "Brand"},
            {"placeholder.name", "Name"},
            {"placeholder.model", "Model"},
            {"confirm.message", "Do you want to remove the car?"},
//users
            {"th.name", "Name"},
            {"th.email", "Email"},
            {"th.role", "Role"},
            {"th.active", "Active"},
            {"th.closed", "Closed"},
            {"th.confirm", "Confirm"}
    };
}