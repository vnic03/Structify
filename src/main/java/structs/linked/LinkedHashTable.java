package structs.linked;

import structs.HashTable;
import structs.List;
import structs.array.ArrayList;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.AbstractMap.SimpleEntry;
import java.util.Set;
import java.util.function.Function;


public class LinkedHashTable<K, V> extends LinkedBase<Entry<K, V>> implements HashTable<K, V> {

    private final LinkedList<Entry<K, V>>[] table;

    private Function<K, Integer> hashFunction;


    @SuppressWarnings("unchecked")
    public LinkedHashTable() {
        table = new LinkedList[DEFAULT_CAPACITY];
        for (int i = 0; i < DEFAULT_CAPACITY; i++) {
            table[i] = new LinkedList<>();
        }
        this.hashFunction = this::defaultHash;
    }

    public LinkedHashTable(Function<K, Integer> hashFunction) {
        this();
        this.hashFunction = hashFunction;
    }

    private int defaultHash(K key) {
        return (key.hashCode() & 0x7fffffff) % table.length;
    }

    private int hash(K key) {
        return hashFunction.apply(key);
    }

    @Override
    public void put(K key, V value) {
        LinkedList<Entry<K, V>> bucket = table[hash(key)];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new SimpleEntry<>(key, value));
        incrementSize();
    }

    @Override
    public void add(Entry<K, V> element) {
        this.put(element.getKey(), element.getValue());
    }

    @Override
    public V get(K key) {
        LinkedList<Entry<K, V>> bucket = table[hash(key)];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        LinkedList<Entry<K, V>> bucket = table[hash(key)];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                bucket.remove(entry);
                decrementSize();
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        LinkedList<Entry<K, V>> bucket = table[hash(key)];
        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                if (entry.getValue().equals(value)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public Set<K> keySet() {
        Set<K> keys = new HashSet<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        List<V> values = new ArrayList<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (LinkedList<Entry<K, V>> bucket : table) {
            for (Entry<K, V> entry : bucket) {
                entries.add(entry);
            }
        }
        return entries;
    }

    @Override
    public void clear() {
        for (int i = 0; i < table.length; i++) {
            table[i].clear();
        }
        size = 0;
    }
}
