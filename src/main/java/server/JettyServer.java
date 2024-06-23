package server;

import jakarta.servlet.DispatcherType;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import server.servlet.QueueServlet;
import server.servlet.StackServlet;

import java.util.EnumSet;


public class JettyServer {

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        System.out.println("Port on: http://localhost:8080");

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        context.addServlet(new ServletHolder(new StackServlet<>(Number.class)), "/structures/stack/number");
        context.addServlet(new ServletHolder(new StackServlet<>(String.class)), "/structures/stack/string");

        context.addServlet(new ServletHolder(new QueueServlet<>(Number.class)), "/structures/queue/number");
        context.addServlet(new ServletHolder(new QueueServlet<>(String.class)), "/structures/queue/string");


        FilterHolder cors = context.addFilter(
                CrossOriginFilter.class, "/*",
                EnumSet.of(DispatcherType.INCLUDE, DispatcherType.REQUEST)
        );

        cors.setInitParameter(CrossOriginFilter.ALLOWED_ORIGINS_PARAM, "*");
        cors.setInitParameter(CrossOriginFilter.ACCESS_CONTROL_ALLOW_ORIGIN_HEADER, "*");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_METHODS_PARAM, "GET,POST,DELETE,HEAD");
        cors.setInitParameter(CrossOriginFilter.ALLOWED_HEADERS_PARAM, "X-Requested-With,Content-Type,Accept,Origin");

        server.start();
        server.join();
    }
}
