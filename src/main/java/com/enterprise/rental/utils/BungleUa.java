package com.enterprise.rental.utils;

import java.util.ListResourceBundle;

public class BungleUa extends ListResourceBundle {
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

// <html lang="ua">
            {"title.login", "Логін"},
            {"title.cars", "Автівки"},
            {"title.User", "Користувач"},
            {"title.orders", "Замовлення"},
            {"title.main", "Основна"},
//login
            {"h3.sign", "Зайти"},
            {"button.signup", "Зареєструватися"},
            {"button.price", "Pахунок"},
            {"button.cost", "Ціна"},
            {"button.search", "Пошук"},
            {"button.submit", "Підтвердити"},

            {"placeholder.username", "Введіть Ім’я"},
            {"placeholder.email", "Введіть Пошту"},
            {"placeholder.password", "Enter Пароль"},

            {"a.sign", "Зайти"},
            {"a.cancel", "Відміна"},
            {"a.forgot", "Забули Пароль"},
//nav
            {"a.cars", "Авто"},
            {"a.cards", "Кошик"},
            {"a.cabinet", "Замовлення"},
//main
            {"label.brand", "Бренд"},
            {"label.price", "Ціна"},
            {"label.cost", "Вартість"},
            {"span.rent", "Ціна"},
            {"span.rental", "Замовлене авто"},
            {"span.price", "Вартість"},
            {"option.choose", "Оберіть авто"},
//car
            {"span.passport", "Паспорт"},
            {"span.term", "Термін"},
            {"span.phone", "Телефон"},
            {"span.driver", "Водій"},
            {"span.payment", "Оплата"},
            {"input.purchase", "Замовити"},
//contract
            {"h5.orders", "Стіл Заказів"},
            {"th.user", "№ користувача"},
            {"th.order", "№ замовлення"},
            {"th.car", "№ Авто"},
            {"th.create", "Створено"},
            {"th.term", "Термін"},
            {"th.damage", "Ушкодження"},
            {"th.reason", "Причина"},
            {"th.payment", "Оплата"},
            {"th.reject", "Відмова"},
            {"th.close", "Закритий"},
            {"th.confirm", "Підтвердити"},
            {"th.invoice", "Pахунок"},
            {"th.remove", "Відалити"},
//label
            {"p.model", "Модель"},
            {"p.self", "Авто-Пілот"},
            {"p.cost", "Вартість"},
            {"p.price", "Оренда"},
            {"p.driver", "Водій"},
            {"h4.upload", "Нове Авто"},
            {"input.upload", "Завантажити"},
            {"placeholder.brand", "Бренд"},
            {"placeholder.name", "Ім’я"},
            {"placeholder.model", "Модель"},
            {"confirm.message", "Ви дійсно хочете вилалити авто?"},
//users
            {"th.name", "Ім’я"},
            {"th.email", "Пошта"},
            {"th.role", "Роль"},
            {"th.active", "Дійсний"},
            {"th.closed", "Закритий"},
            {"th.confirm", "Підвердити"}
    };
}