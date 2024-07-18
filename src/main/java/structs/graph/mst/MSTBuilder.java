package structs.graph.mst;

import structs.Graph;
import structs.graph.Edge;


abstract class MSTBuilder<T> {

    protected final Graph<T> graph;

    protected Graph<T> mst;

    protected MSTBuilder(Graph<T> graph) {
        this.graph = graph;
        this.mst = null;
    }

    protected abstract boolean isValid();

    protected double getTotalWeight() {
        return mst.getEdges().stream().mapToDouble(Edge::weight).sum();
    }

    protected void checkGraph() {
        for (Edge<T> edge : graph.getEdges()) {
            if (edge.weight() == null) {
                throw new IllegalArgumentException(
                        String.format(
                                "Edge with src %s and dest %s has no weight",
                                edge.source(), edge.destination()
                        )
                );
            }
        }
    }

    @Override
    public String toString() {
        return mst.toString();
    }
}
