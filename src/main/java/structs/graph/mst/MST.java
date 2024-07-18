package structs.graph.mst;

import structs.Graph;
import structs.Structure;
import structs.graph.Edge;
import java.util.*;


public class MST<T> implements Structure<T> {

    private final Graph<T> graph;

    private final MSTBuilder<T> builder;

    private final String algorithm;


    public MST(Graph<T> graph) {
        this(graph, null);
    }

    public MST(Graph<T> graph, T start) {
        this(graph, checkDensity(graph) == 1, start);
    }

    public MST(Graph<T> graph, boolean prim, T start) {
        this.graph = graph;
        this.builder = prim ? new Prim<>(graph, start) : new Kruskal<>(graph);
        this.algorithm = prim ? "Prim" : "Kruskal";
    }

    public MST(Graph<T> graph, boolean prim) {
        this.graph = graph;
        this.builder = prim ? new Prim<>(graph, null) : new Kruskal<>(graph);
        this.algorithm = prim ? "Prim" : "Kruskal";
    }


    private static <T> int checkDensity(Graph<T> graph) {
        // 1 == DENSE and 0 == SPARSE
        int V = graph.size();
        int E = graph.getEdges().size();
        return E > V * Math.log(V) ? 1 : 0;
    }

    @Override
    public void add(T element) {
        builder.mst.add(element);
    }

    public Set<Edge<T>> getEdges() {
        return builder.mst.getEdges();
    }

    public boolean isValid() {
        return builder.isValid();
    }

    public String getAlgorithm() {
        return algorithm;
    }

    public double getTotalWeight() {
        return builder.getTotalWeight();
    }

    @Override
    public boolean isEmpty() {
        return builder.mst.isEmpty();
    }

    @Override
    public int size() {
        return builder.mst.size();
    }

    @Override
    public void clear() {
        builder.mst.clear();
    }

    @Override
    public String toString() {
        return builder.toString();
    }
}
