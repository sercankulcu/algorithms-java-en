import java.util.Arrays;

// Counting sort: no comparisons; keys are counted and placed using prefix sums.
// Theta(n + k) time and memory (k: largest key); stable because it scans from the end.
public class CountingSort {

	static int[] countingSort(int[] arr) { // elements >= 0
		if (arr.length == 0) return arr;
		int max = Arrays.stream(arr).max().getAsInt();
		int[] count = new int[max + 1];
		for (int x : arr) count[x]++;
		for (int i = 1; i <= max; i++) count[i] += count[i - 1];
		int[] output = new int[arr.length];
		for (int i = arr.length - 1; i >= 0; i--) {
			output[count[arr[i]] - 1] = arr[i];
			count[arr[i]]--;
		}
		return output;
	}

	// Pseudocode from the slides (keys 1..k, arrays start at 1); Java does not use A[0], B[0], C[0].
	static int[] countingSortPseudo(int[] A, int n, int k) {
		int[] B = new int[n + 1];
		int[] C = new int[k + 1];
		for (int i = 1; i <= k; i++)
			C[i] = 0;
		for (int j = 1; j <= n; j++)
			C[A[j]] = C[A[j]] + 1;   // C[i] = |{key = i}|
		for (int i = 2; i <= k; i++)
			C[i] = C[i] + C[i - 1];  // C[i] = |{key <= i}|
		for (int j = n; j >= 1; j--) {
			B[C[A[j]]] = A[j];
			C[A[j]] = C[A[j]] - 1;
		}
		return B;
	}

	public static void main(String[] args) {
		int[] arr = {4, 1, 3, 4, 3};
		System.out.println(Arrays.toString(countingSort(arr)));   // [1, 3, 3, 4, 4]

		int[] A = {0, 4, 1, 3, 4, 3};               // A[1..5]
		int[] B = countingSortPseudo(A, 5, 4);
		System.out.println(Arrays.toString(Arrays.copyOfRange(B, 1, 6)));   // [1, 3, 3, 4, 4]
	}
}

/* Expected output:
[1, 3, 3, 4, 4]
[1, 3, 3, 4, 4]
*/
