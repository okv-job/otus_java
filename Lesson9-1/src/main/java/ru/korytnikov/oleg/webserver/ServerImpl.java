package ru.korytnikov.oleg.webserver;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.ContextHandler;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.korytnikov.oleg.webserver.controller.Login;
import ru.korytnikov.oleg.webserver.controller.Logout;
import ru.korytnikov.oleg.webserver.controller.State;
import ru.korytnikov.oleg.webserver.controller.Status;
import ru.korytnikov.oleg.webserver.filter.AuthFilter;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class ServerImpl {

    private static final int PORT = 8080;
    private static final String STATIC = "static";

    public ServerImpl() throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(STATIC);
        resourceHandler.setWelcomeFiles(new String[]{"index.html"});

        ContextHandler resCtx = new ContextHandler();
        resCtx.setContextPath("/");
        resCtx.setHandler(resourceHandler);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(Login.class, "/login");
        context.addServlet(Status.class, "/status");
        context.addServlet(Logout.class, "/logout");
        context.addServlet(State.class, "/state");

        context.addFilter(AuthFilter.class, "/status",
                EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST));

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resCtx, context));

        server.start();
        server.join();
    }
}
