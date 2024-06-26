package structs.linked;

import structs.Structure;
import java.util.Iterator;
import java.util.NoSuchElementException;


abstract class LinkedBase<T> implements Structure<T> {

    protected static class Node<T> {
        protected T data;
        protected Node<T> next;

        protected Node(T data) {
            this.data = data;
            this.next = null;
        }
    }

    protected Node<T> head;

    protected int size;

    public LinkedBase() {
        this.head = null;
        this.size = 0;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    protected void incrementSize() {
        size++;
    }

    protected void decrementSize() {
        size--;
    }

    protected void e(int index) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new LinkedIterator();
    }

    private class LinkedIterator implements Iterator<T> {
        private Node<T> current = head;

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            if (!hasNext()) throw new NoSuchElementException();
            T data = current.data;
            current = current.next;
            return data;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("{ size=").append(size).append(", elements=[");

        Node<T> current = head;
        while (current != null) {
            sb.append(current.data);
            if (current.next != null) {
                sb.append(" -> ");
            }
            current = current.next;
        }

        sb.append("] }");
        return sb.toString();
    }
}
