package structs.probabilistic;

import structs.Structure;
import java.util.BitSet;
import java.util.function.Function;


public class BloomFilter<T> implements Structure<T> {

    private final BitSet bitSet;

    private final int bitSetSize;

    private final int hashCount;

    private final Function<T, Integer>[] hashFunctions;

    // keeps track of the number of times each bit position has been set
    private final int[] counters;


    public BloomFilter(int bitSetSize, int hashCount, Function<T, Integer>[] hashFunctions) {
        this.bitSetSize = bitSetSize;
        this.hashCount = hashCount;
        this.bitSet = new BitSet(bitSetSize);
        this.hashFunctions = hashFunctions;
        this.counters = new int[bitSetSize];
    }

    // Default Constructor for Strings
    @SuppressWarnings("unchecked")
    public BloomFilter(int bitSetSize) {
        this.bitSetSize = bitSetSize;
        this.hashCount = 3;
        this.bitSet = new BitSet(bitSetSize);
        this.hashFunctions = new Function[] {
                ( Function<String, Integer> ) value -> value.hashCode(),
                ( Function<String, Integer> ) value -> value.hashCode() * 31,
                ( Function<String, Integer> ) value -> value.hashCode() * 17
        };
        this.counters = new int[bitSetSize];
    }

    private int[] getHashes(T value) {
        int[] hashes = new int[hashCount];
        for (int i = 0; i < hashCount; i++) {
            hashes[i] = Math.abs(
                    hashFunctions[i].apply(value) % bitSetSize
            );
        }
        return hashes;
    }

    @Override
    public void add(T x) {
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            bitSet.set(hash);
            counters[hash]++;
        }
    }

    public boolean remove(T x) {
        if (!contains(x)) return false;
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            counters[hash]--;
            if (counters[hash] == 0) {
                bitSet.clear(hash);
            }
        }
        return true;
    }

    public boolean contains(T x) {
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            if (!bitSet.get(hash)) return false;
        }
        return true;
    }

    public void setBit(int index) {
        if (index >= bitSetSize) throw new IllegalArgumentException();
        bitSet.set(index);
    }

    @Override
    public boolean isEmpty() {
        return bitSet.isEmpty();
    }

    @Override
    public int size() {
        return bitSet.cardinality();
    }

    @Override
    public void clear() {
        bitSet.clear();
        for (int i = 0; i < bitSetSize; i++) {
            counters[i] = 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < bitSetSize; i++) {
            sb.append(bitSet.get(i) ? "1" : "0");
            if (i < bitSetSize - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
