package algorithms.sort;

import java.util.List;


public class InsertionSort<T extends Comparable<T>> implements Sorter<T> {

    @Override
    public  T[] sort(T[] A) {
        for (int j = 1; j < A.length; j++) {
            T key = A[j];
            int i = j - 1;
            while (i >= 0 && A[i].compareTo(key) > 0) { // A[i] > key
                A[i + 1] = A[i];
                i--; // i = i - 1;
            }
            A[i + 1] = key;
        }
        return A;
    }

    @Override
    public List<T> sort(List<T> list) {
        for (int j = 1; j < list.size(); j++) {
            T key = list.get(j);
            int i = j - 1;
            while (i >= 0 && list.get(i).compareTo(key) > 0) {
                list.set(i + 1, list.get(i));
                i--;
            }
            list.set(i + 1, key);
        }
        return list;
    }
}
