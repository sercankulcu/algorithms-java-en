// Exponential search: the position doubles 1, 2, 4, 8, ... to find a range, then binary search.
// O(log i) (i: position of the target); used for unbounded arrays.
public class ExponentialSearch {

	static int exponentialSearch(int[] arr, int target, int n) {
		if (n == 0) return -1;
		if (arr[0] == target) return 0;
		int position = 1;
		while (position < n && arr[position] <= target)
			position *= 2;            // 1, 2, 4, 8, ...
		int low = position / 2, high = Math.min(position, n - 1);
		while (low <= high) {       // binary search
			int mid = (low + high) / 2;
			if (arr[mid] == target) return mid;
			if (arr[mid] < target) low = mid + 1;
			else high = mid - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {2, 9, 30, 32, 38, 47, 61, 69, 79, 81};
		System.out.println(exponentialSearch(arr, 47, 10));   // 5
		System.out.println(exponentialSearch(arr, 2, 10));    // 0
		System.out.println(exponentialSearch(arr, 40, 10));   // -1
	}
}

/* Expected output:
5
0
-1
*/
