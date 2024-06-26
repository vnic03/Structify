package structs.graph;

import structs.Structure;
import java.util.*;


public class MST<T> implements Structure<T> {

    private final WeightedGraph<T> graph;

    private final WeightedGraph<T> mst;


    public MST(WeightedGraph<T> graph) {
        this.graph = graph;
        this.mst = kruskal();
        // create();
    }

    private WeightedGraph<T> kruskal() {
        WeightedUndirectedGraph<T> mst = new WeightedUndirectedGraph<>();
        UnionFind unionFind = new UnionFind();

        for (T vertex : graph.getAllVertices()) {
            mst.add(vertex);
            unionFind.makeSet(vertex);
        }

        List<Edge<T>> edges = new ArrayList<>(graph.getEdges());
        edges.sort(Comparator.comparingDouble(e -> e.weight().get()));

        for (Edge<T> edge : edges) {
            T src = edge.source();
            T dest = edge.destination();
            if (unionFind.union(src, dest)) {
                mst.addEdge(src, dest, edge.weight().get());
            }
        }

        return mst;
    }

    public List<Edge<T>> getEdges() {
        return List.copyOf(mst.getEdges());
    }

    public boolean isValid() {
        UnionFind unionFind = new UnionFind();
        for (T vertex : graph.getAllVertices()) {
            unionFind.makeSet(vertex);
        }

        for (Edge<T> edge : mst.getEdges()) {
            T src = edge.source();
            T dest = edge.destination();
            if (!unionFind.union(src, dest)) {
                System.out.println("Cycle detected with edge: " + src + " -- " + dest);
                return false;
            }
        }

        T root = null;
        for (T vertex : graph.getAllVertices()) {
            if (root == null) {
                root = unionFind.find(vertex);
            } else if (!root.equals(unionFind.find(vertex))) {
                System.out.println("Vertex " + vertex + " is not connected to the root " + root);
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isEmpty() {
        return mst.isEmpty();
    }

    @Override
    public int size() {
        return mst.size();
    }

    @Override
    public void clear() {
        mst.clear();
    }

    @Override
    public String toString() {
        return mst.toString();
    }

    protected class UnionFind {

        private final Map<T, T> parent = new HashMap<>();

        private final Map<T, Integer> rank = new HashMap<>();

        void makeSet(T item) {
            parent.put(item, item);
            rank.put(item, 0);
        }

        T find(T item) {
            if (parent.get(item) != item) {
                parent.put(item, find(parent.get(item)));
            }
            return parent.get(item);
        }

        boolean union(T set1, T set2) {
            T root1 = find(set1);
            T root2 = find(set2);

            if (root1.equals(root2)) return false;

            if (rank.get(root1) > rank.get(root2)) {
                parent.put(root2, root1);

            } else if (rank.get(root1) < rank.get(root2)) {
                parent.put(root1, root2);

            } else {
                parent.put(root2, root1);
                rank.put(root1, rank.get(root1) + 1);
            }
            return true;
        }
    }
}
