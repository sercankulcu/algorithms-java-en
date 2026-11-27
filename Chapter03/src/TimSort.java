import java.util.Arrays;

// Simplified Timsort: runs of MIN_RUN elements are insertion-sorted,
// then merged with the size doubling each time. O(n log n); stable.
public class TimSort {

	static final int MIN_RUN = 4;

	static void timSort(int[] arr) {
		int n = arr.length;
		for (int i = 0; i < n; i += MIN_RUN)               // 1) runs
			insertionSort(arr, i, Math.min(i + MIN_RUN - 1, n - 1));
		for (int size = MIN_RUN; size < n; size *= 2)       // 2) merge
			for (int left = 0; left < n; left += 2 * size) {
				int mid = left + size - 1;
				int right = Math.min(left + 2 * size - 1, n - 1);
				if (mid < right) merge(arr, left, mid, right);
			}
	}

	// Insertion-sorts the range arr[left..right]
	static void insertionSort(int[] arr, int left, int right) {
		for (int i = left + 1; i <= right; i++) {
			int key = arr[i];
			int j = i - 1;
			while (j >= left && arr[j] > key) {
				arr[j + 1] = arr[j];
				j = j - 1;
			}
			arr[j + 1] = key;
		}
	}

	static void merge(int[] A, int p, int q, int r) {
		int[] L = Arrays.copyOfRange(A, p, q + 1);
		int[] R = Arrays.copyOfRange(A, q + 1, r + 1);
		int i = 0, j = 0, k = p;
		while (i < L.length && j < R.length) {
			if (L[i] <= R[j]) A[k++] = L[i++];
			else              A[k++] = R[j++];
		}
		while (i < L.length) A[k++] = L[i++];
		while (j < R.length) A[k++] = R[j++];
	}

	public static void main(String[] args) {
		int[] arr = {5, 21, 7, 23, 19, 10, 3, 12, 8, 1};
		timSort(arr);
		System.out.println(Arrays.toString(arr));   // [1, 3, 5, 7, 8, 10, 12, 19, 21, 23]
	}
}

/* Expected output:
[1, 3, 5, 7, 8, 10, 12, 19, 21, 23]
*/
