package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Queue;
import structs.array.ArrayQueue;
import java.io.IOException;


public class QueueServlet<T> extends StructureServlet<T> {

    private final Queue<T> queue;

    public QueueServlet(Class<T> type) {
        super(new ArrayQueue<>(), type);
        this.queue = (Queue<T>) structure;
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        queue.enqueue(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else queue.dequeue();
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
