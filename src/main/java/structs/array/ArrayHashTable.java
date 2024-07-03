package structs.array;

import structs.HashTable;
import structs.List;
import java.util.AbstractMap.SimpleEntry;
import java.util.HashSet;
import java.util.Map.Entry;
import java.util.Set;
import java.util.function.Function;


public class ArrayHashTable<K, V> extends ArrayBase<Entry<K, V>> implements HashTable<K, V> {


    private final ArrayList<Entry<K, V>>[] table;

    private Function<K, Integer> hashFunction;


    @SuppressWarnings("unchecked")
    public ArrayHashTable() {
        super(HashTable.DEFAULT_CAPACITY);
        table = new ArrayList[HashTable.DEFAULT_CAPACITY];
        for (int i = 0; i < HashTable.DEFAULT_CAPACITY; i++) {
            table[i] = new ArrayList<>();
        }
        this.hashFunction = this::defaultHash;
    }

    public ArrayHashTable(Function<K, Integer> hashFunction) {
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
        ArrayList<Entry<K, V>> bucket = table[hash(key)];
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
                return;
            }
        }
        bucket.add(new SimpleEntry<>(key, value));
        size++;
    }

    @Override
    public void add(Entry<K, V> element) {
        this.put(element.getKey(), element.getValue());
    }

    @Override
    public V get(K key) {
        ArrayList<Entry<K, V>> bucket = table[hash(key)];
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public V remove(K key) {
        ArrayList<Entry<K, V>> bucket = table[hash(key)];
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                V value = entry.getValue();
                bucket.remove(i);
                size--;
                return value;
            }
        }
        return null;
    }

    @Override
    public boolean containsKey(K key) {
        ArrayList<Entry<K, V>> bucket = table[hash(key)];
        for (int i = 0; i < bucket.size(); i++) {
            Entry<K, V> entry = bucket.get(i);
            if (entry.getKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean containsValue(V value) {
        for (ArrayList<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
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
        for (ArrayList<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                keys.add(entry.getKey());
            }
        }
        return keys;
    }

    @Override
    public List<V> values() {
        ArrayList<V> values = new ArrayList<>();
        for (ArrayList<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                values.add(entry.getValue());
            }
        }
        return values;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<Entry<K, V>> entries = new HashSet<>();
        for (ArrayList<Entry<K, V>> bucket : table) {
            for (int i = 0; i < bucket.size(); i++) {
                Entry<K, V> entry = bucket.get(i);
                entries.add(entry);
            }
        }
        return entries;
    }

    @Override
    public void clear() {
        for (ArrayList<Entry<K, V>> bucket : table) {
            bucket.clear();
        }
        size = 0;
    }
}
