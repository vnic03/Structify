package structs.graph;

import java.util.NoSuchElementException;


public class WeightedDirectedGraph<T> extends DirectedGraph<T> implements WeightedGraph<T> {

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
        Edge<T> edge = new Edge<>(this, src, dest, weight);
        edges.add(edge);
    }

    @Override
    public double getWeight(T src, T dest) {
        return edges.stream()
                .filter(edge -> edge.source().equals(src) && edge.destination().equals(dest))
                .findFirst().flatMap(Edge::weight)
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
                    .append(" -> ")
                    .append(edge.destination().toString())
                    .append(" [")
                    .append(edge.weight().get())
                    .append("]\n");
        }
        return sb.toString();
    }
}
