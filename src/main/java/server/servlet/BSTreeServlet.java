package server.servlet;

import jakarta.servlet.http.HttpServletResponse;
import structs.Tree;
import structs.tree.binary.BinarySearchTree;
import java.io.IOException;


public class BSTreeServlet<T extends Comparable<T>> extends StructureServlet<T> {

    private final Tree<T> tree;

    public BSTreeServlet(Class<T> type) {
        super(new BinarySearchTree<>(), type);
        this.tree = (Tree<T>) structure;
    }

    @Override
    protected void handlePost(T value, HttpServletResponse resp) throws IOException {
        tree.insert(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }

    @Override
    protected void handleDelete(T value, String action, HttpServletResponse resp) throws IOException {
        if ("clear".equals(action)) structure.clear();
        else tree.remove(value);
        resp.setStatus(HttpServletResponse.SC_OK);
    }
}
