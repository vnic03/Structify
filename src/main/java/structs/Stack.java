package structs;


public interface Stack<S> extends Structure<S> {

    void push(S x);

    S pop();

    S peek();
}
