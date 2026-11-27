// Rabin-Karp: the hash of the window is updated by rolling; the characters are compared only if the hashes are equal.
// Average O(n + m), worst case (many collisions) O(n * m).
public class RabinKarp {

	// is t[s..s+m-1] == p? (exact check after the hashes are equal)
	static boolean matchAt(String t, String p, int s) {
		for (int j = 0; j < p.length(); j++)
			if (t.charAt(s + j) != p.charAt(j)) return false;
		return true;
	}

	static int rabinKarp(String t, String p) {
		int n = t.length(), m = p.length();
		int B = 256, Q = 101, h = 1;
		for (int i = 0; i < m - 1; i++)
			h = (h * B) % Q;  // B^(m-1) mod Q
		int ph = 0, th = 0;
		for (int i = 0; i < m; i++) {
			ph = (B * ph + p.charAt(i)) % Q;
			th = (B * th + t.charAt(i)) % Q;
		}
		for (int s = 0; s <= n - m; s++) {
			if (ph == th && matchAt(t, p, s))
				return s;
			if (s < n - m) {
				th = (B * (th - t.charAt(s) * h)
					+ t.charAt(s + m)) % Q;
				if (th < 0) th += Q;
			}
		}
		return -1;
	}

	public static void main(String[] args) {
		System.out.println(rabinKarp("abracadabra", "cad"));  // 4
		System.out.println(rabinKarp("abracadabra", "dab"));  // 6
		System.out.println(rabinKarp("abracadabra", "xyz"));  // -1
	}
}

/* Expected output:
4
6
-1
*/
