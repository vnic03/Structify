package structs.array;

import structs.Tree;


public class ArrayTree<T> extends ArrayBase<T> implements Tree<T> {

    public ArrayTree() {
        super(DEFAULT_CAPACITY);
    }

    public ArrayTree(int capacity) {
        super(capacity);
    }

    @Override
    public boolean insert(T x) {
        if (size == capacity) {
            resize(2 * capacity);
        }
        array[size++] = x;
        return true;
    }

    @Override
    public boolean remove(T x) {
        int index = indexOf(x);
        if (index == -1) return false;

        array[index] = array[--size];
        array[size] = null;
        return true;
    }

    @Override
    public boolean contains(T x) {
        return indexOf(x) != -1;
    }

    @Override
    public T getRoot() {
        return isEmpty() ? null : array[0];
    }

    @Override
    public int getHeight() {
        return isEmpty() ? 0 : (int) Math.ceil(Math.log(size + 1) / Math.log(2));
    }

    private int indexOf(T x) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(x)) {
                return i;
            }
        }
        return -1;
    }
}
