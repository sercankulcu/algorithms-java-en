import java.util.Arrays;

// Bubble sort: adjacent pairs are compared, the larger one moves right.
// Worst case O(n^2); thanks to the swapped flag O(n) on a sorted array (early exit).
public class BubbleSort {

	static void bubbleSort(int[] arr) {
		int n = arr.length;
		boolean swapped;
		for(int i = 0; i < n - 1; i++) {
			swapped = false;
			for(int j = 0; j < n - 1 - i; j++) {
				if(arr[j] > arr[j+1]) {
					int temp = arr[j];
					arr[j] = arr[j+1];
					arr[j+1] = temp;
					swapped = true;
				}
			}
			if(swapped == false) break;
		}
	}

	public static void main(String[] args) {
		int[] a = {3, 1, 5, 2, 6, 4};
		bubbleSort(a);
		System.out.println(Arrays.toString(a));   // [1, 2, 3, 4, 5, 6]

		int[] arr = {5, 1, 9, 2, 10};
		bubbleSort(arr);
		System.out.println(Arrays.toString(arr));   // [1, 2, 5, 9, 10]
	}
}

/* Expected output:
[1, 2, 3, 4, 5, 6]
[1, 2, 5, 9, 10]
*/
