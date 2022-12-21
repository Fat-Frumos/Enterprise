package com.enterprise.rental.utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

/**
 * Implementations of this interface receive files about
 * changes to the servlet context of the web application they are part of.
 *
 * @author Pasha Pollack
 * @see ServletContextListener
 */
@WebListener
public class FileContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(FileContextListener.class);

    /**
     * This method is called when the servlet context is initialized
     * (when the Web application is deployed).
     *
     * @param servletContextEvent is the event class for notifications about changes
     *                            to the servlet context of a web application.
     */
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter("templates.img");
        File file = new File(String.format("%s%s%s", rootPath, File.separator, relativePath));
        if (!file.exists()) file.mkdirs();
        log.debug("File Directory created to be used for storing files");
        ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute("FILES_DIR", String.format("%s%s%s", rootPath, File.separator, relativePath));
    }

    /**
     * Notification that the servlet context is about to be shut down.
     * All servlets and filters have been destroyed before
     * any ServletContextListeners are notified of context destruction
     *
     * @param servletContextEvent is the event class for notifications
     */
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        log.debug("ServletContextListener destroyed");
    }
}