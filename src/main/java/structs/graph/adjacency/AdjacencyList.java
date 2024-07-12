package structs.graph.adjacency;

import structs.Graph;
import structs.Structure;
import structs.graph.Edge;
import structs.graph.UndirectedGraph;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;


public class AdjacencyList<T> implements Structure<T> {

    // Should probably be an Array of LinkedLists
    private final Map<T, LinkedList<T>> adjacency;

    private final Graph<T> graph;


    public AdjacencyList(Graph<T> graph) {
        this.graph = graph;
        this.adjacency = new HashMap<>();
        init();
    }

    private void init() {
        for (T vertex : graph.getVertices()) {
            adjacency.put(vertex, new LinkedList<>());
        }
        create();
    }

    private void create() {
        for (T vertex : graph.getVertices()) {
            List<T> neighbors = adjacency.computeIfAbsent(vertex, k -> new LinkedList<>());
            neighbors.addAll(graph.getNeighbors(vertex));
        }
    }

    @Override
    public void add(T element) {
        adjacency.putIfAbsent(element, new LinkedList<>());
    }

    public List<T> getEdges(T vertex) {
        return adjacency.getOrDefault(vertex, new LinkedList<>());
    }

    public boolean hasEdge(Edge<T> edge) {
        T src = edge.source();
        T dest = edge.destination();
        if (adjacency.containsKey(src) && adjacency.get(src).contains(dest)) {
            return true;
        }
        if (graph instanceof UndirectedGraph) {
            return adjacency.containsKey(dest) && adjacency.get(dest).contains(src);
        }
        return false;
    }

    public boolean hasEdge(T src, T des) {
        return hasEdge(new Edge<>(graph, src, des));
    }

    protected void addEdge(T vertex, T neighbor) {
        adjacency.computeIfAbsent(vertex, k -> new LinkedList<>()).add(neighbor);
    }

    public AdjacencyMatrix<T> listToMatrix() {
        AdjacencyMatrix<T> matrix = new AdjacencyMatrix<>(graph);

        for (Map.Entry<T, LinkedList<T>> entry : adjacency.entrySet()) {
            T vertex = entry.getKey();
            int i = matrix.getVertexIndex(vertex);

            for (T neighbor : entry.getValue()) {
                int j = matrix.getVertexIndex(neighbor);
                matrix.set(i, j, 1);
            }
        }
        return matrix;
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
        if (adjacency.isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<T, LinkedList<T>> entry : adjacency.entrySet()) {
            sb.append(entry.getKey().toString()).append("*->[");
            List<T> neighbors = entry.getValue();
            for (int i = 0; i < neighbors.size(); i++) {
                sb.append(neighbors.get(i).toString());
                if (i < neighbors.size() - 1) sb.append(", ");
            }
            sb.append("]\n");
        }
        return sb.toString();
    }
}
