package structs.graph.adjacency;

import structs.Graph;
import structs.graph.Edge;
import structs.matrix.Matrix;
import java.util.*;


public class AdjacencyMatrix<T> implements Matrix<Integer> {

    private final int rows;

    private final int cols;

    private final BitSet matrix;

    private final Map<T, Integer> vertexIndexMap;


    public AdjacencyMatrix(Graph<T> graph) {
        this.rows = graph.getAllVertices().size();
        this.cols = this.rows;
        this.matrix = new BitSet(rows * cols);
        this.vertexIndexMap = new HashMap<>();
        initVIndexes(graph);
        createMatrix(graph);
    }

    private void initVIndexes(Graph<T> graph) {
        int index = 0;
        for (T vertex : graph.getAllVertices()) {
            vertexIndexMap.put(vertex, index++);
        }
    }

    private void createMatrix(Graph<T> graph) {
        for (T vertex : graph.getAllVertices()) {
            int i = vertexIndexMap.get(vertex);
            for (T neighbor : graph.getNeighbors(vertex)) {
                int j = vertexIndexMap.get(neighbor);
                this.set(i, j, 1);
            }
        }
    }

    @Override
    public Integer get(int row, int col) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            int index = row * cols + col;
            return matrix.get(index) ? 1 : 0;

        } else throw new IndexOutOfBoundsException();
    }

    @Override
    public void set(int row, int col, Integer value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            int index = row * cols + col;

            if (value == 1) matrix.set(index);
            else matrix.clear(index);

        } else throw new IndexOutOfBoundsException();
    }

    // TODO: Dopplungen vermeiden
    public AdjacencyList<T> matrixToList(Graph<T> graph) {
        AdjacencyList<T> list = new AdjacencyList<>(graph);
        Set<Edge<T>> addedEdges = new HashSet<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (this.get(i, j) == 1) {
                    T vertexI = getVertex(i);
                    T vertexJ = getVertex(j);
                    Edge<T> edge = new Edge<>(vertexI, vertexJ);
                    if (!addedEdges.contains(edge)) {
                        list.addEdge(vertexI, vertexJ);
                        addedEdges.add(edge);
                    }
                }
            }
        }
        return list;
    }

    private T getVertex(int index) {
        for (Map.Entry<T, Integer> entry : vertexIndexMap.entrySet()) {
            if (entry.getValue().equals(index)) {
                return entry.getKey();
            }
        }
        return null;
    }

    @Override
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public boolean isEmpty() {
        return matrix.isEmpty();
    }

    @Override
    public void clear() {
        matrix.clear();
        vertexIndexMap.clear();
    }

    @Override
    public int size() {
        return matrix.cardinality();
    }

    public int getVertexIndex(T vertex) {
        return vertexIndexMap.getOrDefault(vertex, -1);
    }

    @Override
    public String toString() {
        if (isEmpty()) return "[]";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < rows; i++) {
            sb.append("[");
            for (int j = 0; j < cols; j++) {
                sb.append(this.get(i, j));
                if (j < cols - 1) sb.append(", ");
            }
            sb.append("]");
            if (i < rows - 1) sb.append("\n");
        }
        return sb.toString();
    }

    @Override
    public Matrix<Integer> add(Matrix<Integer> other) {
        throw new UnsupportedOperationException("Not needed for this type of Matrix");
    }

    @Override
    public Matrix<Integer> multiply(Matrix<Integer> other) {
        throw new UnsupportedOperationException("Not needed for this type of Matrix");
    }

    @Override
    public Matrix<Integer> transpose() {
        throw new UnsupportedOperationException("Not needed for this type of Matrix");
    }
}
