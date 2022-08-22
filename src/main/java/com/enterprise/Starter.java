package com.enterprise;

import com.enterprise.dao.jdbc.JdbcCarDao;
import com.enterprise.service.CarService;
import com.enterprise.servlet.CarServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class Starter {


    public static void main(String[] args) throws Exception {
        ServletContextHandler contextHandler =
                new ServletContextHandler(ServletContextHandler.SESSIONS);

        contextHandler
                .addServlet(
                        new ServletHolder(
                                new CarServlet(
                                        new CarService(
                                                new JdbcCarDao()))), "/cars/*");

        Server server = new Server(8080);
        server.setHandler(contextHandler);
        server.start();
    }
}
