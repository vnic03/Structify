package structs.matrix;

import structs.Structure;


public interface Matrix<T> extends Structure<T> {

    int getRows();

    int getCols();

    void set(int row, int col, T value);

    T get(int row, int col);

    Matrix<T> add(Matrix<T> other);

    Matrix<T> multiply(Matrix<T> other);

    Matrix<T> transpose();
}
