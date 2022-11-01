package com.enterprise.rental.utils;

import org.apache.log4j.PropertyConfigurator;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.util.logging.Logger;

@WebListener
public class Listener implements ServletContextListener {

    public Listener() {

    }

    /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        String webAppPath = servletContext.getRealPath("/");
        String log4jFilePath = webAppPath + "WEB-INF/classes/log4j.xml";
        DOMConfigurator.configure(log4jFilePath);
        System.out.println("initialized log4j configuration from file:"+log4jFilePath);

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
