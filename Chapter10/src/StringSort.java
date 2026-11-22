import java.util.Arrays;

// Lexicographic comparison and comparison-based string sorting.
// compare O(W); lexSort O(N^2) comparisons x O(W) = O(N^2 * W).
public class StringSort {

	// Negative: a first, positive: b first, 0: equal (like String.compareTo)
	static int compare(String a, String b) {
		int n = Math.min(a.length(), b.length());
		for (int k = 0; k < n; k++) {
			char x = a.charAt(k), y = b.charAt(k);
			if (x != y)
				return x - y;   // first differing character
		}
		return a.length() - b.length();
	}

	static void lexSort(String[] a) {
		int n = a.length;
		for (int i = 0; i < n - 1; i++)
			for (int j = i + 1; j < n; j++)
				if (a[i].compareTo(a[j]) > 0) {
					String tmp = a[i];
					a[i] = a[j];
					a[j] = tmp;
				}
	}

	public static void main(String[] args) {
		System.out.println(compare("apple", "apricot"));  // -2
		System.out.println(compare("car", "cart"));       // -1

		String[] a = {"eabcdf", "abcde", "bcdea", "cdeab", "deabcg"};
		lexSort(a);
		System.out.println(Arrays.toString(a));  // [abcde, bcdea, cdeab, deabcg, eabcdf]

		String[] b = {"banana", "orange", "grape", "apple", "kiwi"};
		lexSort(b);
		System.out.println(Arrays.toString(b));  // [apple, banana, grape, kiwi, orange]
	}
}

/* Expected output:
-2
-1
[abcde, bcdea, cdeab, deabcg, eabcdf]
[apple, banana, grape, kiwi, orange]
*/
