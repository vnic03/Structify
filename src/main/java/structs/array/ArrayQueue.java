package structs.array;

import structs.Queue;


public class ArrayQueue<T> extends ArrayBase<T> implements Queue<T> {

    private int front;

    private int rear;

    public ArrayQueue(int capacity) {
        super(capacity);
        this.front = 0;
        this.rear = -1;
    }

    public ArrayQueue() {
       this(DEFAULT_CAPACITY);
    }

    @Override
    public void enqueue(T x) {
        if (size == capacity) return;

        rear = (rear + 1) % capacity;
        array[rear] = x;
        size += 1;
    }

    @Override
    public void add(T element) {
        this.enqueue(element);
    }

    @Override
    public T dequeue() {
        if (isEmpty()) return null;

        T x = array[front];
        array[front] = null;
        front = (front + 1) % capacity;
        size -= 1;

        return x;
    }

    @Override
    public T peek() {
        return isEmpty() ? null : array[front];
    }

    @Override
    public void clear() {
        size = 0;
        front = 0;
        rear = -1;

        for (int i = 0; i < capacity; i++) {
            array[i] = null;
        }
    }
}