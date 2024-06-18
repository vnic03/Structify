package structs.graph.adjacency;

import structs.Graph;
import structs.Structure;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AdjacencyList<T> implements Structure<T> {

    private final Map<T, List<T>> adjacency;


    public AdjacencyList(Graph<T> graph) {
        this.adjacency = new HashMap<>();
        init(graph);
    }

    private void init(Graph<T> graph) {
        for (T vertex : graph.getAllVertices()) {
            adjacency.put(vertex, new LinkedList<>());
        }
        create(graph);
    }

    private void create(Graph<T> graph) {
        for (T vertex : graph.getAllVertices()) {
            List<T> neighbors = adjacency.get(vertex);
            neighbors.addAll(graph.getNeighbors(vertex));
        }
    }

    public List<T> getEdges(T vertex) {
        return adjacency.getOrDefault(vertex, new LinkedList<>());
    }

    @Override
    public boolean isEmpty() {
        return adjacency.isEmpty();
    }

    @Override
    public int size() {
        return adjacency.size();
    }

    @Override
    public void clear() {
        adjacency.clear();
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, List<T>> entry : adjacency.entrySet()) {
            sb.append(entry.getKey().toString()).append(": [");
            List<T> neighbors = entry.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                sb.append(neighbors.get(i).toString());
                if (i < neighbors.size() - 1) {
                    sb.append(", ");
                }
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
