package com.enterprise.rental.dao.jdbc;

public class Constants {
    // Car sql
    protected static final String CAR_FIELDS = "SELECT id, name, brand, model, path, price, cost, year ";
    protected static final String FIND_ALL_SQL = CAR_FIELDS + "FROM car LIMIT 80;";
    protected static final String FILTER_CAR_BY_SQL = CAR_FIELDS + "FROM car ";
    protected static final String FILTER_BY_ID_SQL = CAR_FIELDS + "FROM car WHERE id=?";
    protected static final String FILTER_BY_CAR_NAME_SQL = CAR_FIELDS + "FROM car WHERE name=?";
    protected static final String FIELD = " id, name, brand, model, path, price, cost, year, date ";
    protected static final String INSERT_CAR_SQL = "INSERT INTO car(" + FIELD + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );";
    // User sql
    protected static final String USERS_SQL = "SELECT id, email, name, password, role, active FROM users";
    protected static final String FILTER_BY_NAME_SQL = "SELECT id, email, name, password, role, active FROM users WHERE name=";
    protected static final String USER_FIELD = " id, name, email, password, role, active ";
    protected static final String INSERT_USER_SQL = "INSERT INTO users(" + USER_FIELD + ") VALUES ( ?, ?, ?, ?, ?, ?);";
    protected static final String FILTER_USER_BY_ID_SQL = USER_FIELD + "FROM user WHERE id=?";
    protected static final String UPDATE_USER_SQL = "UPDATE users SET name = '%s', role = '%s', active = '%b' WHERE id =%d";

    // Order sql
    protected static final String UPDATE_ORDER_SQL = "UPDATE orders SET damage ='%s', payment = %s, rejected = %b, closed = %b WHERE order_id =%d";
    protected static final String ORDER_FIELD = " order_id, user_id, car_id, term, payment, driver, rejected, closed, created, phone, damage, passport ";
    protected static final String ORDER_INSERT_FIELD = " order_id, user_id, car_id, created, passport, phone, payment, term, driver ";
    protected static final String ORDER_FIELDS = "SELECT" + ORDER_FIELD;
    protected static final String FIND_ALL_ORDERS_SQL = ORDER_FIELDS + "FROM orders;";
    protected static final String FILTER_ORDER_BY_USER_ID_SQL = ORDER_FIELDS + "FROM orders WHERE user_id=";
    protected static final String FILTER_ORDER_BY_ID_SQL = ORDER_FIELDS + "FROM orders WHERE id=?";
    protected static final String INSERT_ORDER_SQL = String.format("INSERT INTO orders(%s) VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );", ORDER_INSERT_FIELD);

    // path urls
    public static final String MAIN = "/WEB-INF/views/main.jsp";
    public static final String CARS = "/WEB-INF/views/cars.jsp";
    public static final String CONTRACT = "/WEB-INF/views/contract.jsp";
    public static final String ORDERS = "/WEB-INF/views/order.jsp";
    public static final String USERS = "/WEB-INF/views/users.jsp";
    public static final String LOGIN = "/WEB-INF/views/login.jsp";
    public static final String INDEX = "/WEB-INF/views/index.jsp";
    public static final String FORGOT = "/WEB-INF/views/forgot.jsp";

    private Constants() {
    }
}