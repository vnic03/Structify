package structs.graph;

import java.util.ArrayList;
import java.util.List;


public class DirectedGraph<T> extends BaseGraph<T> {

    @Override
    public void addEdge(T src, T dest) {
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
        edges.add(new Edge<>(this, src, dest));
    }

    @Override
    public boolean removeEdge(T src, T dest) {
        return edges.removeIf(edge -> edge.source().equals(src) && edge.destination().equals(dest));
    }

    @Override
    public Edge<T> getEdge(T src, T dest) {
        for (Edge<T> edge : edges) {
            if (edge.source().equals(src) && edge.destination().equals(dest)) {
                return edge;
            }
        }
        return null;
    }

    @Override
    public List<T> getNeighbors(T vertex) {
        final List<T> neighbors = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (edge.source().equals(vertex)) {
                neighbors.add(edge.destination());
            }
        }
        return neighbors;
    }

    @Override
    public boolean containsEdge(T src, T dest) {
        return edges.contains(new Edge<>(this, src, dest));
    }
}