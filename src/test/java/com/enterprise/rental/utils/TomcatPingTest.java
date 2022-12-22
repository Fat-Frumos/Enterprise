//package com.enterprise.rental.utils;
//
//import org.apache.catalina.LifecycleException;
//import org.apache.catalina.startup.Tomcat;
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.jupiter.api.Test;
//
//import java.io.File;
//
///**
// * Minimal tomcat starter for unit tests.
// *
// * @author Pasha Pollack
// * @see Tomcat
// */
//public class TomcatPingTest {
//    private static Tomcat tomcat;
//    private static final int TOMCAT_PORT = 8888;
//
//    @BeforeClass
//    public static void setUp() throws LifecycleException {
//        tomcat = new Tomcat();
//        tomcat.setBaseDir(".");
//        tomcat.setPort(TOMCAT_PORT);
//        /* There needs to be a symlink to the current dir named 'webapps' */
//        tomcat.addWebapp("/", "src/main/webapp");
//        tomcat.init();
//        tomcat.start();
//    }
//
//    @AfterClass
//    public static void shutDownTomcat() throws LifecycleException {
//        tomcat.stop();
//    }
//
//    @Test
//    void main() throws Exception {
//
//        Tomcat tomcat = new Tomcat();
//
//        File appDir = new File("test/webapp-3.0");
////        tomcat.addWebapp(null, "", appDir.getAbsolutePath());
//        tomcat.start();
//    }
//}