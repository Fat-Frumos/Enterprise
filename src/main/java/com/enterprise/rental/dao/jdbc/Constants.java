package com.enterprise.rental.dao.jdbc;

public class Constants {
    protected static final String FIELDS = "SELECT id, name, brand, model, path, price, year ";
    protected static final String FIND_ALL_SQL = FIELDS + "FROM car LIMIT 100;";
    protected static final String FILTER_BY_SQL = FIELDS + "FROM car ";
    protected static final String FILTER_BY_ID_SQL = FIELDS + "FROM car WHERE id=?";
    protected static final String FIELD = " id, name, brand, model, path, price, year, date ";
    protected static final String INSERT_CAR_SQL = "INSERT INTO car(" + FIELD + ") VALUES ( ?, ?, ?, ?, ?, ?, ?, ? );";

//    protected static final String FIND_PRICE_ASC_SQL = FIELD + " FROM car order by CAST(price AS Double) asc";
//    protected static final String FIND_PRICE_DESC_SQL = FIELD + " FROM car order by CAST(price AS Double) desc";
//    protected static final String FILTER_BY_BRAND_DESC_SQL = FIELDS + "FROM car WHERE brand =? ORDER BY price DESC";
//    protected static final String FILTER_BY_BRAND_SQL = FIELDS + "FROM car WHERE brand='";
    private Constants() {
    }
}
