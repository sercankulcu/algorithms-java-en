// Interpolation search: the position is estimated by linear interpolation.
// Average O(log log n) on uniformly distributed data, worst case O(n).
public class InterpolationSearch {

	static int interpolationSearch(int[] arr, int n,
								   int target) {
		int lo = 0, hi = n - 1;
		while (lo <= hi && target >= arr[lo]
						   && target <= arr[hi]) {
			if (arr[hi] == arr[lo]) return lo; // division by 0
			long num = (long) (target - arr[lo]) * (hi - lo);
			int idx = lo + (int) (num / (arr[hi] - arr[lo]));
			if (arr[idx] == target) return idx;
			if (arr[idx] < target) lo = idx + 1;
			else hi = idx - 1;
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
