package structs.matrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SparseMatrix extends BaseMatrix<Number> {

    private final List<Double> values;

    private final List<Integer> columnIndices;

    private final List<Integer> rowPointers;


    public SparseMatrix(int rows, int cols) {
        super(rows, cols);
        this.values = new ArrayList<>();
        this.columnIndices = new ArrayList<>();
        this.rowPointers = new ArrayList<>(Collections.nCopies(rows + 1, 0));
    }


    @Override
    public void set(int row, int col, Number value) {
        if (value.doubleValue() != 0) {
            values.add(value.doubleValue());
            columnIndices.add(col);
            for (int i = row + 1; i <= rows; i++) {
                rowPointers.set(i, rowPointers.get(i) + 1);
            }
        }
    }

    @Override
    public Number get(int row, int col) {
        for (int i = rowPointers.get(row); i < rowPointers.get(row + 1); i++) {
            if (columnIndices.get(i) == col) {
                return values.get(i);
            }
        }
        return 0;
    }

    @Override
    public Matrix<Number> transpose() {
        List<Double> transposedValues = new ArrayList<>();
        List<Integer> transposedColIndices = new ArrayList<>();
        List<Integer> transposedRowPointers = new ArrayList<>(Collections.nCopies(cols + 1, 0));

        for (int i = 0; i < rows; i++) {
            for (int j = rowPointers.get(i); j < rowPointers.get(i + 1); j++) {
                int col = columnIndices.get(j);
                double value = values.get(j);
                transposedValues.add(value);
                transposedColIndices.add(i);
                transposedRowPointers.set(col + 1, transposedRowPointers.get(col + 1) + 1);
            }
        }

        for (int i = 0; i < cols; i++) {
            transposedRowPointers.set(i + 1, transposedRowPointers.get(i + 1) + transposedRowPointers.get(i));
        }

        SparseMatrix transposed = new SparseMatrix(cols, rows);

        transposed.values.addAll(transposedValues);
        transposed.columnIndices.addAll(transposedColIndices);
        transposed.rowPointers.addAll(transposedRowPointers);

        return transposed;
    }

    @Override
    protected Matrix<Number> createInstance(int rows, int cols) {
        return new SparseMatrix(rows, cols);
    }

    @Override
    public boolean isEmpty() {
        return values.isEmpty();
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public void clear() {
        values.clear();
        columnIndices.clear();
        Collections.fill(rowPointers, 0);
    }

    @Override
    protected Number getAsNumber(int row, int col) {
        return this.get(row, col);
    }
}
