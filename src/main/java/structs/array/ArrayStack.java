package structs.array;

import structs.Stack;
import java.util.Arrays;


public class ArrayStack<T> extends ArrayBase<T> implements Stack<T> {

    private int top;

    public ArrayStack(int capacity) {
        super(capacity);
        this.top = -1;
    }

    public ArrayStack() {
        this(DEFAULT_CAPACITY);
    }

    @Override
    public void push(T x) {
        if (top == capacity - 1) resize(2 * capacity);

        top += 1;
        array[top] = x;
        size += 1;
    }

    @Override
    public T pop() {
        if (top == -1) return null;

        T x = array[top];
        array[top--] = null;
        size -= 1;
        if (size > 0 && size == capacity / 4) {
            resize(capacity / 2);
        }
        return x;
    }

    @Override
    public T peek() {
        return top != -1 ? array[top] : null;
    }

    public int search(T x) {
        for (int i = top; i >= 0 ; i--) {
            if (array[i].equals(x)) return top - i + 1;
        }
        return -1;
    }

    @Override
    public void clear() {
        Arrays.fill(array, 0, size, null);
        top = -1;
        size = 0;
        resize(DEFAULT_CAPACITY);
    }
}
