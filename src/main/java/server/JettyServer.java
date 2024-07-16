package server;

import jakarta.servlet.DispatcherType;
import jakarta.servlet.http.HttpServlet;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.FilterHolder;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.eclipse.jetty.servlets.CrossOriginFilter;
import server.servlet.*;
import java.util.EnumSet;


public class JettyServer {

    private static final String HOST = "http://localhost:8080";

    public static void main(String[] args) throws Exception {
        Server server = new Server(8080);
        System.out.println("Server started on " + HOST);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.NO_SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);

        // Stack
        add(context, new StackServlet<>(Number.class), "stack/number");
        add(context, new StackServlet<>(String.class), "stack/string");

        // Queue
        add(context, new QueueServlet<>(Number.class), "queue/number");
        add(context, new QueueServlet<>(String.class), "queue/string");

        // Binary-Search-Tree
        add(context, new BSTreeServlet<>(Double.class), "bst/number");
        add(context, new BSTreeServlet<>(String.class), "bst/string");

        // Heap
        // boolean isMin: if true MinHeap else MaxHeap
        add(context, new HeapServlet<>(Double.class, true), "heap/min/number");
        add(context, new HeapServlet<>(String.class, true), "heap/min/string");
        add(context, new HeapServlet<>(Double.class, false), "heap/max/number");
        add(context, new HeapServlet<>(String.class, false), "heap/max/string");

        // Graphs
        add(context, new GraphServlet<>(Double.class, GraphServlet.GType.DIRECTED), "graph/directed/number");
        add(context, new GraphServlet<>(String.class, GraphServlet.GType.DIRECTED), "graph/directed/string");
        add(context, new GraphServlet<>(Double.class, GraphServlet.GType.UNDIRECTED), "graph/undirected/number");
        add(context, new GraphServlet<>(String.class, GraphServlet.GType.UNDIRECTED), "graph/undirected/string");
        // true == WeightedDirected-, false = WeightedUndirectedGraph
        add(context, new GraphServlet<>(Double.class, GraphServlet.GType.WEIGHTED, true), "graph/weighteddirected/number");
        add(context, new GraphServlet<>(String.class, GraphServlet.GType.WEIGHTED, true), "graph/weighteddirected/string");
        add(context, new GraphServlet<>(Double.class, GraphServlet.GType.WEIGHTED, false), "graph/weightedundirected/number");
        add(context, new GraphServlet<>(String.class, GraphServlet.GType.WEIGHTED, false), "graph/weightedundirected/string");

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

    private static void add(ServletContextHandler context, HttpServlet servlet, String p) {
        String path = "/structures/" + p;
        context.addServlet(new ServletHolder(servlet), path);
        System.out.println(("Servlet at " + HOST + path));
    }
}
