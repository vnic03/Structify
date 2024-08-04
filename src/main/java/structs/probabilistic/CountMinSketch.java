package structs.probabilistic;

import java.util.function.Function;


public class CountMinSketch<T> extends BloomFilter<T> {


    public CountMinSketch(int bitSetSize, int hashCount, Function<T, Integer>[] hashFunctions)
    {
        super(bitSetSize, hashCount, hashFunctions);
    }

    public CountMinSketch(int bitSetSize) {
        super(bitSetSize);
    }

    @Override
    public void add(T x) {
        int[] hashes = getHashes(x);
        for (int hash : hashes) {
            counters[hash]++;
        }
    }

    public int estimate(T x) {
        int min = Integer.MAX_VALUE;
        for (int hash : getHashes(x)) {
            min = Math.min(min, counters[hash]);
        }
        return min;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for (int i = 0; i < bitSetSize; i++) {
            sb.append(counters[i]);
            if (i < bitSetSize - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
