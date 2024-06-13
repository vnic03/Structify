import structs.List;
import structs.probabilistic.BloomFilter;
import structs.probabilistic.SkipList;

public class Test {


    public static void main(String[] args) {
        List<Integer> list = new SkipList<>();

        BloomFilter<Integer> i = new BloomFilter<>(10);

        i.iterator();
    }



}
