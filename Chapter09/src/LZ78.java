import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// LZ78: starts with an empty dictionary; the longest dictionary piece w and the next character c are written as (index(w), c).
// With a hash table as the dictionary, O(n) expected time.
public class LZ78 {

	static List<String> lz78(String s) {
		Map<String, Integer> dict =
			new HashMap<>();
		List<String> out = new ArrayList<>();
		String w = "";
		for (char c : s.toCharArray()) {
			if (dict.containsKey(w + c)) {
				w = w + c;               // extend
			} else {
				int k = w.isEmpty() ? 0
					: dict.get(w);
				out.add("(" + k + ", " + c + ")");
				dict.put(w + c, dict.size() + 1);
				w = "";
			}
		}
		if (!w.isEmpty())        // last piece
			out.add("(" + dict.get(w) + ")");
		return out;
	}

	public static void main(String[] args) {
		System.out.println(lz78("aabaacabcabcb"));  // [(0, a), (1, b), (1, a), (0, c), (2, c), (5, b)]
		System.out.println(lz78("aaaa"));           // [(0, a), (1, a), (1)]
	}
}

/* Expected output:
[(0, a), (1, b), (1, a), (0, c), (2, c), (5, b)]
[(0, a), (1, a), (1)]
*/
