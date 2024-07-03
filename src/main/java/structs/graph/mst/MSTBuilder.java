package structs.graph.mst;

import structs.graph.Edge;
import structs.graph.WeightedGraph;


abstract class MSTBuilder<T> {

    protected final WeightedGraph<T> graph;

    protected WeightedGraph<T> mst;

    protected MSTBuilder(WeightedGraph<T> graph) {
        this.graph = graph;
        this.mst = null;
    }

    protected abstract boolean isValid();

    protected double getTotalWeight() {
        return mst.getEdges().stream().mapToDouble(e -> e.weight().get()).sum();
    }

    @Override
    public String toString() {
        return mst.toString();
    }
}
