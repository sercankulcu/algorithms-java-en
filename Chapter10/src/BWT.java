import java.util.Arrays;

// Burrows-Wheeler transform: the last column L of the sorted rotations; inverted with the LF mapping.
// Direct transform O(n^2 log n); inverse O(n).
public class BWT {

	// Direct method: n rotations are generated, sorted, and their last characters taken
	static String bwt(String s) {
		int n = s.length();
		String[] rot = new String[n];
		for (int i = 0; i < n; i++)
			rot[i] = s.substring(i) + s.substring(0, i);
		Arrays.sort(rot);
		StringBuilder L = new StringBuilder();
		for (String r : rot) L.append(r.charAt(n - 1));
		return L.toString();
	}

	static String inverseBwt(String L) {   // with $
		int n = L.length();
		int[] occ = new int[n];
		int[] count = new int[256];
		int[] C = new int[256];
		for (int i = 0; i < n; i++)
			occ[i] = count[L.charAt(i)]++;
		for (int c = 1; c < 256; c++)
			C[c] = C[c - 1] + count[c - 1];
		StringBuilder s = new StringBuilder();
		int i = 0;                   // the $ row
		for (int k = 0; k < n - 1; k++) {
			char c = L.charAt(i);
			s.insert(0, c);
			i = C[c] + occ[i];        // LF(i)
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(bwt("BANANA"));          // NNBAAA
		System.out.println(bwt("abraca"));          // caraab
		System.out.println(bwt("banana$"));         // annb$aa
		System.out.println(inverseBwt("annb$aa"));  // banana
	}
}

/* Expected output:
NNBAAA
caraab
annb$aa
banana
*/
