package structs;

public interface Tree<T> extends Structure {

    boolean insert(T x);

    boolean remove(T x);

    boolean contains(T x);

    Object getRoot();

    int getHeight();
}
