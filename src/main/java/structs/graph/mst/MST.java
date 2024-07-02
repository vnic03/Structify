package structs.graph.mst;

import structs.Structure;
import structs.graph.Edge;
import structs.graph.WeightedGraph;
import java.util.*;


public class MST<T> implements Structure<T> {

    private final WeightedGraph<T> graph;

    private final MSTBuilder<T> builder;

    private final String algorithm;


    public MST(WeightedGraph<T> graph) {
        this.graph = graph;
        if (checkDensity() == 1) {
            builder = new Prim<>(graph);
            algorithm = "Prim";
        } else {
            builder = new Kruskal<>(graph);
            algorithm = "Kruskal";
        }
    }

    private int checkDensity() {
        // 1 == DENSE and 0 == SPARSE
        int V = graph.size();
        int E = graph.getEdges().size();
        return E > V * Math.log(V) ? 1 : 0;
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
