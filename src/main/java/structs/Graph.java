package structs;

import structs.graph.Edge;
import java.util.Set;


public interface Graph<T> extends Structure<T> {

    /**
     * Adds a vertex to the graph.
     *
     * @param vertex the vertex to be added
     */
    void add(T vertex);

    /**
     * Removes a vertex from the graph.
     *
     * @param vertex the vertex to be removed
     * @return true if the vertex was removed, false otherwise
     */
    boolean remove(T vertex);

    /**
     * Adds an unweighted edge between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     */
    void addEdge(T src, T dest);

    /**
     * Adds a weighted edge between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     * @param weight the weight of the edge
     */
    void addEdge(T src, T dest, Double weight);

    /**
     * Retrieves an edge between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     * @return the edge between the source and destination vertices, or null if no such edge exists
     */
    Edge<T> getEdge(T src, T dest);

    /**
     * Removes an edge between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     * @return true if the edge was removed, false otherwise
     */
    boolean removeEdge(T src, T dest);

    /**
     * Gets the neighbors of a given vertex.
     *
     * @param vertex the vertex whose neighbors are to be retrieved
     * @return a set of neighbors of the given vertex
     */
    Set<T> getNeighbors(T vertex);

    /**
     * Checks if a vertex exists in the graph.
     *
     * @param vertex the vertex to be checked
     * @return true if the vertex exists, false otherwise
     */
    boolean contains(T vertex);

    /**
     * Checks if an edge exists between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     * @return true if an edge exists between the source and destination vertices, false otherwise
     */
    boolean containsEdge(T src, T dest);

    /**
     * Retrieves all vertices in the graph.
     *
     * @return a set of all vertices in the graph
     */
    Set<T> getVertices();

    /**
     * Retrieves all edges in the graph.
     *
     * @return a set of all edges in the graph
     */
    Set<Edge<T>> getEdges();

    /**
     * Gets the weight of the edge between two vertices.
     *
     * @param src the source vertex
     * @param dest the destination vertex
     * @return the weight of the edge between the source and destination vertices, or null if no such edge exists or if the edge is unweighted
     */
    Double getWeight(T src, T dest);

    /**
     * Checks if the graph is weighted.
     *
     * @return true if the graph is weighted, false otherwise
     */
    boolean isWeighted();

    /**
     * Calculates the total weight of all edges in the graph.
     *
     * @return the total weight of all edges in the graph
     */
    double weight();
}
