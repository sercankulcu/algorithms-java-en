import java.util.Arrays;

// Quicksort: partition around a pivot, sort the two sides recursively.
// Average Theta(n log n), worst case (sorted input) Theta(n^2); in place, not stable.
public class QuickSort {

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	// Lomuto partition, pivot = last element
	static int partition(int[] arr, int lo, int hi) {
		int pivot = arr[hi];
		int i = lo - 1;
		for (int j = lo; j < hi; j++) {
			if (arr[j] <= pivot) {
				i++;
				swap(arr, i, j);
			}
		}
		swap(arr, i + 1, hi);
		return i + 1;
	}

	static void quickSort(int[] arr, int lo, int hi) {
		if (lo < hi) {
			int p = partition(arr, lo, hi);
			quickSort(arr, lo, p - 1);
			quickSort(arr, p + 1, hi);
		}
	}

	// Pseudocode PARTITION(A, p, q): pivot x = A[p] (first element)
	static int PARTITION(int[] A, int p, int q) {
		int x = A[p];            // pivot
		int i = p;
		for (int j = p + 1; j <= q; j++) {
			if (A[j] <= x) {
				i = i + 1;
				swap(A, i, j);
			}
		}
		swap(A, p, i);
		return i;
	}

	// Pseudocode QUICKSORT(A, p, r); Java indices start at 0: the first call is (A, 0, n - 1)
	static void QUICKSORT(int[] A, int p, int r) {
		if (p < r) {
			int q = PARTITION(A, p, r);
			QUICKSORT(A, p, q - 1);
			QUICKSORT(A, q + 1, r);
		}
	}

	public static void main(String[] args) {
		int[] arr = {2, 8, 7, 1, 3, 5, 6, 4};
		System.out.println(partition(arr, 0, arr.length - 1));   // 3
		System.out.println(Arrays.toString(arr));                // [2, 1, 3, 4, 7, 5, 6, 8]

		int[] d = {5, 2, 8, 1, 9, 3};
		quickSort(d, 0, d.length - 1);
		System.out.println(Arrays.toString(d));                  // [1, 2, 3, 5, 8, 9]

		int[] A = {6, 10, 13, 5, 8, 3, 2, 11};
		System.out.println(PARTITION(A, 0, A.length - 1));       // 3
		System.out.println(Arrays.toString(A));                  // [2, 5, 3, 6, 8, 13, 10, 11]

		int[] B = {6, 10, 13, 5, 8, 3, 2, 11};
		QUICKSORT(B, 0, B.length - 1);
		System.out.println(Arrays.toString(B));                  // [2, 3, 5, 6, 8, 10, 11, 13]
	}
}

/* Expected output:
3
[2, 1, 3, 4, 7, 5, 6, 8]
[1, 2, 3, 5, 8, 9]
3
[2, 5, 3, 6, 8, 13, 10, 11]
[2, 3, 5, 6, 8, 10, 11, 13]
*/
