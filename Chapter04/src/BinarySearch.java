// Binary search: in a sorted array the range halves at every step, O(log n).
// Iterative and recursive versions; firstIndex finds the first index of a repeated key.
public class BinarySearch {

	static int binarySearch(int[] arr, int n, int target) {
		int lo = 0, hi = n - 1;
		while (lo <= hi) {
			int mid = (lo + hi) / 2;
			if (target < arr[mid])
				hi = mid - 1;      // left half
			else if (target > arr[mid])
				lo = mid + 1;       // right half
			else
				return mid;          // found
		}
		return -1;
	}

	static int search(int[] arr, int lo,
					  int hi, int target) {
		if (lo > hi) return -1;  // empty range
		int mid = (lo + hi) / 2;
		if (arr[mid] == target) return mid;
		if (arr[mid] < target)             // right half
			return search(arr, mid + 1, hi, target);
		return search(arr, lo, mid - 1, target);
	}

	static int firstIndex(int[] arr, int target) {
		int lo = 0, hi = arr.length - 1, result = -1;
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			if (arr[mid] == target) {
				result = mid;      // record it,
				hi = mid - 1;    // search on the left
			} else if (arr[mid] < target)
				lo = mid + 1;
			else
				hi = mid - 1;
		}
		return result;
	}

	public static void main(String[] args) {
		int[] a = {5, 7, 10, 17, 23, 34, 55, 67, 99};
		System.out.println(binarySearch(a, 9, 23));   // 4 (best case)
		System.out.println(binarySearch(a, 9, 54));   // -1

		int[] arr = {0, 4, 7, 10, 14, 23, 45, 47, 53};
		System.out.println(binarySearch(arr, 9, 47));   // 7

		int[] b = {1, 3, 5, 6, 7, 8, 11, 14, 16, 18, 20, 22, 23, 25, 27};
		System.out.println(search(b, 0, b.length - 1, 20));   // 10

		int[] c = {2, 4, 4, 4, 4, 7, 9, 12};
		System.out.println(firstIndex(c, 4));   // 1
	}
}

/* Expected output:
4
-1
7
10
1
*/
