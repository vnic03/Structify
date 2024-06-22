package structs.graph;

import java.util.Objects;
import java.util.Optional;

// param <V> Type of the Vertex
public record Edge<V>(V source, V destination, Optional<Double> weight) implements Comparable<Edge<V>> {

    public Edge(V source, V destination) {
        this(source, destination, Optional.empty());
    }

    public Edge(V source, V destination, double weight) {
        this(source, destination, Optional.of(weight));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return (Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination) &&
                Objects.equals(weight, edge.weight)) ||
                (Objects.equals(source, edge.destination) && Objects.equals(destination, edge.source) &&
                        Objects.equals(weight, edge.weight));
    }

    @Override
    public String toString() {
        return source + " -> " + destination + weight.map(w -> "[weight=" + w + "]").orElse("");
    }

    @Override
    public int compareTo(Edge<V> other) {
        if (this.weight.isPresent() && other.weight.isPresent()) {
            return Double.compare(this.weight.get(), other.weight.get());
        }
        else if (this.weight.isPresent()) return 1;
        else if (other.weight.isPresent()) return -1;
        else return 0;
    }
}
