package algorithms.sort;

import java.util.List;


public class BubbleSort<T extends Comparable<T>> implements Sorter<T> {


    @Override
    public T[] sort(T[] array) {
        int n = array.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (array[j].compareTo(array[j + 1]) > 0) {
                    swap(array, j, j + 1);
                }
            }
        }
        return array;
    }

    @Override
    public List<T> sort(List<T> list) {
        int n = list.size();
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (list.get(j).compareTo(list.get(j +  1)) > 0) {
                    swap(list, j, j + 1);
                }
            }
        }
        return list;
    }
}
