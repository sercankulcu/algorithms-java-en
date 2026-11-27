import java.util.Arrays;

// Selection sort: in each round the smallest of the unsorted part is found and moved to the front.
// O(n^2) comparisons in every case, at most n - 1 swaps.
public class SelectionSort {

	public void sort(int[] arr) {
		int n = arr.length;
		for(int i = 0; i < n - 1; i++) {
			int minIdx = i;
			for(int j = i + 1; j < n; j++) {
				if(arr[j] < arr[minIdx]) {
					minIdx = j;
				}
			}
			int temp = arr[minIdx];
			arr[minIdx] = arr[i];
			arr[i] = temp;
		}
	}

	public static void main(String[] args) {
		SelectionSort s = new SelectionSort();
		int[] a = {3, 1, 5, 2, 6, 4};
		s.sort(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] arr = {5, 1, 10, 2, 9};
		s.sort(arr);
		System.out.println(Arrays.toString(arr));   // [1, 2, 5, 9, 10]
	}
}

/* Expected output:
[1, 2, 3, 4, 5, 6]
[1, 2, 5, 9, 10]
*/
