package structs.graph;

import java.util.ArrayList;
import java.util.List;


public class DirectedGraph<T> extends BaseGraph<T> {

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
        return edges.contains(new Edge<>(src,dest));
    }
}
