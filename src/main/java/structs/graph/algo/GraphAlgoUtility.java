package structs.graph.algo;

import algorithms.sort.MergeSort;
import algorithms.sort.Sorter;
import structs.Graph;
import structs.graph.Edge;
import structs.graph.WeightedGraph;
import structs.Queue;
import structs.Stack;
import structs.linked.LinkedQueue;
import structs.linked.LinkedStack;
import java.util.*;


public class GraphAlgoUtility<T extends Comparable<T>> {

    private final Graph<T> graph;

    private final Sorter<T> sorter = new MergeSort<>();


    public GraphAlgoUtility(Graph<T> graph) {
        this.graph = graph;
    }


    public List<T> bfs(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedQueue<>();

        queue.enqueue(start);
        visited.add(start);

        while (!queue.isEmpty()) {
            T current = queue.dequeue();
            result.add(current);

            List<T> neighbors = new ArrayList<>(graph.getNeighbors(current));
            sorter.sort(neighbors);

            for (T neighbor : neighbors) {
                if (!visited.contains(neighbor)) {
                    queue.enqueue(neighbor);
                    visited.add(neighbor);
                }
            }
        }
        return result;
    }


    public List<T> dfs(T start) {
        List<T> result = new ArrayList<>();
        Set<T> visited = new HashSet<>();
        Stack<T> stack = new LinkedStack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            T current = stack.pop();

            if (!visited.contains(current)) {
                visited.add(current);
                result.add(current);

                List<T> neighbors = new ArrayList<>(graph.getNeighbors(current));
                sorter.reverseSort(neighbors);

                for (T neighbor : neighbors) {
                    if (!visited.contains(neighbor)) {
                        stack.push(neighbor);
                    }
                }
            }
        }
        return result;
    }

    public Map<T, Double> bellmanFord(T start) {
        if (graph instanceof WeightedGraph) {

            Map<T, Double> dist = new HashMap<>();
            Map<T, T> pred = new HashMap<>();

            for (T vertex : graph.getAllVertices()) {
                dist.put(vertex, Double.POSITIVE_INFINITY);
            }
            dist.put(start, 0.0);

            int V = graph.getAllVertices().size();

            for (int i = 0; i < V; i++) {
                for (Edge<T> edge : graph.getEdges()) {
                    relax(dist, pred, edge);
                }
            }

            for (Edge<T> edge : graph.getEdges()) {
                if (dist.get(edge.destination()) > dist.get(edge.source()) + edge.weight().get()) {
                    throw new IllegalArgumentException("Graph contains a negative-weight cycle");
                }
            }
            return dist;

        } else throw new IllegalArgumentException("Graph must be weighted");
    }


    private void relax(Map<T, Double> dist, Map<T, T> pred, Edge<T> edge) {
        double newDist = dist.get(edge.source()) + edge.weight().get();
        if (newDist < dist.get(edge.destination()) ||
                (newDist == dist.get(edge.destination()) &&
                        edge.source().compareTo(edge.destination()) < 0))
        {
            dist.put(edge.destination(), newDist);
            pred.put(edge.destination(), edge.source());
        }
    }
}
