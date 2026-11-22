import java.util.Arrays;

// Horspool: the shift is read from a single table indexed by the last character of the alignment (t[s + m - 1]).
// Table O(m + 256), search worst case O(n * m), sublinear on average.
public class Horspool {

	static int horspool(String t, String p) {
		int n = t.length(), m = p.length();
		int[] shift = new int[256];
		Arrays.fill(shift, m);
		for (int i = 0; i < m - 1; i++)
			shift[p.charAt(i)] = m - 1 - i;
		int s = 0;
		while (s <= n - m) {
			int j = m - 1;
			while (j >= 0 &&
				p.charAt(j) == t.charAt(s + j))
				j--;
			if (j < 0) return s;
			s += shift[t.charAt(s + m - 1)];
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(horspool("LO LOELLO O HELLO", "HELLO"));  // 12
		System.out.println(horspool("LO LOELLO O HELLO", "WORLD"));  // -1
	}
}

/* Expected output:
12
-1
*/
