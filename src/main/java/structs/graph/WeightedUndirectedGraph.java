package structs.graph;

import java.util.NoSuchElementException;


public class WeightedUndirectedGraph<T> extends UndirectedGraph<T> implements WeightedGraph<T> {

    @Override
    public void addEdge(T src, T dest, double weight) {
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
    }

    @Override
    public double getWeight(T src, T dest) {
        return edges.stream()
                .filter(e -> e.source().equals(src) && e.destination().equals(dest))
                .findFirst()
                .map(edge -> {
                    if (edge.weight() == null) {
                        throw new NoSuchElementException("Weight for the edge from " + src + " to " + dest + " not found");
                    }
                    return edge.weight();
                })
                .orElseThrow(() -> new NoSuchElementException("src: " + src + " or dest: " + dest + " not found"));
    }

    @Override
    public void addEdge(T src, T dest) {
        throw new IllegalArgumentException(
                String.format("Weight is missing for the edge from %s to %s!", src, dest)
        );
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{ }";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices).append("\n");
        sb.append("Edges:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(" <---> ")
                    .append(edge.destination().toString())
                    .append(" [")
                    .append(edge.weight())
                    .append("]\n");
        }
        return sb.toString();
    }
}
