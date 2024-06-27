package algorithms.sort;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public interface Sorter<T extends Comparable<T>> {

    T[] sort(T[] array);

    List<T> sort(List<T> list);

    default void swap(T[] array, int i, int j) {
        T tmp = array[i];
        array[i] = array[j];
        array[j] = tmp;
    }

    default void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }

    default T[] reverseSort(T[] array) {
        Arrays.sort(array, Comparator.reverseOrder());
        return array;
    }

    default List<T> reverseSort(List<T> list) {
        list.sort(Collections.reverseOrder());
        return list;
    }
}
