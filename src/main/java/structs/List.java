package structs;


public interface List<T> extends Structure<T> {

    void add(T x);

    void add(int index, T x);

    boolean remove(T x);

    T remove(int index);

    T get(int index);

    T set(int index, T x);

    boolean contains(T x);

    int indexOf(T x);

    int lastIndexOf(T x);
}
