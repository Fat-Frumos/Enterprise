package com.enterprise.rental.utils;

import com.enterprise.rental.dao.factory.ConnectionFactory;

import javax.servlet.*;
import javax.servlet.http.*;

public class Listener implements ServletContextListener {

    public Listener() {
    }

    /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();

    }

    /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    //TODO Listener methods
}
