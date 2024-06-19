package structs.graph;

import java.util.HashMap;
import java.util.Map;


public class WeightedDirectedGraph<T> extends DirectedGraph<T> implements WeightedGraph<T> {

    private final Map<Edge<T>, Double> weights;


    public WeightedDirectedGraph() {
        super();
        this.weights = new HashMap<>();
    }

    @Override
    public void addEdge(T src, T dest, double weight) {
        Edge<T> edge = new Edge<>(src, dest);
        super.addEdge(src, dest);
        weights.put(edge, weight);
    }

    @Override
    public double getWeight(T src, T dest) {
        return weights.getOrDefault(new Edge<>(src, dest), Double.NaN);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "{ }";
        StringBuilder sb = new StringBuilder();
        sb.append("Vertices: ").append(vertices.toString()).append("\n");
        sb.append("Edges with weights:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(" -> ")
                    .append(edge.destination().toString())
                    .append(" [Weight: ")
                    .append(weights.get(edge))
                    .append("]\n");
        }
        return sb.toString();
    }
}
