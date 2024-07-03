package structs.linked;

import structs.List;


public class LinkedList<T> extends LinkedBase<T> implements List<T> {

    @Override
    public void add(T x) {
        Node<T> node = new Node<>(x);
        if (head == null) head = node;
         else {
             Node<T> current = head;
             while (current.next != null) {
                 current = current.next;
             }
             current.next = node;
        }
         incrementSize();
    }

    @Override
    public void add(int index, T x) {
        e(index);
        Node<T> node = new Node<>(x);
        if (index == 0) {
            node.next = head;
            head = node;
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            node.next = current.next;
            current.next = node;
        }
        incrementSize();
    }

    @Override
    public boolean remove(T x) {
        if (head == null) return false;

        if (head.data.equals(x)) {
            head = head.next;
            decrementSize();
            return true;
        }

        Node<T> current = head;
        while (current.next != null && !current.next.data.equals(x)) {
            current = current.next;
        }

        if (current.next == null) return false;

        current.next = current.next.next;

        decrementSize();
        return true;
    }

    @Override
    public T remove(int index) {
        e(index);
        Node<T> current = head;
        if (index == 0) {
            T data = head.data;
            head = head.next;
            decrementSize();
            return data;
        }

        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }

        T data = current.next.data;
        current.next = current.next.next;
        decrementSize();
        return data;
    }

    @Override
    public T get(int index) {
        e(index);
        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.data;
    }

    @Override
    public T set(int index, T x) {
        e(index);

        Node<T> current = head;
        for (int i = 0; i < index; i++) {
            current = current.next;
        }

        T old = current.data;
        current.data = x;
        return old;
    }

    @Override
    public boolean contains(T x) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(x)) return true;
            current = current.next;
        }
        return false;
    }

    @Override
    public int indexOf(T x) {
        Node<T> current = head;
        int i = 0;

        while (current != null) {
            if (current.data.equals(x)) return i;
            current = current.next;
            i++;
        }
        return -1;
    }

    @Override
    public int lastIndexOf(T x) {
        Node<T> current = head;
        int i = 0;
        int last = -1;

        while (current != null) {
            if (current.data.equals(x)) last = i;
            current = current.next;
            i++;
        }
        return last;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
