// Interpolation search: the position is estimated by linear interpolation.
// Average O(log log n) on uniformly distributed data, worst case O(n).
public class InterpolationSearch {

	static int interpolationSearch(int[] arr, int n,
								   int target) {
		int low = 0, high = n - 1;
		while (low <= high && target >= arr[low]
						   && target <= arr[high]) {
			if (arr[high] == arr[low]) return low; // division by 0
			long num = (long) (target - arr[low]) * (high - low);
			int index = low + (int) (num / (arr[high] - arr[low]));
			if (arr[index] == target) return index;
			if (arr[index] < target) low = index + 1;
			else high = index - 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {10, 12, 13, 16, 18, 19, 20, 21, 22, 23, 24, 33, 35, 42, 47};
		System.out.println(interpolationSearch(arr, 15, 24));   // 10 (5 probes)
		System.out.println(interpolationSearch(arr, 15, 25));   // -1
	}
}

/* Expected output:
10
-1
*/
