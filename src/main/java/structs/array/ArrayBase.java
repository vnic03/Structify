package structs.array;

import structs.Structure;
import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;


public abstract class ArrayBase<T> implements Structure<T> {

    protected T[] array;

    protected int capacity;

    protected static final int DEFAULT_CAPACITY = 10;

    protected int size;

    @SuppressWarnings("unchecked")
    protected ArrayBase(int capacity) {
        this.capacity = capacity;
        this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    @SuppressWarnings("unchecked")
    protected ArrayBase(int capacity, boolean comparable) {
        this.capacity = capacity;
        if (comparable) this.array = (T[]) new Comparable[capacity];
        else this.array = (T[]) new Object[capacity];
        this.size = 0;
    }

    public  boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            array[i] = null;
        }
        size = 0;
    }

    protected void resize(int capacity) {
        array = Arrays.copyOf(array, capacity);
        this.capacity = capacity;
    }


    public Iterator<T> iterator() {
        return new BaseIterator();
    }

    protected class BaseIterator implements Iterator<T> {
        private int current = 0;

        @Override
        public boolean hasNext() {
            return current < size;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            return array[current++];
        }
    }

    @Override
    public String toString() {
        if (this.isEmpty()) return "[]";
        return Arrays.toString(this.array);
    }
}
