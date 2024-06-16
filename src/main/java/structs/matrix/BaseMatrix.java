package structs.matrix;


abstract class BaseMatrix<T> implements Matrix<T> {

    protected final int rows;

    protected final int cols;

    protected BaseMatrix(int rows, int cols) {
        this.rows = rows;
        this.cols = cols;
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }

    protected abstract Number getAsNumber(int row, int col);

    protected abstract Matrix<T> createInstance(int rows, int cols);

    @Override
    public Matrix<T> add(Matrix<T> other) {
        if (this.rows != other.getRows() || this.cols != other.getCols()) {
            throw new IllegalArgumentException("Matrices must have the same dimensions");
        }

        Matrix<T> result = createInstance(rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                double sum = this.getAsNumber(i, j).doubleValue() + ((Number) other.get(i, j)).doubleValue();
                result.set(i, j, convert(sum, other.get(i, j).getClass()));
            }
        }

        return result;
    }

    @Override
    public Matrix<T> multiply(Matrix<T> other) {
        if (this.cols != other.getRows()) {
            throw new IllegalArgumentException("The first matrix's columns must equal the second matrix's rows");
        }

        Matrix<T> result = createInstance(this.rows, other.getCols());

        for (int i = 0; i < this.rows; i++) {
            for (int j = 0; j < other.getCols(); j++) {
                double sum = 0;
                for (int k = 0; k < this.cols; k++) {
                    sum += this.getAsNumber(i, k).doubleValue() * ((Number) other.get(k, j)).doubleValue();
                }
                result.set(i, j, convert(sum, other.get(0, 0).getClass()));
            }
        }
        return result;
    }

    @SuppressWarnings("unchecked")
    private T convert(double value, Class<?> clazz) {
        return switch (clazz.getSimpleName()) {
            case "Integer" -> (T) Integer.valueOf((int) value);
            case "Long" -> (T) Long.valueOf((long) value);
            case "Float" -> (T) Float.valueOf((float) value);
            case "Double" -> (T) Double.valueOf(value);
            default -> throw new UnsupportedOperationException("Type not supported");
        };
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
}
