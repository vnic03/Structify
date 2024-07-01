package structs.heap;


public class MaxHeap<T extends Comparable<T>> extends BaseHeap<T> {


    public MaxHeap(int capacity) {
        super(capacity);
    }

    public MaxHeap() {
        super();
    }

    @Override
    protected void heapifyUp(int index) {
        while (hasParent(index) && parent(index).compareTo(array[index]) < 0) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    @Override
    protected void heapifyDown(int index) {
        while (hasLeftChild(index)) {
            int largerChildIndex = getLeftChildIndex(index);
            if (hasRightChild(index) && rightChild(index).compareTo(leftChild(index)) > 0) {
                largerChildIndex = getRightChildIndex(index);
            }

            if (array[index].compareTo(array[largerChildIndex]) >= 0) break;
            else {
                swap(index, largerChildIndex);
            }
            index = largerChildIndex;
        }
    }

    @Override
    public boolean contains(T x) {
        return contains(0, x);
    }

    private boolean contains(int index, T x) {
        if (index >= size) return false;
        if (array[index].equals(x)) return true;

        if (array[index].compareTo(x) < 0) return false;

        return contains(getLeftChildIndex(index), x) ||
                contains(getRightChildIndex(index), x);
    }
}
