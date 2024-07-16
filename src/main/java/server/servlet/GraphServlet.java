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


    public GraphServlet(Class<T> classType, GType graphType, Boolean d) {
        super(init(graphType, d), classType);
    }

    public GraphServlet(Class<T> classType, GType graphType) {
        super(init(graphType, null), classType);
    }


    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        structure.add(value);
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

        JsonElement valueElement = json.get("value");
        JsonElement edgeElement = json.get("edge");

        if (valueElement != null && edgeElement == null) {
            T value = gson.fromJson(valueElement, type);
            handlePost(value, resp);

        } else if (edgeElement != null) {
            JsonObject edge = edgeElement.getAsJsonObject();
            T src = gson.fromJson(edge.get("src"), type);
            T dest = gson.fromJson(edge.get("dest"), type);

            if (edge.has("weight")) {
                double weight = edge.get("weight").getAsDouble();
                ((WeightedGraph<T>) structure).addEdge(src, dest, weight);
            } else {
                ((Graph<T>) structure).addEdge(src, dest);
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
            ((Graph<T>) structure).removeEdge(src, dest);

        } else {
            ((Graph<T>) structure).remove(value);
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

    public enum GType {
        DIRECTED, UNDIRECTED, WEIGHTED
    }

    private static <T> Graph<T> init(GType type, Boolean d) {
        switch (type) {
            case DIRECTED -> {
                return new DirectedGraph<>();
            } case UNDIRECTED -> {
                return new UndirectedGraph<>();
            } case WEIGHTED -> {
                return d ? new WeightedDirectedGraph<>() : new WeightedUndirectedGraph<>();
            }
        }
        return null;
    }
}
