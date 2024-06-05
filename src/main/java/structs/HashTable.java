package structs;

import java.util.Map.Entry;
import java.util.Set;


public interface HashTable<K, V> extends Structure {

    int DEFAULT_CAPACITY = 16;

    void put(K key, V value);

    V get(K key);

    V remove(K key);

    boolean containsKey(K key);

    boolean containsValue(V value);

    Set<K> keySet();

    List<V> values();

    Set<Entry<K, V>> entrySet();
}
