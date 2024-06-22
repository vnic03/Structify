package structs.graph;


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
        Edge<T> edge = new Edge<>(src, dest, weight);
        edges.add(edge);
    }

    @Override
    public double getWeight(T src, T dest) {
        return edges.stream()
                .filter(e -> e.source().equals(src) && e.destination().equals(dest))
                .findFirst().flatMap(Edge::weight).orElse(Double.NaN);
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
        sb.append("Edges with weights:\n");
        for (Edge<T> edge : edges) {
            sb.append(edge.source().toString())
                    .append(" <---> ")
                    .append(edge.destination().toString())
                    .append(" [")
                    .append(edge.weight().get())
                    .append("]\n");
        }
        return sb.toString();
    }
}
