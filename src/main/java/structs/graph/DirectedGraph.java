package structs.graph;

import java.util.ArrayList;
import java.util.List;


public class DirectedGraph<T> extends BaseGraph<T> {

    @Override
    public List<T> getNeighbors(T vertex) {
        final List<T> neighbors = new ArrayList<>();
        for (Edge<T> edge : edges) {
            if (edge.vertex1().equals(vertex)) {
                neighbors.add(edge.vertex2());
            }
        }
        return neighbors;
    }

    @Override
    public boolean containsEdge(T src, T dest) {
        return edges.contains(new Edge<>(src,dest));
    }
}
