package algorithms.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class MergeSort<T extends Comparable<T>> implements Sorter<T> {


    @Override
    public T[] sort(T[] array) {
        if (array.length <= 1) return array;
        mergeSort(array, 0, array.length);
        return array;
    }

    @Override
    public List<T> sort(List<T> list) {
        if (list.size() <= 1) return list;

        List<T> tmp = new ArrayList<>(list);

        mergeSort(tmp, 0, tmp.size());

        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(i));
        }

        return list;
    }

    private void mergeSort(T[] array, int start, int end) {
        if (end - start > 1) {
            int mid = (start + end) / 2;

            mergeSort(array, start, mid);
            mergeSort(array, mid, end);

            merge(array, start, mid, end);
        }
    }

    private void mergeSort(List<T> list, int start, int end) {
        if (end - start > 1) {
            int mid = (start + end) / 2;

            mergeSort(list, start, mid);
            mergeSort(list, mid, end);

            merge(list, start, mid, end);
        }
    }

    private void merge(T[] array, int start, int mid, int end) {
        T[] left = Arrays.copyOfRange(array, start, mid);
        T[] right = Arrays.copyOfRange(array, mid, end);

        int i = 0, j = 0, k = start;

        while (i < left.length && j < right.length) {
            if (left[i].compareTo(right[j]) <= 0) {
                array[k] = left[i];
                k += 1; i += 1;
            } else {
                array[k] = right[j];
                k += 1; j += 1;
            }
        }

        while (i < left.length) {
            array[k] = left[i];
            k += 1; i += 1;
        }
        while (j < right.length) {
            array[k] = right[j];
            k += 1; j += 1;
        }
    }

    private void merge(List<T> list, int start, int mid, int end) {
        List<T> left = new ArrayList<>(list.subList(start, mid));
        List<T> right = new ArrayList<>(list.subList(mid, end));

        int i = 0, j = 0, k = start;

        while (i < left.size() && j < right.size()) {
            if (left.get(i).compareTo(right.get(j)) <= 0) {
                list.set(k, left.get(i));
                k += 1; i += 1;
            } else {
                list.set(k, right.get(j));
                k += 1; j += 1;
            }
        }

        while (i < left.size()) {
            list.set(k, left.get(i));
            k += 1; i += 1;
        }
        while (j < right.size()) {
            list.set(k, right.get(j));
            k += 1; j += 1;
        }
    }
}
