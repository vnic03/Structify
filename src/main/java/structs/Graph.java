package structs;

import structs.graph.Edge;
import java.util.List;
import java.util.Set;


public interface Graph<T> extends Structure<T> {

    void addVertex(T vertex);

    void addEdge(T src, T dest);

    List<T> getNeighbors(T vertex);

    boolean containsVertex(T vertex);

    boolean containsEdge(T src, T dest);

    Set<T> getAllVertices();

    List<Edge<T>> getEdges();
}
