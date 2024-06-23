package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Stack;
import structs.array.ArrayStack;
import java.io.IOException;


public class StackServlet<T> extends StructureServlet<T> {

    public StackServlet(Class<T> type) {
        super(new ArrayStack<>(), type);
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        ((Stack<T>) structure).push(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else ((Stack<T>) structure).pop();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
