import java.util.Arrays;

// Boyer-Moore (bad-character rule): the pattern is compared right to left,
// on a mismatch s += max(1, j - last[c]). Worst case O(n * m), sublinear in practice.
public class BoyerMoore {

	// last[c]: rightmost position of character c in the pattern; -1 if absent
	static int[] lastOccurrence(String p) {
		int[] last = new int[256];
		Arrays.fill(last, -1);
		for (int i = 0; i < p.length(); i++)
			last[p.charAt(i)] = i;
		return last;
	}

	static int boyerMoore(String t, String p) {
		int n = t.length(), m = p.length();
		int[] last = lastOccurrence(p);
		int s = 0;
		while (s <= n - m) {
			int j = m - 1;
			while (j >= 0 &&
				p.charAt(j) == t.charAt(s + j))
				j--;
			if (j < 0) return s;
			s += Math.max(1,
				j - last[t.charAt(s + j)]);
		}
		return -1;
	}

	public static void main(String[] args) {
		int[] last = lastOccurrence("abacab");
		System.out.println(last['a'] + " " + last['b'] + " " + last['c'] + " " + last['x']);  // 4 5 3 -1

		last = lastOccurrence("abe");
		System.out.println(last['a'] + " " + last['b'] + " " + last['e']);  // 0 1 2
		System.out.println(boyerMoore("merhaba naber", "abe"));             // 9
	}
}

/* Expected output:
4 5 3 -1
0 1 2
9
*/
