package structs.graph;

import structs.Graph;
import java.util.*;


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
    public void add(T vertex) {
        if (!vertices.contains(vertex)) vertices.add(vertex);
    }

    @Override
    public boolean contains(T vertex) {
        return vertices.contains(vertex);
    }


    @Override
    public Set<T> getAllVertices() {
        return Set.copyOf(vertices);
    }

    @Override
    public List<Edge<T>> getEdges() {
        return List.copyOf(edges);
    }

    @Override
    public boolean remove(T vertex) {
        if (!vertices.contains(vertex)) return false;
        edges.removeIf(edge -> edge.source().equals(vertex) || edge.destination().equals(vertex));
        return vertices.remove(vertex);
    }

    @Override
    public boolean removeEdge(T src, T dest) {
        return edges.removeIf(edge -> edge.source().equals(src) && edge.destination().equals(dest));
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
        if (isEmpty()) return "{ }";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(this instanceof DirectedGraph ? " -> " : " <---> ")
                    .append(edge.destination().toString())
                    .append("\n");
        }
        return sb.toString();
    }
}
