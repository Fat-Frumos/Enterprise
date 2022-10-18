package com.enterprise.rental.dao.jdbc;

public class Constants {
    protected static final String FIELDS = "SELECT id, name, brand, model, path, price, cost, year ";
    protected static final String FIND_ALL_SQL = FIELDS + "FROM car LIMIT 100;";
    protected static final String FILTER_BY_SQL = FIELDS + "FROM car ";
    protected static final String FILTER_BY_ID_SQL = FIELDS + "FROM car WHERE id=?";
    protected static final String FIELD = " id, name, brand, model, path, price, cost, year, date ";
    protected static final String INSERT_CAR_SQL = "INSERT INTO car(" + FIELD + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ?, ? );";

    private Constants() {
    }
}
