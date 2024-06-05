package structs.probabilistic;

import structs.List;
import java.util.Random;

// TODO: when done, not abstract
public abstract class SkipList<T extends Comparable<T>> implements List<T> {

    private static final int MAX_LEVEL = 16;

    private static class Node<T> {
        final T value;
        final Node<T>[] forward;

        @SuppressWarnings("unchecked")
        public Node(T value, int level) {
            this.value = value;
            this.forward = new Node[level + 1];
        }
    }

    private final Node<T> head = new Node<>(null, MAX_LEVEL);

    private final Random random = new Random();


    @Override
    public void add(T x) {
        Node<T>[] update = new Node[MAX_LEVEL + 1];
        Node<T> n = head;

        for (int i = MAX_LEVEL; i >= 0; i--) {
            while (n.forward[i] != null && n.forward[i].value.compareTo(x) < 0) {
                n = n.forward[i];
            }
            update[i] = n;
        }

        int level = randomLevel();
        n = new Node<>(x, level);

        for (int i = 0; i <= level ; i++) {
            n = n.forward[i] = update[i].forward[i];
            update[i].forward[i] = n;
        }
    }

    private int randomLevel() {
        int level = 0;
        while (level < MAX_LEVEL && random.nextInt(2) == 0) {
            level++;
        }
        return level;
    }
}
