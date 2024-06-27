package server.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import structs.Structure;


abstract class StructureServlet<T> extends HttpServlet {

    private final Gson gson = new Gson();

    protected final Structure<T> structure;

    private final Class<T> type;


    protected StructureServlet(Structure<T> structure, Class<T> type) {
        this.structure = structure;
        this.type = type;
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
        T value = gson.fromJson(json.get("value"), type);

        handlePost(value, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        JsonObject responseJson = new JsonObject();
        responseJson.add("structure", gson.toJsonTree(structure));
        resp.setContentType("application/json");
        resp.getWriter().write(responseJson.toString());
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        BufferedReader reader = req.getReader();
        StringBuilder body = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            body.append(line);
        }
        JsonObject json = gson.fromJson(body.toString(), JsonObject.class);
        T value = gson.fromJson(json.get("value"), type);

        handleDelete(value, action, resp);
    }

    protected abstract void handlePost(T value, HttpServletResponse resp) throws IOException;

    protected abstract void handleDelete(T value, String action, HttpServletResponse resp) throws IOException;
}
