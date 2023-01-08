package com.enterprise.rental.service.locale;

import java.util.ListResourceBundle;

/**
 * BungleUa Class extends ListResourceBundle that manages resources for a locale in a convenient and easy to use list.
 * <p>
 * BungleUa must override getContents and provide an array,
 * where each item in the array is a pair of objects.
 * <p>
 * The first element of each pair is the key, which must be a String,
 * and the second element is the value associated with that key.
 *
 * @author Pasha Polyak
 * @see java.util.ResourceBundle
 */
public class BungleUa extends ListResourceBundle {
    private static final CurrencyConvector currencyConvector = new CurrencyConvector();
    private static final double usd = currencyConvector.exchangeMultiply(1D, "USD");

    /**
     * Returns an array in which each item is a pair of objects in an
     * <code>Object</code> array. The first element of each pair is
     * the key, which must be a <code>String</code>, and the second
     * element is the value associated with that key.  See the class
     * description for details.
     *
     * @return an array of an <code>Object</code> array representing a
     * key-value pair.
     */
    public Object[][] getContents() {
        return contents;
    }

    static final Object[][] contents = {

            {"span.card", "Картка"},
            {"month.September", "\u0412\u0435\u0440\u0435\u0441\u0435\u043D\u044C"},
            {"month.October", "\u0416\u043E\u0432\u0442\u0435\u043D\u044C"},
            {"month.November", "\u041B\u0438\u0441\u0442\u043E\u043F\u0430\u0434"},
            {"month.December", "\u0413\u0440\u0443\u0434\u0435\u043D\u044C"},
            {"month.January", "\u0421\u0456\u0447\u0435\u043D\u044C"},
            {"month.February", "\u041B\u044E\u0442\u0438\u0439"},
            {"month.March", "\u0411\u0435\u0440\u0435\u0437\u0435\u043D\u044C"},
            {"month.April", "\u041A\u0432\u0456\u0442\u0435\u043D\u044C"},
            {"month.May", "\u0415\u0440\u0430\u0432\u0435\u043D\u044C"},
            {"month.June", "\u0427\u0435\u0440\u0432\u0435\u043D\u044C"},
            {"month.July", "\u041B\u0438\u043F\u0435\u043D\u044C"},
            {"month.August", "\u0421\u0435\u0440\u043F\u0435\u043D\u044C"},

// <html lang="en">
            {"exchange", "" + usd},
            {"exchange.sign", "\u20B4"},
            {"title.login", "\u041B\u043E\u0433\u0456\u043D"},
            {"title.cars", "\u0410\u0432\u0442\u0456\u0432\u043A\u0438"},
            {"title.user", "\u041A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447"},
            {"title.orders", "\u0417\u0430\u043C\u043E\u0432\u043B\u0435\u043D\u043D\u044F"},
            {"title.main", "\u041E\u0441\u043D\u043E\u0432\u043D\u0430"},
//login
            {"h3.sign", "\u0417\u0430\u0439\u0442\u0438"},
            {"button.signup", "\u0417\u0430\u0440\u0435\u0454\u0441\u0442\u0440\u0443\u0432\u0430\u0442\u0438\u0441\u044F"},
            {"button.price", "\u0426\u0456\u043D\u0430"},
            {"button.cost", "\u0412\u0430\u0440\u0442\u0456\u0441\u0442\u044C"},
            {"button.search", "\u041F\u043E\u0448\u0443\u043A"},
            {"button.submit", "\u041F\u0456\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0438"},

            {"placeholder.username", "\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u0406\u043C\u2019\u044F"},
            {"placeholder.email", "\u0412\u0432\u0435\u0434\u0456\u0442\u044C \u041F\u043E\u0448\u0442\u0443"},
            {"placeholder.password", "Enter \u041F\u0430\u0440\u043E\u043B\u044C"},

            {"a.sign", "\u0417\u0430\u0439\u0442\u0438"},
            {"a.cancel", "\u0412\u0456\u0434\u043C\u0456\u043D\u0430"},
            {"a.forgot", "\u0417\u0430\u0431\u0443\u043B\u0438 \u041F\u0430\u0440\u043E\u043B\u044C"},
//nav
            {"a.cars", "\u0410\u0432\u0442\u043E"},
            {"a.cards", "\u041A\u043E\u0448\u0438\u043A"},
            {"a.cabinet", "\u0417\u0430\u043C\u043E\u0432\u043B\u0435\u043D\u043D\u044F"},
//main
            {"label.brand", "\u0411\u0440\u0435\u043D\u0434"},
            {"label.price", "\u0426\u0456\u043D\u0430"},
            {"label.cost", "\u0412\u0430\u0440\u0442\u0456\u0441\u0442\u044C"},
            {"span.rent", "\u0426\u0456\u043D\u0430"},
            {"span.rental", "\u0417\u0430\u043C\u043E\u0432\u043B\u0435\u043D\u0435 \u0430\u0432\u0442\u043E"},
            {"span.price", "\u0412\u0430\u0440\u0442\u0456\u0441\u0442\u044C"},
            {"option.choose", "\u041E\u0431\u0435\u0440\u0456\u0442\u044C \u0430\u0432\u0442\u043E"},
//car
            {"span.passport", "\u041F\u0430\u0441\u043F\u043E\u0440\u0442"},
            {"span.term", "\u0422\u0435\u0440\u043C\u0456\u043D"},
            {"span.phone", "\u0422\u0435\u043B\u0435\u0444\u043E\u043D"},
            {"span.driver", "\u0412\u043E\u0434\u0456\u0439"},
            {"span.payment", "\u041E\u043F\u043B\u0430\u0442\u0430"},
            {"input.purchase", "\u0417\u0430\u043C\u043E\u0432\u0438\u0442\u0438"},
//contract
            {"h5.orders", "\u0421\u0442\u0456\u043B \u0417\u0430\u043A\u0430\u0437\u0456\u0432"},
            {"th.user", "\u2116 \u043A\u043E\u0440\u0438\u0441\u0442\u0443\u0432\u0430\u0447\u0430"},
            {"th.order", "\u2116 \u0437\u0430\u043C\u043E\u0432\u043B\u0435\u043D\u043D\u044F"},
            {"th.car", "\u2116 \u0410\u0432\u0442\u043E"},
            {"th.create", "\u0421\u0442\u0432\u043E\u0440\u0435\u043D\u043E"},
            {"th.term", "\u0422\u0435\u0440\u043C\u0456\u043D"},
            {"th.damage", "\u0423\u0448\u043A\u043E\u0434\u0436\u0435\u043D\u043D\u044F"},
            {"th.reason", "\u041F\u0440\u0438\u0447\u0438\u043D\u0430"},
            {"th.payment", "\u041E\u043F\u043B\u0430\u0442\u0430"},
            {"th.reject", "\u0412\u0456\u0434\u043C\u043E\u0432\u0430"},
            {"th.close", "\u0417\u0430\u043A\u0440\u0438\u0442\u0438\u0439"},
            {"th.confirm", "\u041F\u0456\u0434\u0442\u0432\u0435\u0440\u0434\u0438\u0442\u0438"},
            {"th.invoice", "P\u0430\u0445\u0443\u043D\u043E\u043A"},
            {"th.remove", "\u0412\u0456\u0434\u0430\u043B\u0438\u0442\u0438"},
//label
            {"p.model", "\u041C\u043E\u0434\u0435\u043B\u044C"},
            {"p.self", "\u0410\u0432\u0442\u043E-\u041F\u0456\u043B\u043E\u0442"},
            {"p.cost", "\u0412\u0430\u0440\u0442\u0456\u0441\u0442\u044C"},
            {"p.price", "\u041E\u0440\u0435\u043D\u0434\u0430"},
            {"p.driver", "\u0412\u043E\u0434\u0456\u0439"},
            {"h4.upload", "\u041D\u043E\u0432\u0435 \u0410\u0432\u0442\u043E"},
            {"input.upload", "\u0417\u0430\u0432\u0430\u043D\u0442\u0430\u0436\u0438\u0442\u0438"},
            {"placeholder.brand", "\u0411\u0440\u0435\u043D\u0434"},
            {"placeholder.name", "\u0406\u043C\u2019\u044F"},
            {"placeholder.model", "\u041C\u043E\u0434\u0435\u043B\u044C"},
            {"confirm.message", "\u0412\u0438 \u0434\u0456\u0439\u0441\u043D\u043E \u0445\u043E\u0447\u0435\u0442\u0435 \u0432\u0438\u043B\u0430\u043B\u0438\u0442\u0438 \u0430\u0432\u0442\u043E?"},
//users
            {"th.name", "\u0406\u043C\u2019\u044F"},
            {"th.email", "\u041F\u043E\u0448\u0442\u0430"},
            {"th.role", "\u0420\u043E\u043B\u044C"},
            {"th.active", "\u0414\u0456\u0439\u0441\u043D\u0438\u0439"},
            {"th.closed", "\u0417\u0430\u043A\u0440\u0438\u0442\u0438\u0439"},
            {"th.confirm", "\u041F\u0456\u0434\u0432\u0435\u0440\u0434\u0438\u0442\u0438"}
    };
}