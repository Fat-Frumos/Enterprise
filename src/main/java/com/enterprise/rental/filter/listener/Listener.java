package com.enterprise.rental.filter.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import org.apache.log4j.xml.DOMConfigurator;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Implementations of this interface receive notifications (log4j) about
 * changes to the servlet context of the web application they are part of.
 *
 * @author Pasha Polyak
 * @see ServletContextListener
 */
@WebListener
public class Listener implements ServletContextListener {

    private ServletContextEvent contextEvent;
    private static final Logger LOGGER = LogManager.getLogger();

    /**
     * method to init log4j configurations, should be called first before using logging
     */
    private static void init() {
        DOMConfigurator.configure("WEB-INF/classes/log4j.xml");
        // OR for property file, should use any one of these
        //PropertyConfigurator.configure("myapp-log4j.properties");
    }

    /**
     * This method to init log4j configurations, should be called first before using logging
     * is called when the servlet context is initialized
     * (when the Web application is deployed).
     *
     * @param servletContext is the event class for notifications about changes
     *                       to the servlet context of a web application.
     * @see ServletContextEvent
     */
    @Override
    public void contextInitialized(ServletContextEvent servletContext) {
        String webAppPath = servletContext.getServletContext().getRealPath("/");
        String log4jFilePath = String.format("%sWEB-INF/classes/log4j.xml", webAppPath);
        DOMConfigurator.configure(log4jFilePath);
        System.out.println("initialized log4j configuration from file:" + log4jFilePath);

//        ServletContext servletContext = sce.getServletContext();
//        String log4jFilePath = String.format(
//                "%ssrc/main/resources/log4j.xml",
//                servletContext.getRealPath("/"));
//        DOMConfigurator.configure("log4j.xml");
        LOGGER.log( Level.INFO, "initialized log4j configuration from file:{}%n",
                log4jFilePath);
        this.contextEvent = servletContext;
    }


    /**
     * Notification that the servlet context is about to be shut down.
     * All servlets and filters have been destroyed before
     * any ServletContextListeners are notified of context destruction
     *
     * @param paramServletContextEvent is the event class for notifications
     * @see ServletContextEvent
     */
    @Override
    public void contextDestroyed(ServletContextEvent paramServletContextEvent) {
        LOGGER.log( Level.INFO, "ServletContextListener destroyed");
        contextEvent = paramServletContextEvent;
    }
}
