package structs.heap;

import structs.Heap;
import structs.array.ArrayBase;
import java.util.Arrays;


abstract class BaseHeap<T extends Comparable<T>> extends ArrayBase<T> implements Heap<T> {

    protected BaseHeap(int capacity) {
        super(capacity, true);
    }

    protected BaseHeap() {
        super(DEFAULT_CAPACITY, true);
    }

    @Override
    public void heapify(T[] array) {
        this.array = Arrays.copyOf(array, array.length);
        this.size = array.length;
        this.capacity = array.length;

        // Beginnt bei den nicht-Blatt-Knoten
        for (int i = getParentIndex(size - 1); i >= 0; i--) {
            heapifyDown(i);
        }
    }

    @Override
    public T peek() {
        if (this.isEmpty()) return null;
        return array[0];
    }

    @Override
    public T poll() {
        if (this.isEmpty()) return null;
        T x = array[0];
        array[0] = array[size - 1];
        size--;
        heapifyDown(0);
        return x;
    }

    @Override
    public boolean insert(T x) {
        if (size == array.length) {
            resize();
            return false;
        }
        array[size] = x;
        size++;
        heapifyUp(size - 1);
        return true;
    }

    @Override
    public void add(T element) {
        this.insert(element);
    }

    @Override
    public boolean remove(T x) {
        int index = -1;
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                index = i;
                break;
            }
        }
        if (index == -1) return false;

        array[index] = array[size - 1];
        size--;

        if (index > 0 && array[index].compareTo(parent(index)) < 0) {
            heapifyUp(index);

        } else heapifyDown(index);

        return true;
    }

    @Override
    public int getHeight() {
        return (int) Math.floor(Math.log(size) / Math.log(2));
    }

    protected abstract void heapifyUp(int index);

    protected abstract void heapifyDown(int index);

    @Override
    public Node<T> getRoot() {
        throw new UnsupportedOperationException("Use peek method instead");
    }

    protected void swap(int one, int two) {
        T tmp = array[one];
        array[one] = array[two];
        array[two] = tmp;
    }

    protected void resize() {
        if (size == capacity) {
            array = Arrays.copyOf(array, capacity * 2);
            capacity *= 2;
        }
    }

    protected int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }
    protected int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }
    protected int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    protected boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }
    protected boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }
    protected boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    protected T leftChild(int index) {
        return array[getLeftChildIndex(index)];
    }
    protected T rightChild(int index) {
        return array[getRightChildIndex(index)];
    }
    protected T parent(int index) {
        return array[getParentIndex(index)];
    }
}
