package structs.graph;

import structs.Graph;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


abstract class BaseGraph<T> implements Graph<T> {

    protected final List<T> vertices;

    protected final List<Edge<T>> edges;


    protected BaseGraph() {
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    protected BaseGraph(List<T> vertices, List<Edge<T>> edges) {
        this.vertices = new ArrayList<>(vertices);
        this.edges = new ArrayList<>(edges);
    }

    @Override
    public void addVertex(T vertex) {
        vertices.add(vertex);
    }

    @Override
    public void addEdge(T src, T dest) {
        edges.add(new Edge<>(src, dest));
    }

    @Override
    public boolean containsVertex(T vertex) {
        return vertices.contains(vertex);
    }


    @Override
    public Set<T> getAllVertices() {
        return new HashSet<>(vertices);
    }

    @Override
    public List<Edge<T>> getEdges() {
        return new ArrayList<>(edges);
    }

    @Override
    public boolean isEmpty() {
        return vertices.isEmpty();
    }

    @Override
    public int size() {
        return vertices.size();
    }

    @Override
    public void clear() {
        vertices.clear();
        edges.clear();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
