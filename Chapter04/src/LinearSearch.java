// Linear search: scan an unsorted array from start to end, worst case O(n).
// The sentinel version removes the i < n check from the loop: n + 2 comparisons (classic: 2n).
public class LinearSearch {

	static int linearSearch(int[] arr, int n,
							int target) {
		int i = 0;
		while (i < n && arr[i] != target)
			i++;
		if (i < n)
			return i;      // found: index
		else
			return -1;     // not found
	}

	static int sentinelSearch(int[] arr, int n,
							  int target) {
		if (n == 0) return -1;
		int last = arr[n - 1];
		arr[n - 1] = target;      // sentinel
		int i = 0;
		while (arr[i] != target)
			i++;
		arr[n - 1] = last;        // put it back
		if (i < n - 1 || last == target)
			return i;
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {33, 21, 12, 7, 8, 1, 3, 44, 56, 18};
		System.out.println(linearSearch(arr, 10, 33));   // 0 (best case)
		System.out.println(linearSearch(arr, 10, 1));    // 5
		System.out.println(linearSearch(arr, 10, 99));   // -1 (worst case)

		int[] d = {7, 3, 9, 4, 6};
		System.out.println(sentinelSearch(d, 5, 5));          // -1
		System.out.println(sentinelSearch(d, 5, 6));          // 4 (the target is the last element)
		System.out.println(sentinelSearch(d, 5, 9));          // 2
	}
}

/* Expected output:
0
5
-1
-1
4
2
*/
