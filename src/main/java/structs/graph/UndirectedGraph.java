package structs.graph;

import java.util.ArrayList;
import java.util.List;


public class UndirectedGraph<T> extends BaseGraph<T> {

    @Override
    public List<T> getNeighbors(T vertex) {
        final List<T> neighbors = new ArrayList<>();
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
        return edges.contains(new Edge<>(src, dest)) || edges.contains(new Edge<>(dest, src));
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{ }";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices.toString()).append("\n");
        sb.append("Edges:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(" <---> ")
                    .append(edge.destination().toString())
                    .append("\n");
        }
        return sb.toString();
    }
}
