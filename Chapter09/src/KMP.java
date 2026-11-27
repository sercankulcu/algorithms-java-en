import java.util.Arrays;

// Knuth-Morris-Pratt: search without moving back in the text, using the LPS (longest proper prefix = suffix) table.
// LPS in O(m), search in O(n); total O(n + m).
public class KMP {

	// lps[q]: length of the longest proper prefix of p[0..q] that is also a suffix of it
	static int[] computeLPS(String p) {
		int m = p.length(), k = 0;
		int[] lps = new int[m];
		for (int q = 1; q < m; q++) {
			while (k > 0 &&
				p.charAt(k) != p.charAt(q))
				k = lps[k - 1];
			if (p.charAt(k) == p.charAt(q))
				k++;
			lps[q] = k;
		}
		return lps;
	}

	static int kmpSearch(String t, String p) {
		int n = t.length(), m = p.length();
		int[] lps = computeLPS(p);
		int q = 0;  // matched length
		for (int i = 0; i < n; i++) {
			while (q > 0 &&
				p.charAt(q) != t.charAt(i))
				q = lps[q - 1];
			if (p.charAt(q) == t.charAt(i))
				q++;
			if (q == m)
				return i - m + 1;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(computeLPS("ACABACACD")));  // [0, 0, 1, 0, 1, 2, 3, 2, 0]
		System.out.println(Arrays.toString(computeLPS("ababd")));      // [0, 0, 1, 2, 0]
		System.out.println(kmpSearch("ababcababcabababd", "ababd")); // 12
	}
}

/* Expected output:
[0, 0, 1, 0, 1, 2, 3, 2, 0]
[0, 0, 1, 2, 0]
12
*/
