import java.util.Arrays;

// Suffix array: the starting indices of the suffixes in sorted order; those that start with p form a contiguous range.
// Brute-force construction O(n^2 log n); search with two binary searches O(m log n).
public class SuffixArray {

	// Brute force: all suffixes are sorted lexicographically
	static int[] suffixArray(String t) {
		Integer[] ix = new Integer[t.length()];
		for (int i = 0; i < ix.length; i++) ix[i] = i;
		Arrays.sort(ix, (x, y) -> t.substring(x).compareTo(t.substring(y)));
		int[] sa = new int[ix.length];
		for (int i = 0; i < sa.length; i++) sa[i] = ix[i];
		return sa;
	}

	// half-open range [first, last) in SA of the suffixes that start with p
	static int[] search(String t, int[] sa, String p) {
		int lo = 0, hi = sa.length;
		while (lo < hi) {   // first >= p
			int mid = (lo + hi) / 2;
			if (t.substring(sa[mid])
				.compareTo(p) < 0) lo = mid + 1;
			else hi = mid;
		}
		int first = lo;
		hi = sa.length;
		while (lo < hi) {   // those that do not start with p
			int mid = (lo + hi) / 2;
			if (t.startsWith(p, sa[mid]))
				lo = mid + 1;
			else hi = mid;
		}
		return new int[] { first, lo };
	}

	public static void main(String[] args) {
		String t = "banana";
		int[] sa = suffixArray(t);
		System.out.println(Arrays.toString(sa));  // [5, 3, 1, 0, 4, 2]

		int[] r = search(t, sa, "an");
		System.out.println(Arrays.toString(r));   // [1, 3]
		for (int i = r[0]; i < r[1]; i++)
			System.out.print(sa[i] + " ");
		System.out.println();                     // 3 1
	}
}

/* Expected output:
[5, 3, 1, 0, 4, 2]
[1, 3]
3 1
*/
