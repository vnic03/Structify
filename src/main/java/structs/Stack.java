package structs;


public interface Stack<T> extends Structure<T> {

    void push(T x);

    T pop();

    T peek();
}
