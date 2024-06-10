package structs.linked;

import structs.Queue;


public class LinkedQueue<T> extends LinkedBase<T> implements Queue<T> {

    private Node<T> rear;

    public LinkedQueue() {
        super();
        this.rear = null;
    }

    @Override
    public void enqueue(T x) {
        Node<T> node = new Node<>(x);
        if (rear == null) {
            head = rear = node;
        } else {
            rear.next = node;
            rear = node;
        }
        incrementSize();
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;
        T data = head.data;
        head = head.next;
        if (head == null) rear = null;
        decrementSize();
        return data;
    }

    @Override
    public T peek() {
        return head != null ? head.data : null;
    }

    @Override
    public void clear() {
        head = null;
        rear = null;
        size = 0;
    }
}
