//package com.enterprise.rental.dao.factory;
//
//import org.junit.jupiter.api.Test;
//
//import javax.naming.Context;
//import javax.naming.InitialContext;
//import javax.sql.DataSource;
//import java.sql.Connection;
//import java.sql.ResultSet;
//import java.sql.Statement;
//
//
//public class DBTest {
//
//    String foo = "Not Connected";
//    int bar = -1;
//
//    @Test
//    public void init() {
//        try {
//            Context ctx = new InitialContext();
//            if (ctx == null)
//                throw new Exception("Boom - No Context");
//
//            DataSource ds =
//                    (DataSource) ctx.lookup(
//                            "java:comp/env/jdbc/TestDB");
//
//            if (ds != null) {
//                Connection conn = ds.getConnection();
//
//                if (conn != null) {
//                    foo = "Got Connection " + conn.toString();
//                    Statement stmt = conn.createStatement();
//                    ResultSet rst =
//                            stmt.executeQuery(
//                                    "select id, foo, bar from testdata");
//                    if (rst.next()) {
//                        foo = rst.getString(2);
//                        bar = rst.getInt(3);
//                    }
//                    conn.close();
//                }
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//    }
//
//    @Test
//    public String getFoo() {
//        return foo;
//    }
//
//    @Test
//    public int getBar() {
//        return bar;
//    }
//}