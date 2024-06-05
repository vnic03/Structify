package structs;


public interface Stack<T> extends Structure {

    void push(T x);

    T pop();

    T peek();

    int search(T x);
}
