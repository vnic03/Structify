package structs.graph;

import java.util.Objects;


public record Edge<T>(T vertex1, T vertex2) {

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Edge<?> edge = (Edge<?>) o;
        return (Objects.equals(vertex1, edge.vertex1) && Objects.equals(vertex2, edge.vertex2)) ||
                (Objects.equals(vertex1, edge.vertex2) && Objects.equals(vertex2, edge.vertex1));
    }

    @Override
    public int hashCode() {
        return Objects.hash(vertex1) + Objects.hash(vertex2);
    }
}
