package structs.graph;

import java.util.Objects;


public record Edge<T>(T source, T destination) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return (Objects.equals(source, edge.source) && Objects.equals(destination, edge.destination)) ||
                (Objects.equals(source, edge.destination) && Objects.equals(destination, edge.source));
    }

    @Override
    public String toString() {
        return source + " -> " + destination;
    }

    @Override
    public int hashCode() {
        return Objects.hash(source) + Objects.hash(destination);
    }
}
