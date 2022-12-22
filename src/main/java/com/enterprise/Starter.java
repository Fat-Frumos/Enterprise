package com.enterprise;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.core.StandardContext;
import org.apache.catalina.startup.Tomcat;
import org.apache.log4j.Logger;

/**
 * Minimal tomcat starter for embedding.
 * <p>
 * Tomcat supports multiple styles of configuration and
 * startup - the most common and stable is server.xml-based,
 * implemented in org.apache.catalina.startup.Bootstrap.
 *
 * @author Pasha Pollack
 */
public class Starter {
    private static final Logger log = Logger.getLogger(Starter.class);
    public static final int TOMCAT_PORT = 8000;
    private static final String CONTEXT_PATH = "";
    public static final String TOMCAT_HOSTNAME = "127.0.0.1";
    public static final String INTERNAL_PATH = "/";

    /**
     * This class is for use in apps that embed tomcat.
     *
     * @param args arguments to pass from CLI
//     * @throws LifecycleException General purpose exception that is thrown
//     *                            to indicate a lifecycle related problem.
     */
    public static void main(String[] args) throws LifecycleException {

        Tomcat tomcatServer = new Tomcat();
        // The user should ensure that the file permissions for the base directory are appropriate.
        tomcatServer.setBaseDir(".");
        //Set the port for the default connector.
        tomcatServer.setPort(TOMCAT_PORT);
        // The hostname of the default host, default is 'localhost'
        tomcatServer.setHostname(TOMCAT_HOSTNAME);
        // Set the auto deploy flag value for this host.
        tomcatServer.getHost().setAutoDeploy(false);
        // Standard implementation of the Context interface.
        StandardContext standardContext = new StandardContext();
        standardContext.setPath(CONTEXT_PATH);
        // Add a LifecycleEvent listener to this component.
        standardContext.addLifecycleListener(new Tomcat.FixContextListener());
        standardContext.setReloadable(false);
        tomcatServer.start();

        log.debug(String.format("Tomcat started to http://localhost:%d%s%s%n", TOMCAT_PORT, CONTEXT_PATH, INTERNAL_PATH));
        tomcatServer.getServer().await();
    }
}