import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

// LZW compression and decompression: the dictionary starts with single characters, w + c is added at every step.
// With a hash table as the dictionary, O(n) expected time.
public class LZW {

	// Initial dictionary for the compressor: the characters of the text in alphabetical order (A=0, B=1, C=2)
	static Map<String, Integer> initialDict(String s) {
		Map<String, Integer> dict = new HashMap<>();
		TreeSet<Character> letters = new TreeSet<>();
		for (char c : s.toCharArray()) letters.add(c);
		for (char c : letters) dict.put("" + c, dict.size());
		return dict;
	}

	// Initial dictionary for the decompressor: 0..255 ASCII
	static List<String> initialDict() {
		List<String> dict = new ArrayList<>();
		for (int i = 0; i < 256; i++) dict.add("" + (char) i);
		return dict;
	}

	static List<Integer> lzw(String s) {
		Map<String, Integer> dict =
			initialDict(s);  // A=0, B=1, C=2
		String w = "";
		List<Integer> codes = new ArrayList<>();
		for (char c : s.toCharArray()) {
			String wc = w + c;
			if (dict.containsKey(wc)) {
				w = wc;
			} else {
				codes.add(dict.get(w));
				dict.put(wc, dict.size());
				w = "" + c;
			}
		}
		if (!w.isEmpty())
			codes.add(dict.get(w));
		return codes;
	}

	static String lzwDecode(List<Integer> codes) {
		List<String> dict =
			initialDict();      // 0..255: byte values
		String w = dict.get(codes.get(0));
		StringBuilder s = new StringBuilder(w);
		for (int i = 1; i < codes.size(); i++) {
			int k = codes.get(i);
			String e = k < dict.size()
				? dict.get(k)
				: w + w.charAt(0); // special case
			s.append(e);
			dict.add(w + e.charAt(0));
			w = e;
		}
		return s.toString();
	}

	public static void main(String[] args) {
		System.out.println(lzw("ABABCABABCA"));  // [0, 1, 3, 2, 3, 5, 0]

		List<Integer> codes = Arrays.asList(97, 97, 98, 256, 99, 257, 261, 99, 98);
		System.out.println(lzwDecode(codes));    // aabaacababacb

		// Special case: k is not in the dictionary yet (k == dict.size()) -> e = w + w[0]
		System.out.println(lzwDecode(Arrays.asList(97, 256)));  // aaa
	}
}

/* Expected output:
[0, 1, 3, 2, 3, 5, 0]
aabaacababacb
aaa
*/
