package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Stack;
import structs.array.ArrayStack;
import java.io.IOException;


public class StackServlet<T> extends StructureServlet<T> {

    private final Stack<T> stack;

    public StackServlet(Class<T> type) {
        super(new ArrayStack<>(), type);
        this.stack = (Stack<T>) structure;
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        stack.push(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else stack.pop();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
