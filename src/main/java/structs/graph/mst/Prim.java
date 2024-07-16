package structs.graph.mst;

import structs.Queue;
import structs.graph.WeightedGraph;
import structs.graph.WeightedUndirectedGraph;
import structs.linked.LinkedQueue;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


class Prim<T> extends MSTBuilder<T> {

    private final Map<T, Double> keys;

    private final Map<T, T> predecessors;

    private final Set<T> remainingNodes;


    protected Prim(WeightedGraph<T> graph, T start) {
        super(graph);
        this.keys = new HashMap<>();
        this.predecessors = new HashMap<>();
        this.remainingNodes = new HashSet<>();
        super.mst = prim(start);
    }

    private WeightedGraph<T> prim(T start) {
        Set<T> V = graph.getVertices();

        if (start == null) start = V.iterator().next();

        for (T v : V) {
            keys.put(v, Double.POSITIVE_INFINITY);
            predecessors.put(v, null);
        }
        keys.put(start, Double.NEGATIVE_INFINITY);
        remainingNodes.addAll(V);

        while (!remainingNodes.isEmpty()) {
            T u = start instanceof String ? extractMinString() : extractMin();
            for (T v : graph.getNeighbors(u)) {
                if (remainingNodes.contains(v)) {
                    double weight = graph.getEdge(u, v).weight();
                    if (weight < keys.get(v)) {
                        keys.put(v, weight);
                        predecessors.put(v, u);
                    }
                }
            }
        }

        WeightedGraph<T> mst = new WeightedUndirectedGraph<>();

        for (T v : graph.getVertices()) {
            mst.add(v);
        }

        for (T v : predecessors.keySet()) {
            T u = predecessors.get(v);
            if (u != null) {
                double weight = keys.get(v);
                mst.addEdge(u, v, weight);
            }
        }
        return mst;
    }

    private T extractMin() {
        T min = null;
        for (T node : remainingNodes) {
            if (min == null || keys.get(node) <= keys.get(min)) {
                min = node;
            }
        }
        remainingNodes.remove(min);
        return min;
    }

    private T extractMinString() {
        T min = null;
        for (T node : remainingNodes) {
            if (min == null || keys.get(node) < keys.get(min) ||
                    (keys.get(node).equals(keys.get(min)) && node.toString().compareTo(min.toString()) < 0))
            {
                min = node;
            }
        }
        remainingNodes.remove(min);
        return min;
    }


    @Override
    protected boolean isValid() {
        Set<T> visited = new HashSet<>();
        Queue<T> queue = new LinkedQueue<>();

        T start = mst.getVertices().iterator().next();
        queue.enqueue(start);

        while (!queue.isEmpty()) {
            T current = queue.dequeue();
            if (visited.contains(current)) {
                System.out.println("Cycle detected at vertex: " + current);
                return false;
            }
            visited.add(current);

            for (T neighbor : mst.getNeighbors(current)) {
                if (!visited.contains(neighbor)) {
                    queue.enqueue(neighbor);
                }
            }
        }

        if (visited.size() != graph.getVertices().size()) {
            System.out.println("Not all vertices are connected");
            return false;
        }

        return true;
    }
}
