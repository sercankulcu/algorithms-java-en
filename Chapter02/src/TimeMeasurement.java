// Time measurement and step counting: linear search takes 3n + 3 steps in the worst case -> Theta(n).
// The slides use 10^8 elements, which is too large for memory, so 10^7 elements are used.
public class TimeMeasurement {

	static int linearSearch(int[] a, int target) {
		for (int i = 0; i < a.length; i++) {
			if (a[i] == target)
				return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = new int[10_000_000];
		for (int i = 0; i < arr.length; i++)
			arr[i] = i % 10000;               // 10001 is not in the array: worst case

		long start = System.currentTimeMillis();
		linearSearch(arr, 10001);    // 10^7 elements
		long end = System.currentTimeMillis();
		System.out.println("Elapsed time: "
			+ (end - start) + " ms");  // varies from machine to machine

		System.out.println(linearSearch(new int[]{4, 7, 2}, 5));   // -1 (12 steps = 3n + 3)
		int[] b = {7, 3, 9, 4, 8, 1};
		System.out.println(linearSearch(b, 7));   // 0 (best case)
		System.out.println(linearSearch(b, 1));   // 5 (worst case)
	}
}

/* Note: the output of this program varies on every run (time measurement, random numbers or
   the order of threads); the block below is one sample run. */
/* Expected output:
Elapsed time: 7 ms
-1
0
5
*/
