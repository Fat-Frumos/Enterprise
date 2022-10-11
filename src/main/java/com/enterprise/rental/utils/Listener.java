package com.enterprise.rental.utils;

import com.enterprise.rental.dao.factory.DbManager;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class Listener implements ServletContextListener {

    private static final Logger log = Logger.getLogger(Listener.class.getName());

    public Listener() {
    }

    /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

//        DbManager dbManager = DbManager.getInstance();
//
//        ServletContext servletContext = sce.getServletContext();

//        servletContext.setAttribute("factory", dbManager);

    }

    /* This method is called when the servlet Context is undeployed or Application Server shuts down. */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    //TODO Listener methods
}
