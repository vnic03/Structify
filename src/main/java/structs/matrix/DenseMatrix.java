package structs.matrix;


public class DenseMatrix<T> extends BaseMatrix<T> {


    private final Object[][] data;


    public DenseMatrix(int rows, int cols) {
        super(rows, cols);
        data = new Object[rows][cols];
    }

    public DenseMatrix(T[][] data) {
        super(data.length, data[0].length);
        this.data = new Object[rows][cols];
        for (int i = 0; i < rows; i++) {
            System.arraycopy(data[i], 0, this.data[i], 0, cols);
        }
    }

    @Override
    public void set(int row, int col, T value) {
        this.data[row][col] = value;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(int row, int col) {
        return (T) data[row][col];
    }

    @Override
    @SuppressWarnings("unchecked")
    public Matrix<T> transpose() {
        DenseMatrix<T> transposed = new DenseMatrix<>(cols, rows);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                transposed.set(j, i, (T) data[i][j]);
            }
        }
        return transposed;
    }

    @Override
    protected Number getAsNumber(int row, int col) {
        Object value = data[row][col];
        if (value instanceof Number) return (Number) value;
        else throw new UnsupportedOperationException("Element: " + value + " is not a number");
    }

    @Override
    protected Matrix<T> createInstance(int rows, int cols) {
        return new DenseMatrix<>(rows, cols);
    }

    @Override
    public boolean isEmpty() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] != null) return false;
            }
        }
        return true;
    }

    @Override
    public int size() {
        int counter = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (data[i][j] != null) counter++;
            }
        }
        return counter;
    }

    @Override
    public void clear() {
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                data[i][j] = null;
            }
        }
    }
}
