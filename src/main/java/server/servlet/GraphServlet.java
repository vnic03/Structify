package server.servlet;

import com.google.gson.*;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import structs.Graph;
import structs.graph.*;
import java.io.BufferedReader;
import java.io.IOException;


public class GraphServlet<T> extends StructureServlet<T> {

    private final Graph<T> graph;

    public GraphServlet(Class<T> type, boolean directed) {
        super(directed ? new DirectedGraph<>() : new UndirectedGraph<>(), type);
        this.graph = (Graph<T>) structure;
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        graph.add(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        JsonObject json = gson.fromJson(body.toString(), JsonObject.class);

        JsonElement v = json.get("value");
        JsonElement e = json.get("edge");

        if (v != null && e == null) {
            T value = gson.fromJson(v, type);
            handlePost(value, resp);

        } else if (e != null) {
            JsonObject edge = e.getAsJsonObject();

            T src = gson.fromJson(edge.get("src"), type);
            T dest = gson.fromJson(edge.get("dest"), type);

            if (edge.has("weight")) {
                double weight = edge.get("weight").getAsDouble();
                graph.addEdge(src, dest, weight);

            } else {
                graph.addEdge(src, dest);
            }
            resp.setStatus(HttpServletResponse.SC_OK);
        }
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) {
            structure.clear();

        } else if ("edge".equals(action)) {
            JsonObject edge = gson.fromJson(value.toString(), JsonObject.class);

            T src = gson.fromJson(edge.get("src"), type);
            T dest = gson.fromJson(edge.get("dest"), type);

            graph.removeEdge(src, dest);

        } else {
            graph.remove(value);
        }
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    @SuppressWarnings("unchecked")
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;

        while ((line = reader.readLine()) != null) {
            body.append(line);
        }

        JsonObject json = gson.fromJson(body.toString(), JsonObject.class);

        if ("vertex".equals(action) || action == null) {
            T value = gson.fromJson(json.get("value"), type);
            handleDelete(value, action, resp);

        } else if ("edge".equals(action)) {
            handleDelete((T) json, action, resp);

        } else {
            resp.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid action");
        }
    }
}
