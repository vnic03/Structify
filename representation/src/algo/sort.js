/*
    A -> the array to be sorted

    swap() -> swaps the indexes of two elements in the array

    Insertion Sort: Sorts the array by repeatedly taking 
                    the next element and inserting it into 
                    the correct position in the sorted portion of the array.

    Bubble Sort:    Sorts the array by repeatedly swapping adjacent 
                    elements that are in the wrong order.

    Merge Sort:     Sorts the array by dividing it into two halves, 
                    sorting each half, and then merging the sorted halves.

    Quick Sort:     Sorts the array by selecting a 'pivot' element and 
                    partitioning the array into elements less than 
                    the pivot and elements greater than the pivot, 
                    then sorting the subarrays.
*/


const insertionSort = (A) => {
    for (let j = 1; j < A.length; j++) {
        let key = A[j];
        let i = j - 1;
        while (i >= 0 && A[i] > key) {
            A[i + 1] = A[i];
            i--; 
        }
        A[i + 1] = key;
    }
    return A;
}


const bubbleSort = (A) => {
    let n = A.length;
    for (let i = 0; i < n - 1; i++) {
        for (let j = 0; j < n - 1 - i; j++) {
            if (A[j] > A[j + 1]) swap(A, j, j + 1);
        }
    }
    return A;
} 


const mergeSort = (A) => {

    const sort = (A, start, end) => {
        if (end - start > 1) {
            let mid = Math.floor((start + end) / 2);

            sort(A, start, mid);
            sort(A, mid, end);

            merge(A, start, mid,  end);
        }
    }

    const merge = (A, start, mid, end) => {
        const left = A.slice(start, mid);
        const right = A.slice(mid, end);

        let i = 0, j = 0, k = start;

        while(i < left.length && j < right.length) {
            if (left[i] <= right[j]) {
                A[k] = left[i];
                i ++;
            } else {
                A[k] = right[j];
                j ++;
            }
            k++;
        }

        while(i < left.length) {
            A[k] = left[i];
            k++; i++;
        }

        while(j < right.length) {
            A[k] = right[j];
            k++; j++;
        }
    }
    if (A.length <= 1) return A;
    sort(A, 0, A.length);
    return A;
}


const quickSort = (A) => {

    const pivot = (A, left, right) => {
        return A[left + Math.floor(Math.random() * (right - left + 1))];
    }

    const sort = (A, left, right) => {
        if (left < right) {
            let pivotValue = pivot(A, left, right);
            let index = partition(A, left, right, pivotValue);
            sort(A, left, index - 1);
            sort(A, index, right);
        }
    }

    const partition = (A, left, right, pivotValue) => {
        while (left <= right) {
            while (A[left] < pivotValue) {
                left += 1;
            }
            while (A[right] > pivotValue) {
                right -= 1;
            }
            if (left <= right) {
                swap(A, left, right);
                left += 1;
                right -= 1;
            }
        }
        return left;
    }

    if (A.length < 1) return A;
    sort(A, 0, A.length - 1);
    return A;
}


const swap = (A, i, j) => {
    let temp = A[i];
    A[i] = A[j];
    A[j] = temp;
}


export {
    insertionSort,
    bubbleSort,
    mergeSort,
    quickSort 
}
