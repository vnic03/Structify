package structs;


public interface Queue<T> extends Structure<T> {

    void enqueue(T x);

    T dequeue();

    T peek();
}
