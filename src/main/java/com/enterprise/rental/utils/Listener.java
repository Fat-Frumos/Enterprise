package com.enterprise.rental.utils;

import com.enterprise.rental.filter.UserFilter;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class Listener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Listener.class);
    public Listener() {

    }

    /* This method is called when the servlet context is initialized(when the Web application is deployed). */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        String webAppPath = servletContext.getRealPath("/");
        String log4jFilePath = String.format("%sWEB-INF/classes/log4j.xml", webAppPath);
        DOMConfigurator.configure(log4jFilePath);
        log.info(String.format("Initialized log4j configuration from file:%s%n", log4jFilePath));

    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}
