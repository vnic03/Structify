package structs.heap;

import structs.Queue;


abstract public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {


    private final BaseHeap<T> heap;

    private final boolean isMin;


    public PriorityQueue(boolean isMin) {
        this.isMin = isMin;
        this.heap = isMin ? new MinHeap<>() : new MaxHeap<>();
    }

    public PriorityQueue(int capacity, boolean isMin) {
        this.isMin = isMin;
        this.heap = isMin ? new MinHeap<>(capacity) : new MaxHeap<>(capacity);
    }

    @Override
    public void enqueue(T x) {
        heap.insert(x);
    }

    @Override
    public T dequeue() {
        return heap.poll();
    }

    @Override
    public T peek() {
        return heap.peek();
    }

    @Override
    public boolean isEmpty() {
        return heap.isEmpty();
    }

    @Override
    public int size() {
        return heap.size();
    }

    @Override
    public void clear() {
        heap.clear();
    }
}
