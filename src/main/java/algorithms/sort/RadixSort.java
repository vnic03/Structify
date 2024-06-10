package algorithms.sort;

import java.util.Collections;
import java.util.List;


public class RadixSort implements Sorter<Integer> {

    @Override
    public Integer[] sort(Integer[] array) {
        int max = Collections.max(List.of(array));

        for (int digit = 1; max / digit > 0; digit *= 10) {
            countingSort(array, digit);
        }
        return array;
    }

    private void countingSort(Integer[] array, int digit) {
        int n = array.length;
        Integer[] output = new Integer[n];
        int[] bucket = new int[10]; // count array for digits (0 to 9)

        for (int i = 0; i < 10; i++) {
            bucket[i] = 0;
        }

        for (Integer number : array) {
            int digitValue = digit(number, digit);
            bucket[digitValue]++;
        }

        // Change bucket[i] so that bucket[i] now contains the actual position of
        // this digit in the output array
        for (int i = 1; i < 10; i++) {
            bucket[i] += bucket[i - 1];
        }

        for (int i = n - 1; i >= 0; i--) {
            int digitValue = digit(array[i], digit);
            output[bucket[digitValue] - 1] = array[i];
            bucket[digitValue]--;
        }

        System.arraycopy(output, 0, array, 0, n);
    }

    private int digit(int number, int digit) {
        return (number / digit) % 10;
    }

    @Override
    public List<Integer> sort(List<Integer> list) {
        return List.of(sort(list.toArray(new Integer[0])));
    }
}
