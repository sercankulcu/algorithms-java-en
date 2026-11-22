import java.util.Arrays;

// Radix sort (LSD): starting from the least significant digit, a stable counting sort on each digit.
// Theta(d (n + 10)) for d digits.
public class RadixSort {

	static int[] radixSort(int[] arr) { // elements >= 0
		if (arr.length == 0) return arr;
		int max = Arrays.stream(arr).max().getAsInt();
		for (long exp = 1; max / exp > 0; exp *= 10)
			arr = countingSortByDigit(arr, (int) exp);
		return arr;
	}

	static int[] countingSortByDigit(int[] arr, int exp) {
		int n = arr.length;
		int[] output = new int[n], count = new int[10];
		for (int x : arr) count[(x / exp) % 10]++;
		for (int i = 1; i < 10; i++) count[i] += count[i - 1];
		for (int i = n - 1; i >= 0; i--) {
			int d = (arr[i] / exp) % 10;
			output[--count[d]] = arr[i];
		}
		return output;
	}

	public static void main(String[] args) {
		int[] arr = {170, 45, 75, 90, 802, 24, 2, 66};
		System.out.println(Arrays.toString(radixSort(arr)));
		// [2, 24, 45, 66, 75, 90, 170, 802]

		int[] d = {32, 15, 41, 23, 11};
		System.out.println(Arrays.toString(countingSortByDigit(d, 1)));   // [41, 11, 32, 23, 15]
	}
}

/* Expected output:
[2, 24, 45, 66, 75, 90, 170, 802]
[41, 11, 32, 23, 15]
*/
