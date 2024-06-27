package structs;

import structs.graph.Edge;
import java.util.List;
import java.util.Set;


public interface Graph<T> extends Structure<T> {

    void add(T vertex);

    boolean remove(T vertex);

    void addEdge(T src, T dest);

    boolean removeEdge(T src, T dest);

    List<T> getNeighbors(T vertex);

    boolean contains(T vertex);

    boolean containsEdge(T src, T dest);

    Set<T> getAllVertices();

    List<Edge<T>> getEdges();
}
