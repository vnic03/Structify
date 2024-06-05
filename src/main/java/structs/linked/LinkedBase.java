package structs.linked;


abstract class LinkedBase<T> {

    protected static class Node<T> {
        T data;
        Node<T> next;

        Node(T data) {
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
}
