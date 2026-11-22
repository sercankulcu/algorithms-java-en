// Pattern automaton: state q = length of the longest prefix of the pattern that matches the end of the text read so far.
// Construction O(m * |S|), search O(n); the automaton form of the KMP prefix function.
public class PatternAutomaton {

	// DFA for p: dfa[q][c] = new state when c is read in state q (0..m)
	static int[][] buildDfa(String p) {
		int m = p.length();
		int[][] dfa = new int[m + 1][256];
		dfa[0][p.charAt(0)] = 1;
		int x = 0;                          // restart state
		for (int q = 1; q <= m; q++) {
			for (int c = 0; c < 256; c++)
				dfa[q][c] = dfa[x][c];          // mismatch: behave like x
			if (q < m) {
				dfa[q][p.charAt(q)] = q + 1;    // match: advance by one
				x = dfa[x][p.charAt(q)];
			}
		}
		return dfa;
	}

	static int dfaSearch(int[][] dfa, String t, int m) {
		int q = 0;                     // state
		for (int i = 0; i < t.length(); i++) {
			q = dfa[q][t.charAt(i)];
			if (q == m)
				return i - m + 1;          // match
		}
		return -1;
	}

	public static void main(String[] args) {
		int[][] dfa = buildDfa("ACACAGA");
		System.out.println(dfa[5]['C']);                      // 4
		System.out.println(dfaSearch(dfa, "GACACACAGA", 7));  // 3
		System.out.println(dfaSearch(dfa, "ACACACAC", 7));    // -1
	}
}

/* Expected output:
4
3
-1
*/
