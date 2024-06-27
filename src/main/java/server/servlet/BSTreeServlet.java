package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Tree;
import structs.tree.binary.BinarySearchTree;
import java.io.IOException;


public class BSTreeServlet<T extends Comparable<T>> extends StructureServlet<T> {

    public BSTreeServlet(Class<T> type) {
        super(new BinarySearchTree<>(), type);
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        ((Tree<T>) structure).insert(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else ((Tree<T>) structure).remove(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
