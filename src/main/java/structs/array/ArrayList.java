package structs.array;

import structs.List;


public class ArrayList<T> extends ArrayBase<T> implements List<T> {

    public ArrayList(int capacity) {
        super(capacity);
    }

    public ArrayList() {
        super(DEFAULT_CAPACITY);
    }

    @Override
    public void add(T element) {
        if (size == capacity) resize(2 * capacity);
        array[size++] = element;
    }

    @Override
    public void add(int index, T element) {
        e(index);

        if (size == capacity) resize(2 * capacity);

        System.arraycopy(array, index, array, index + 1, size - index);
        array[index] = element;
        size += 1;
    }

    @Override
    public boolean remove(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                remove(i);
                return true;
            }
        }
        return false;
    }

    @Override
    public T remove(int index) {
        e(index);

        T r = array[index];
        int x = size - index - 1;
        if (x > 0) System.arraycopy(array, index + 1, array, index, x);

        array[--size] = null;
        return r;
    }

    @Override
    public T get(int index) {
        e(index);
        return array[index];
    }

    @Override
    public T set(int index, T element) {
        e(index);
        T old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public boolean contains(T element) {
        return indexOf(element) >= 0;
    }

    @Override
    public int indexOf(T element) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T element) {
        for (int i = size - 1; i >= 0; i--) {
            if (array[i].equals(element)) {
                return i;
            }
        }
        return -1;
    }

    private void e(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }
}
