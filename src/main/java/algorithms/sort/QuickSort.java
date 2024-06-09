package algorithms.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class QuickSort<T extends Comparable<T>> implements Sorter<T> {

    private final Random random = new Random();


    @Override
    public T[] sort(T[] array) {
        if (array.length < 1) return array;
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(T[] array, int left, int right) {
        if (left < right) {
            T pivot = pivot(array, left, right);
            int index = partition(array, left, right, pivot);
            quickSort(array, left, index - 1);
            quickSort(array, index, right);
        }
    }

    private int partition(T[] array, int left, int right, T pivot) {
        while (left <= right) {
            while (array[left].compareTo(pivot) < 0) {
                left += 1;
            }
            while (array[right].compareTo(pivot) > 0) {
                right -= 1;
            }
            if (left <= right) {
                swap(array, left, right);
                left += 1;
                right -= 1;
            }
        }
        return left;
    }

    @Override
    public List<T> sort(List<T> list) {
        if (list.isEmpty()) return list;

        List<T> tmp = new ArrayList<>(list);
        quickSort(tmp, 0, tmp.size() - 1);

        for (int i = 0; i < list.size(); i++) {
            list.set(i, tmp.get(i));
        }

        return list;
    }

    private void quickSort(List<T> list, int left, int right) {
        if (left < right) {
            T pivot = pivot(list, left, right);
            int index = partition(list, left, right, pivot);
            quickSort(list, left, index - 1);
            quickSort(list, index, right);
        }
    }

    private int partition(List<T> list, int left, int right, T pivot) {
        while (left <= right) {
            while (list.get(left).compareTo(pivot) < 0) {
                left += 1;
            }
            while (list.get(right).compareTo(pivot) > 0) {
                right -= 1;
            }
            if (left <= right) {
                swap(list, left, right);
                left += 1;
                right -= 1;
            }
        }
        return left;
    }

    private T pivot(T[] array, int left, int right) {
        return array[left + random.nextInt(right - left + 1)];
    }

    private T pivot(List<T> list, int left, int right) {
        return list.get(left + random.nextInt(right - left + 1));
    }
}
