package algorithms.sort;

import structs.List;


public interface Sort<T extends Comparable<T>> {

     T[] sort(T[] array);

    List<T> sort(List<T> list);
}
