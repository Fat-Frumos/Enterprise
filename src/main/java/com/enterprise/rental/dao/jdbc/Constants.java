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

    // Order sql

    protected static final String ORDER_FIELD = " order_id, customer_id, car_id, day, payment, driver, rejected, closed, order_date, ended, damage, passport ";

    protected static final String ORDER_INSERT_FIELD = " order_id, user_id, car_id, created, passport ";
    protected static final String ORDER_FIELDS = "SELECT"+ ORDER_FIELD;
    protected static final String FIND_ALL_ORDERS_SQL = ORDER_FIELDS + "FROM orders;";
    protected static final String FILTER_ORDER_BY_SQL = ORDER_FIELDS + "FROM orders ";
    protected static final String FILTER_ORDER_BY_ID_SQL = ORDER_FIELDS + "FROM orders WHERE id=?";
    protected static final String INSERT_ORDER_SQL = "INSERT INTO orders(" + ORDER_INSERT_FIELD + ") VALUES ( ?, ?, ?, ?, ? );";

    // path urls
    public static final String main = "/WEB-INF/views/main.jsp";
    public static final String cars = "/WEB-INF/views/cars.jsp";
    public static final String contact = "/WEB-INF/views/contact.jsp";
    public static final String users = "/WEB-INF/views/users.jsp";
    public static final String login = "/WEB-INF/views/login.jsp";
    public static final String index = "/WEB-INF/views/index.jsp";
    public static final String forgot = "/WEB-INF/views/forgot.jsp";

    private Constants() {
    }
}