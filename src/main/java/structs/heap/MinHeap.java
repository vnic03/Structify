package structs.heap;


public class MinHeap<T extends Comparable<T>> extends BaseHeap<T> {


    public MinHeap(int capacity) {
        super(capacity);
    }

    public MinHeap() {
        super();
    }

    @Override
    protected void heapifyUp(int index) {
        while (hasParent(index) && parent(index).compareTo(array[index]) > 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    @Override
    protected void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int smallerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) < 0) {
                smallerChildIndex = getRightChildIndex(index);
            }

            if (array[index].compareTo(array[smallerChildIndex]) <= 0) break;
            else {
                swap(index, smallerChildIndex);
            }
            index = smallerChildIndex;
        }
    }

    @Override
    public boolean contains(T x) {
        return contains(0, x);
    }

    private boolean contains(int index, T x) {
        if (index >= size) return false;
        if (array[index].equals(x)) return true;

        if (array[index].compareTo(x) > 0) return false;

        return contains(getLeftChildIndex(index), x) ||
                contains(getRightChildIndex(index), x);
    }
}
