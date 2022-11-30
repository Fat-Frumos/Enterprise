package com.enterprise.rental.utils;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.File;

@WebListener
public class FileContextListener implements ServletContextListener {
    private static final Logger log = Logger.getLogger(FileContextListener.class);

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        String rootPath = System.getProperty("catalina.home");
        ServletContext ctx = servletContextEvent.getServletContext();
        String relativePath = ctx.getInitParameter("templates.img");
        File file = new File(String.format("%s%s%s", rootPath, File.separator, relativePath));
        if (!file.exists()) file.mkdirs();
        log.info("File Directory created to be used for storing files");
        ctx.setAttribute("FILES_DIR_FILE", file);
        ctx.setAttribute("FILES_DIR", String.format("%s%s%s", rootPath, File.separator, relativePath));
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
    }
}