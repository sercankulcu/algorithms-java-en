// Levenshtein (edit) distance: the minimum number of insertions, deletions and substitutions; dynamic programming.
// Time and memory O(m * n).
public class Levenshtein {

	static int levenshtein(String a, String b) {
		int m = a.length(), n = b.length();
		int[][] D = new int[m + 1][n + 1];
		for (int i = 0; i <= m; i++) D[i][0] = i;  // deletion
		for (int j = 0; j <= n; j++) D[0][j] = j;  // insertion
		for (int i = 1; i <= m; i++)
			for (int j = 1; j <= n; j++)
				if (a.charAt(i - 1) == b.charAt(j - 1))
					D[i][j] = D[i - 1][j - 1];            // equal
				else
					D[i][j] = 1 + Math.min(D[i - 1][j - 1], // substitute
						Math.min(D[i][j - 1],         // insert
							D[i - 1][j]));       // delete
		return D[m][n];
	}

	public static void main(String[] args) {
		System.out.println(levenshtein("kitten", "sitting"));  // 3
		System.out.println(levenshtein("ercan", "sercan"));    // 1
		System.out.println(levenshtein("apple", "banana"));    // 5
		System.out.println(levenshtein("kar", "ark"));         // 2
	}
}

/* Expected output:
3
1
5
2
*/
