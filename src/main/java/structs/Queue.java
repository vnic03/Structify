package structs;

public interface Queue<T> extends Structure {

    void enqueue(T x);

    T dequeue();

    T peek();
}
