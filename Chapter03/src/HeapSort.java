import java.util.Arrays;

// Heap sort: first a max-heap is built in O(n) time, then the root is moved to the end n - 1 times.
// O(n log n) in every case; in place, not stable.
public class HeapSort {

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void heapSort(int[] arr) {
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; i--)   // 1) build the heap
			heapify(arr, n, i);
		for (int i = n - 1; i > 0; i--) {      // 2) sort
			swap(arr, 0, i);                     // largest to the end
			heapify(arr, i, 0);                  // sift the root down
		}
	}

	static void heapify(int[] arr, int n, int i) {
		while (true) {
			int largest = i, left = 2 * i + 1, right = 2 * i + 2;
			if (left < n && arr[left] > arr[largest]) largest = left;
			if (right < n && arr[right] > arr[largest]) largest = right;
			if (largest == i) return;          // in place
			swap(arr, i, largest);
			i = largest;                       // continue downwards
		}
	}

	public static void main(String[] args) {
		int[] arr = {6, 5, 3, 1, 8, 7, 2, 4};
		int n = arr.length;
		for (int i = n / 2 - 1; i >= 0; i--)      // build the heap only
			heapify(arr, n, i);
		System.out.println(Arrays.toString(arr));   // [8, 6, 7, 4, 5, 3, 2, 1]

		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		heapSort(a);
		System.out.println(Arrays.toString(a));      // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] b = {3, 9, 2, 1, 4, 5};
		for (int i = b.length / 2 - 1; i >= 0; i--)
			heapify(b, b.length, i);
		System.out.println(Arrays.toString(b));      // [9, 4, 5, 1, 3, 2]
	}
}

/* Expected output:
[8, 6, 7, 4, 5, 3, 2, 1]
[1, 2, 3, 4, 5, 6, 7, 8]
[9, 4, 5, 1, 3, 2]
*/
