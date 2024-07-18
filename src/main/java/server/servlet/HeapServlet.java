package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Heap;
import structs.heap.MaxHeap;
import structs.heap.MinHeap;
import java.io.IOException;


public class HeapServlet<T extends Comparable<T>> extends StructureServlet<T> {

    private final Heap<T> heap;

    public HeapServlet(Class<T> type, boolean isMin) {
        super(isMin ? new MinHeap<>() : new MaxHeap<>(), type);
        this.heap = (Heap<T>) structure;
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        heap.insert(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else heap.remove(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
