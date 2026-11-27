// Step counting and O(1) / O(n) examples: array sum O(n), first element O(1), maximum O(n).
public class TimeComplexity {

	// The "Time Complexity" part of the slides: 2n + 3 steps -> O(n)
	static int findSum(int[] arr, int n) {
		int sum = 0;                      // once
		for (int i = 0; i < n; i++)       // n+1 comparisons
			sum += arr[i];                // n times
		return sum;                       // once
	}

	// O(1): independent of the array size
	static void printFirst(int[] arr) {
		if (arr.length > 0)
			System.out.println("First element: "
					+ arr[0]);
		else
			System.out.println("Array is empty");
	}

	// O(n): every element is examined once
	static Integer findMax(int[] arr) {
		if (arr.length == 0)
			return null;            // empty array
		int max = arr[0];
		for (int x : arr)
			if (x > max)
				max = x;
		return max;
	}

	public static void main(String[] args) {
		int[] arr = {7, 3, 9, 4, 8, 1};
		System.out.println(findSum(arr, arr.length));   // 32
		printFirst(arr);                                // First element: 7
		printFirst(new int[0]);                         // Array is empty
		System.out.println(findMax(arr));               // 9
		System.out.println(findMax(new int[0]));        // null
	}
}

/* Expected output:
32
First element: 7
Array is empty
9
null
*/
