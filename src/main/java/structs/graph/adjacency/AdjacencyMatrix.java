package structs.graph.adjacency;

import structs.Graph;
import structs.matrix.Matrix;

import java.util.BitSet;
import java.util.HashMap;
import java.util.Map;


public class AdjacencyMatrix<T> implements Matrix<Integer> {

    private final int rows;

    private final int cols;

    private final BitSet matrix;

    private final Map<T, Integer> vertexIndexMap;


    public AdjacencyMatrix(Graph<T> graph) {
        this.rows = graph.getAllVertices().size();
        this.cols = graph.getAllVertices().size();
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
    public int getRows() {
        return rows;
    }

    @Override
    public int getCols() {
        return cols;
    }

    @Override
    public void set(int row, int col, Integer value) {
        if (row >= 0 && row < rows && col >= 0 && col < cols) {
            int index = row * cols + col;

            if (value == 1) matrix.set(index);
            else matrix.clear(index);

        } else {
            throw new IndexOutOfBoundsException();
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
    public boolean isEmpty() {
        return matrix.isEmpty();
    }

    @Override
    public void clear() {
        matrix.clear();
    }

    @Override
    public int size() {
        return matrix.cardinality();
    }

    @Override
    public String toString() {
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
        throw new UnsupportedOperationException();
    }

    @Override
    public Matrix<Integer> multiply(Matrix<Integer> other) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Matrix<Integer> transpose() {
        throw new UnsupportedOperationException();
    }
}
