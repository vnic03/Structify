package structs.graph;

import structs.Graph;


public interface WeightedGraph<T> extends Graph<T> {

    void addEdge(T src, T dest, double weight);

    double getWeight(T src, T dest);
}
