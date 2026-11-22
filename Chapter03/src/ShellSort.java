import java.util.Arrays;

// Shell sort: with gaps n/2, n/4, ..., 1, distant elements are insertion-sorted.
// With this gap sequence the worst case is O(n^2), in practice much faster than insertion sort.
public class ShellSort {

	static void shellSort(int[] arr) {
		int n = arr.length;
		for (int gap = n / 2; gap > 0; gap /= 2)
			for (int i = gap; i < n; i++) {
				int temp = arr[i], j;
				for (j = i; j >= gap && arr[j - gap] > temp; j -= gap)
					arr[j] = arr[j - gap];
				arr[j] = temp;
			}
	}

	public static void main(String[] args) {
		int[] a = {6, 5, 3, 1, 8, 7, 2, 4};
		shellSort(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6, 7, 8]

		int[] arr = {5, 2, 6, 1, 4, 3};
		shellSort(arr);
		System.out.println(Arrays.toString(arr));   // [1, 2, 3, 4, 5, 6]
	}
}

/* Expected output:
[1, 2, 3, 4, 5, 6, 7, 8]
[1, 2, 3, 4, 5, 6]
*/
