package structs.linked;

import structs.Stack;


public class LinkedStack<T> extends LinkedBase<T> implements Stack<T> {

    @Override
    public void push(T x) {
        Node<T> node = new Node<>(x);
        node.next = head;
        head = node;
        incrementSize();
    }

    @Override
    public T pop() {
        if (this.isEmpty()) return null;
        T data = head.data;
        head = head.next;
        decrementSize();
        return data;
    }

    @Override
    public T peek() {
        return head != null ? head.data : null;
    }


    public int search(T x) {
        Node<T> current = head;
        int i = 0;

        for (; current != null; current = current.next, i++) {
            if (current.data.equals(x)) return i;
        }
        return i;
    }

    @Override
    public void clear() {
        head = null;
        size = 0;
    }
}
