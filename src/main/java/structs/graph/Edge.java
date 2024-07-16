package structs.graph;

import structs.Graph;
import java.util.Objects;


// param <V> Type of the Vertex
public record Edge<V>(Graph<V> graph, V source, V destination, Double weight)
        implements Comparable<Edge<V>>
{

    public Edge(Graph<V> graph, V source, V destination) {
        this(graph, source, destination, null);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        if (graph instanceof DirectedGraph) {
            return Objects.equals(source, edge.source) &&
                    Objects.equals(destination, edge.destination) &&
                    Objects.equals(weight, edge.weight);
        } else {
            return (Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination) &&
                    Objects.equals(weight, edge.weight)) ||
                    (Objects.equals(source, edge.destination) && Objects.equals(destination, edge.source) &&
                            Objects.equals(weight, edge.weight));
        }
    }

    @Override
    public String toString() {
        boolean directed = graph instanceof DirectedGraph;
        return source + (directed ? "->" : "--") + destination +
                (weight != null ? "[w: " + weight + "]" : "");
    }

    @Override
    public int compareTo(Edge<V> other) {
        if (this.weight != null && other.weight != null) {
            return Double.compare(this.weight, other.weight);
        }
        else if (this.weight != null) return 1;
        else if (other.weight != null) return -1;
        else return 0;
    }
}
