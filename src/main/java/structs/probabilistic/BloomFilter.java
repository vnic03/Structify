package structs.probabilistic;

import structs.Structure;
import java.util.BitSet;
import java.util.function.Function;


public class BloomFilter<T> implements Structure<T> {

    private final BitSet bitSet;

    private final int bitSetSize;

    private final int hashCount;

    private final Function<T, Integer>[] hashFunctions;


    public BloomFilter(int bitSetSize, int hashCount, Function<T, Integer>[] hashFunctions) {
        this.bitSetSize = bitSetSize;
        this.hashCount = hashCount;
        this.bitSet = new BitSet(bitSetSize);
        this.hashFunctions = hashFunctions;
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

    public void add(T x) {
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            bitSet.set(hash);
        }
    }

    public boolean contains(T x) {
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            if (!bitSet.get(hash)) return false;
        }
        return true;
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
    }
}
