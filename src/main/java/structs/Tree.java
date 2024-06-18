package structs;


public interface Tree<T> extends Structure<T> {

    boolean insert(T x);

    boolean remove(T x);

    boolean contains(T x);

    Node<T> getRoot();

    int getHeight();


    interface Node<N> {

        default Node<N> getLeft() {
            throw new UnsupportedOperationException();
        }

        default Node<N> getRight() {
            throw new UnsupportedOperationException();
        }

        default N getData() {
            throw new UnsupportedOperationException();
        }
    }
}
