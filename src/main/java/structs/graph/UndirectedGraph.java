package structs.graph;

import java.util.HashSet;
import java.util.Set;


public class UndirectedGraph<T> extends BaseGraph<T> {

    @Override
    public boolean removeEdge(T src, T dest) {
        boolean result = edges.removeIf(
                edge -> edge.source().equals(src) && edge.destination().equals(dest) ||
                        edge.source().equals(dest) && edge.destination().equals(src)
                );
        if (isWeighted) {
            isWeighted = edges.stream().anyMatch(e -> e.weight() != null);
        }
        return result;
    }

    @Override
    public Edge<T> getEdge(T src, T dest) {
        for (Edge<T> edge : edges) {
            if ((edge.source().equals(src) && edge.destination().equals(dest)) ||
                    (edge.source().equals(dest) && edge.destination().equals(src)))
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
            
            } else if (edge.destination().equals(vertex)) {
                neighbors.add(edge.source());
            }
        }
        return neighbors;
    }

    @Override
    public boolean containsEdge(T src, T dest) {
        return edges.contains(new Edge<>(this, src, dest)) ||
                edges.contains(new Edge<>(this, dest, src));
    }
}
