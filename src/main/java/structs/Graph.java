package structs;

import structs.array.ArrayStack;
import structs.graph.Edge;
import structs.graph.WeightedGraph;
import structs.linked.LinkedQueue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import java.util.List;


public interface Graph<T> extends Structure<T> {

    void addVertex(T vertex);

    void addEdge(T src, T dest);

    List<T> getNeighbors(T vertex);

    boolean containsVertex(T vertex);

    boolean containsEdge(T src, T dest);

    Set<T> getAllVertices();

    List<Edge<T>> getEdges();

    // TODO: remove

    default List<T> bfs(T start) {
        if (!(this instanceof WeightedGraph)) {
            Queue<T> queue = new LinkedQueue<>();
            Set<T> visited = new HashSet<>();
            List<T> way = new ArrayList<>();

            queue.enqueue(start);
            visited.add(start);

            while (!queue.isEmpty()) {
                T vertex = queue.dequeue();
                way.add(vertex);

                for (T neighbor : getNeighbors(vertex)) {
                    if (!visited.contains(neighbor)) {
                        queue.enqueue(neighbor);
                        visited.add(neighbor);
                    }
                }
            }
            return way;
        }
        throw new UnsupportedOperationException("Only works with Unweighted graphs");
    }

    default List<T> dfs(T start) {
        if (!(this instanceof WeightedGraph)) {
            Stack<T> stack = new ArrayStack<>();
            Set<T> visited = new HashSet<>();
            List<T> way = new ArrayList<>();

            stack.push(start);

            while (!stack.isEmpty()) {
                T vertex = stack.pop();

                if (!visited.contains(vertex)) {
                    visited.add(vertex);
                    way.add(vertex);

                    for (T neighbor : getNeighbors(vertex)) {
                        if (!visited.contains(neighbor)) {
                            stack.push(neighbor);
                        }
                    }
                }
            }
            return way;
        }
        throw new UnsupportedOperationException("Only works with Unweighted graphs");
    }

    default void printPath(List<T> path) {
        if (path == null || path.isEmpty()) {
            System.out.println("No path found");
            return;
        }
        StringBuilder sb = new StringBuilder();
        sb.append("Path: ");
        for (T vertex : path) {
            sb.append(vertex).append(" -> ");
        }
        sb.setLength(sb.length() - 4);
        System.out.println(sb);
    }
}
