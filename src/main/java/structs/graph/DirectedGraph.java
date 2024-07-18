package structs.graph;

import java.util.HashSet;
import java.util.Set;


public class DirectedGraph<T> extends BaseGraph<T> {

    @Override
    public boolean removeEdge(T src, T dest) {
        return edges.removeIf(
                edge -> edge.source().equals(src) && edge.destination().equals(dest)
        );
    }

    @Override
    public Edge<T> getEdge(T src, T dest) {
        for (Edge<T> edge : edges) {
            if (edge.source().equals(src) && edge.destination().equals(dest))
            {
                return edge;
            }
        }
        return null;
    }

    @Override
    public Set<T> getNeighbors(T vertex) {
        final Set<T> neighbors = new HashSet<>();
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