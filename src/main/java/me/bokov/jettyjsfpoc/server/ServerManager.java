package me.bokov.jettyjsfpoc.server;

import com.sun.faces.config.ConfigureListener;
import lombok.extern.slf4j.Slf4j;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.webapp.WebAppContext;
import org.jboss.weld.environment.servlet.Listener;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Slf4j
public final class ServerManager {

    private static final ServerManager INSTANCE = new ServerManager ();

    private Server jettyServer = null;

    private ServerManager () {

    }

    public static void start () {
        INSTANCE.doStart ();
    }

    public static void stop () {
        INSTANCE.doStop ();
    }

    private void doStart () {

        log.info ("Starting");

        jettyServer = new Server (getServerPort ());

        // Create the web application context
        WebAppContext context = new WebAppContext (
                ServerManager.class.getResource ("/webapp").toExternalForm (),
                "/"
        );

        jettyServer.setHandler (context);

        try {
            jettyServer.start ();
        } catch (Exception exc) {
            log.error ("Could not start Jetty server", exc);
        }

    }

    private void doStop () {

    }

    private int getServerPort () {
        return Integer.parseInt (
                System.getProperty ("me.bokov.jettyjsfpoc.port", "8080")
        );
    }

}
