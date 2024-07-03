package structs.linked;

import structs.Structure;
import java.util.LinkedList;
import java.util.function.Function;


public class SimpleHashTable<K> implements Structure<K> {

    private final LinkedList<K>[] table;

    private final Function<K, Integer> hashFunction;


    @SuppressWarnings("unchecked")
    public SimpleHashTable(int capacity, Function<K, Integer> hashFunction) {
        table = new LinkedList[capacity];
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
        this.hashFunction = hashFunction;
    }

    @Override
    public void add(K element) {
        int index = hashFunction.apply(element);
        table[index].add(element);
    }

    @Override
    public boolean isEmpty() {
        for (LinkedList<K> bucket : table) {
            if (!bucket.isEmpty()) return false;
        }
        return true;
    }

    @Override
    public int size() {
        int size = 0;
        for (LinkedList<K> bucket : table) {
            size += bucket.size();
        }
        return size;
    }

    @Override
    public void clear() {
        for (LinkedList<K> bucket : table) {
            bucket.clear();
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append(i).append(": ");
            for (K element : table[i]) {
                sb.append(element).append(" -> ");
            }
            sb.append("null\n");
        }
        return sb.toString();
    }
}
