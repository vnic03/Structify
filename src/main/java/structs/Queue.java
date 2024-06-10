package structs;


public interface Queue<Q> extends Structure<Q> {

    void enqueue(Q x);

    Q dequeue();

    Q peek();
}
