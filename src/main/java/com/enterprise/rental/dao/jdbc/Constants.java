package com.enterprise.rental.dao.jdbc;

/**
 * The Constants class describes the constants variables
 * Includes Sql queries, paths, and fonts
 *
 * @author Pasha Pollack
 */
public class Constants {
    // Car sql
    protected static final String FIELD = "id, name, brand, model, path, price, cost, year, rent, date ";
    protected static final String CAR_FIELDS = String.format("SELECT %s", FIELD);
    public static final String FIND_ALL_LIMIT_SQL = String.format("%sFROM car", CAR_FIELDS);
    public static final String FIND_ALL_SQL = String.format("%sFROM car LIMIT 80;", CAR_FIELDS);
    protected static final String FILTER_CAR_BY_SQL = String.format("%sFROM car WHERE", CAR_FIELDS);
    protected static final String FILTER_BY_ID_SQL = String.format("%sFROM car WHERE id=?", CAR_FIELDS);
    protected static final String FILTER_BY_CAR_NAME_SQL = String.format("%sFROM car WHERE name=?", CAR_FIELDS);
    protected static final String INSERT_CAR_SQL = String.format("INSERT INTO car(%s) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );", FIELD);
    protected static final String UPDATE_CAR_SQL = "UPDATE car SET brand = '%s', model = '%s', name = '%s', price = %s, cost = %s WHERE id =%d";
    // User sql
    protected static final String USER_FIELD = " id, name, email, password, passport, phone, role, active, closed, language, salt ";
    protected static final String USERS_SQL = String.format("SELECT %s FROM users", USER_FIELD);
    protected static final String FILTER_BY_NAME_SQL = "SELECT id, email, name, language, salt, password, passport, phone, salt, role, active, closed FROM users WHERE name=";
    protected static final String INSERT_USER_SQL = String.format("INSERT INTO users(%s) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );", USER_FIELD);
    public static final String FILTER_USER_BY_ID_SQL = String.format("%sFROM user WHERE id=", USER_FIELD);
    protected static final String UPDATE_USER_SQL = "UPDATE users SET name = '%s', role = '%s', active = '%b', closed = '%b' WHERE id = %d";

    // Order sql
    protected static final String ORDER_INSERT_FIELD = " order_id, user_id, car_id, created, passport, reason, phone, payment, term, driver ";
    protected static final String INSERT_INVOICE = "INSERT INTO invoices (invoice_id, user_id, car_id, damage, payment, reason, passport, phone)";
    protected static final String DELETE_ORDER_SQL = "DELETE FROM orders WHERE order_id=";
    protected static final String UPDATE_ORDER_SQL = "UPDATE orders SET damage ='%s', reason ='%s', payment = %s, rejected = %b, closed = %b WHERE order_id = %d";
    protected static final String ORDER_FIELD = " order_id, user_id, car_id, payment, driver, rejected, closed, created, damage, passport, phone, term, reason ";
    protected static final String ORDER_FIELDS = String.format("SELECT%s", ORDER_FIELD);
    protected static final String FIND_ALL_ORDERS_SQL = String.format("%sFROM orders;", ORDER_FIELDS);
    protected static final String FILTER_ORDER_BY_USER_ID_SQL = ORDER_FIELDS + "FROM orders WHERE user_id=";
    protected static final String FILTER_ORDER_BY_ID_SQL = ORDER_FIELDS + "FROM orders WHERE order_id=?";
    protected static final String INSERT_ORDER_SQL = String.format("INSERT INTO orders(%s) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ? );", ORDER_FIELD);

    // path urls
    public static final String MAIN = "/WEB-INF/views/main.jsp";
    public static final String CART = "/cart";
    public static final String USER = "/user";
    public static final String CARS = "/WEB-INF/views/cars.jsp";
    public static final String CONTRACT = "/WEB-INF/views/contract.jsp";
    public static final String ORDERS = "/WEB-INF/views/order.jsp";
    public static final String USERS = "/WEB-INF/views/users.jsp";
    public static final String LOGIN = "/WEB-INF/views/login.jsp";
    public static final String INDEX = "/WEB-INF/views/index.jsp";
    public static final String FORGOT = "/WEB-INF/views/forgot.jsp";
    public static final String NOT_FOUND = "/WEB-INF/views/error/404.jsp";
    public static final String FORBIDDEN = "/WEB-INF/views/error/403.jsp";

    //colour font
    public static final String BLACK_BOLD = "\033[1;30m";  // BLACK
    public static final String RED = "\033[1;31m";    // RED
    public static final String GREEN = "\033[1;32m"; // GREEN
    public static final String YELLOW = "\033[1;33m"; // YELLOW
    public static final String PURPLE = "\033[1;34m";   // PURPLE
    public static final String BLUE = "\033[1;35m";   // BLUE
    public static final String CYAN = "\033[1;36m";   // CYAN
    public static final String WHITE_BOLD = "\033[1;37m";  // WHITE
    public static final String RESET = "\033[0m";  // Text Reset

}