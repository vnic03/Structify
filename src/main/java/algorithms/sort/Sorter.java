package algorithms.sort;

import java.util.List;


public interface Sorter<T extends Comparable<T>> {

    T[] sort(T[] array);

    List<T> sort(List<T> list);

    default void swap(T[] array, int i, int j) {
        T temp = array[i];
        array[i] = array[j];
        array[j] = temp;
    }

    default void swap(List<T> list, int i, int j) {
        T tmp = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tmp);
    }
}
