import java.util.Arrays;

// Merge sort: divide, sort the two halves recursively, merge.
// T(n) = 2T(n/2) + Theta(n) -> Theta(n log n) in every case; stable, O(n) extra memory.
public class MergeSort {

	static void mergeSort(int[] arr, int left, int right) {
		if (left < right) {
			int mid = left + (right - left) / 2;
			mergeSort(arr, left, mid);
			mergeSort(arr, mid + 1, right);
			merge(arr, left, mid, right);
		}
	}

	// Merges the sorted parts A[p..q] and A[q+1..r]: Theta(r - p + 1)
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
		int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};
		mergeSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));   // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] A = {1, 3, 5, 6, 2, 4, 7, 8};         // L = [1, 3, 5, 6], R = [2, 4, 7, 8]
		merge(A, 0, 3, 7);
		System.out.println(Arrays.toString(A));      // [1, 2, 3, 4, 5, 6, 7, 8]
	}
}

/* Expected output:
[1, 2, 3, 4, 5, 6, 7, 8]
[1, 2, 3, 4, 5, 6, 7, 8]
*/
