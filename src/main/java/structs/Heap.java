package structs;

public interface Heap<T> extends Tree<T> {

    void heapify(T[] array);

    T peek();

    T poll();
}
