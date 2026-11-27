import java.util.Arrays;

// Insertion sort: each element is shifted into place in the sorted part on its left.
// Worst case O(n^2), O(n) on a sorted array; stable and in place.
public class InsertionSort {

	static void insertionSort(int[] arr) {
		int n = arr.length;
		for(int i = 1; i < n; i++) {
			int key = arr[i];
			int j = i - 1;
			while(j >= 0 && arr[j] > key) {
				arr[j+1] = arr[j];
				j = j - 1;
			}
			arr[j+1] = key;
		}
	}

	public static void main(String[] args) {
		int[] a = {3, 1, 5, 2, 6, 4};
		insertionSort(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] arr = {5, 1, 9, 2, 10};
		insertionSort(arr);
		System.out.println(Arrays.toString(arr));   // [1, 2, 5, 9, 10]
	}
}

/* Expected output:
[1, 2, 3, 4, 5, 6]
[1, 2, 5, 9, 10]
*/
