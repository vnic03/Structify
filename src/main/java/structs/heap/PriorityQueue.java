package structs.heap;

import structs.Queue;
import java.util.Collection;


public class PriorityQueue<T extends Comparable<T>> implements Queue<T> {


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

    public PriorityQueue(Collection<? extends T> collection, boolean isMin) {
        this.isMin = isMin;
        this.heap = isMin ? new MinHeap<>() : new MaxHeap<>();
        for (T item : collection) {
            heap.insert(item);
        }
    }

    public PriorityQueue(Collection<? extends T> collection, int capacity, boolean isMin) {
        this.isMin = isMin;
        this.heap = isMin ? new MinHeap<>(capacity) : new MaxHeap<>(capacity);
        for (T item : collection) {
            heap.insert(item);
        }
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

    public boolean changePriority(T old, T x) {
        boolean removed = heap.remove(old);
        if (removed) {
            heap.insert(x);
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return heap.toString();
    }
}
