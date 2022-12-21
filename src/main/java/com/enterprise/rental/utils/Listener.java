package com.enterprise.rental.utils;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Implementations of this interface receive notifications (log4j) about
 * changes to the servlet context of the web application they are part of.
 *
 * @author Pasha Pollack
 * @see ServletContextListener
 */
@WebListener
public class Listener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(Listener.class);

    /**
     * Default listener constructor
      */
    public Listener() {
    }

    /**
     * This method is called when the servlet context is initialized
     * (when the Web application is deployed).
     *
     * @param sce is the event class for notifications about changes
     *            to the servlet context of a web application.
     * @see ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {

        ServletContext servletContext = sce.getServletContext();
        String log4jFilePath = String.format(
                "%sWEB-INF/classes/log4j.xml",
                servletContext.getRealPath("/"));
        DOMConfigurator.configure(log4jFilePath);
        log.debug(String.format(
                "initialized log4j configuration from file:%s%n",
                log4jFilePath));
    }

    /**
     * Notification that the servlet context is about to be shut down.
     * All servlets and filters have been destroyed before
     * any ServletContextListeners are notified of context destruction
     *
     * @param sce is the event class for notifications
     * @see ServletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.debug("ServletContextListener destroyed");
    }
}