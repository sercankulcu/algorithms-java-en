import java.util.Arrays;

// LSD radix sort: stable counting sort at every character position, from right to left.
// One pass O(N + R), R = 256; W passes -> O(W * (N + R)). Makes no comparisons.
public class RadixSort {

	// Length of the longest string (W)
	static int maxLen(String[] a) {
		int W = 0;
		for (String s : a) W = Math.max(W, s.length());
		return W;
	}

	static void lsdSort(String[] a) {
		int W = maxLen(a);               // longest string
		for (int d = W - 1; d >= 0; d--)
			countingSort(a, d);            // right to left
	}

	static void countingSort(String[] a, int d) {
		int[] count = new int[256];      // ASCII
		for (String s : a) count[key(s, d)]++;
		for (int r = 1; r < 256; r++) count[r] += count[r - 1];
		String[] tmp = new String[a.length];
		for (int i = a.length - 1; i >= 0; i--)   // stable
			tmp[--count[key(a[i], d)]] = a[i];
		System.arraycopy(tmp, 0, a, 0, a.length);
	}

	static int key(String s, int d) {         // short string -> 0
		return d < s.length() ? s.charAt(d) : 0;
	}

	public static void main(String[] args) {
		String[] a = {"banana", "orange", "grape", "apple", "kiwi"};
		lsdSort(a);
		System.out.println(Arrays.toString(a));  // [apple, banana, grape, kiwi, orange]

		String[] b = {"cart", "car", "cat", "ca", "bat"};
		lsdSort(b);
		System.out.println(Arrays.toString(b));  // [bat, ca, car, cart, cat]
	}
}

/* Expected output:
[apple, banana, grape, kiwi, orange]
[bat, ca, car, cart, cat]
*/
