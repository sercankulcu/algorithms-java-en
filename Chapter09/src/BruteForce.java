// Brute-force string matching: the pattern is compared left to right at every shift.
// Worst case O(n * m), extra memory O(1).
public class BruteForce {

	// position of the first occurrence of p in t; -1 if none
	static int bruteForce(String t, String p) {
		int n = t.length(), m = p.length();
		for (int i = 0; i <= n - m; i++) {
			int j = 0;
			while (j < m &&
				t.charAt(i + j) == p.charAt(j))
				j++;
			if (j == m) return i;
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(bruteForce("HELLO WORLD", "LO"));   // 3
		System.out.println(bruteForce("HELLO WORLD", "XY"));   // -1
	}
}

/* Expected output:
3
-1
*/
