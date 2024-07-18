package structs.graph;

import annotation.NeedsFixing;
import structs.Graph;
import java.util.HashSet;
import java.util.Set;


abstract class BaseGraph<T> implements Graph<T> {

    protected final Set<T> vertices;

    protected final Set<Edge<T>> edges;

    private boolean isWeighted;


    protected BaseGraph() {
        this.vertices = new HashSet<>();
        this.edges = new HashSet<>();
        this.isWeighted = false;
    }

    protected BaseGraph(Set<T> vertices, Set<Edge<T>> edges) {
        this.vertices = Set.copyOf(vertices);
        this.edges = Set.copyOf(edges);
        this.isWeighted = edges.stream().anyMatch(e -> e.weight() != null);
    }

    @Override
    public void add(T vertex) {
        vertices.add(vertex);
    }

    @Override
    public boolean contains(T vertex) {
        return vertices.contains(vertex);
    }

    @Override
    public Set<T> getVertices() {
        return Set.copyOf(vertices);
    }

    @Override
    public Set<Edge<T>> getEdges() {
        return Set.copyOf(edges);
    }

    @Override
    public boolean remove(T vertex) {
        if (!vertices.contains(vertex)) return false;
        edges.removeIf(
                edge -> edge.source().equals(vertex) ||
                edge.destination().equals(vertex)
        );
        return vertices.remove(vertex);
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
        isWeighted = false;
    }

    @Override
    public void addEdge(T src, T dest) {
        this.addEdge(src, dest, null);
    }

    @Override
    public void addEdge(T src, T dest, Double weight) {
        if (!vertices.contains(src)) {
            throw new IllegalArgumentException(
                    String.format("Source vertex %s does not exist in the graph!", src)
            );
        }
        if (!vertices.contains(dest)) {
            throw new IllegalArgumentException(
                    String.format("Destination vertex %s does not exist in the graph!", dest)
            );
        }
        edges.add(new Edge<>(this, src, dest, weight));
        if (weight != null) isWeighted = true;
    }

    @Override
    public boolean isWeighted() {
        return isWeighted;
    }

    @Override
    public Double getWeight(T src, T dest) {
        Edge<T> edge = getEdge(src, dest);
        if (edge == null || edge.weight() == null) {
            return null;
        }
        return edge.weight();
    }

    @Override
    @NeedsFixing(reason = "If !weighted return something else")
    public double weight() {
        if (!isWeighted) return 0;
        double sum = 0;
        for (Edge<T> edge : edges) {
            if (edge.weight() != null) {
                sum += edge.weight();
            }
        }
        return sum;
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(this instanceof DirectedGraph ? " -> " : " <---> ")
                    .append(edge.destination().toString());
            if (edge.weight() != null) {
                sb.append(" [").append(edge.weight()).append("]");
            }
            sb.append("\n");
        }
        return sb.toString();
    }
}
