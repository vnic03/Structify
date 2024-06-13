package structs.probabilistic;

import structs.List;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Random;


public class SkipList<T extends Comparable<T>> implements List<T> {

    private static final int MAX_LEVEL = 16;

    private static class Node<T> {
        T value;
        final Node<T>[] forward;

        @SuppressWarnings("unchecked")
        public Node(T value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    private final Node<T> head = new Node<>(null, MAX_LEVEL);

    private final Random random = new Random();

    private int size = 0;


    @Override
    public void add(T x) {
        Node<T>[] update = findUpdateArray(x);
        int level = randomLevel();
        Node<T> n = new Node<>(x, level);

        for (int i = 0; i <= level; i++) {
            n.forward[i] = update[i].forward[i];
            update[i].forward[i] = n;
        }
        size += 1;
    }

    @Override
    public boolean remove(T x) {
        Node<T>[] update = findUpdateArray(x);
        Node<T> n = update[0].forward[0];

        if (n != null && n.value.compareTo(x) == 0) {
            for (int i = 0; i <= MAX_LEVEL; i++) {
                if (update[i].forward[i] != n) break;
                update[i].forward[i] = n.forward[i];
            }
            size -= 1;
            return true;
        }
        return false;
    }


    @SuppressWarnings("unchecked")
    private Node<T>[] findUpdateArray(T x) {
        Node<T>[] update = new Node[MAX_LEVEL + 1];
        Node<T> n = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (n.forward[i] != null && n.forward[i].value.compareTo(x) < 0) {
                n = n.forward[i];
            }
            update[i] = n;
        }
        return update;
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size " + size
        );
        Node<T> current = head.forward[0];
        int i = 0;

        while (current != null) {
            if (i == index) return current.value;
            current = current.forward[0];
            i++;
        }
        throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size " + size
        );
    }

    @Override
    public T set(int index, T x) {
        if (index < 0 || index >= size) throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size " + size
        );
        Node<T> current = head.forward[0];
        int i = 0;

        while (current != null) {
            if (i == index) {
                T old = current.value;
                current.value = x;
                return old;
            }
            current = current.forward[0];
            i++;
        }
        throw new IndexOutOfBoundsException(
                "Index " + index + " out of bounds for size " + size
        );
    }

    @Override
    public boolean contains(T x) {
        Node<T> current = head;
        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (current.forward[i] != null && current.forward[i].value.compareTo(x) < 0) {
                current = current.forward[i];
            }
        }
        current = current.forward[0];
        return current != null && current.value.compareTo(x) == 0;
    }

    @Override
    public int indexOf(T x) {
        Node<T> current = head.forward[0];
        int index = 0;

        while (current != null) {
            if (current.value.compareTo(x) == 0) {
                return index;
            }
            current = current.forward[0];
            index++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T x) {
        Node<T> current = head.forward[0];
        int index = 0;
        int last = -1;

        while (current != null) {
            if (current.value.compareTo(x) == 0) {
                last = index;
            }
            current = current.forward[0];
            index++;
        }
        return last;
    }

    @Override
    public void clear() {
        for (int i = 0; i <= MAX_LEVEL; i++) {
            head.forward[i] = null;
        }
        size = 0;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void add(int index, T x) {
        throw new UnsupportedOperationException("Not supported with Skip lists");
    }

    @Override
    public T remove(int index) {
        throw new UnsupportedOperationException("Not supported with Skip lists");
    }

    private int randomLevel() {
        int level = 0;
        while (level < MAX_LEVEL && random.nextInt(2) == 0) {
            level++;
        }
        return level;
    }

    @Override
    public Iterator<T> iterator() {
        return new SkipListIterator();
    }

    private class SkipListIterator implements Iterator<T> {
        private Node<T> current;

        public SkipListIterator() {
            this.current = head.forward[0];
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (current == null) throw new NoSuchElementException();
            T value = current.value;
            current = current.forward[0];
            return value;
        }
    }
}
