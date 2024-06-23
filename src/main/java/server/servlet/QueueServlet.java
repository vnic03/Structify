package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Queue;
import structs.array.ArrayQueue;
import java.io.IOException;


public class QueueServlet<T> extends StructureServlet<T> {

    public QueueServlet(Class<T> type) {
        super(new ArrayQueue<>(), type);
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        ((Queue<T>) structure).enqueue(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) (structure).clear();
        else ((Queue<T>) structure).dequeue();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
