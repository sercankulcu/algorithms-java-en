// Prefix counter: in every node the field "count" holds the number of words passing through that node.
// countPrefix O(P); the price is one int per node.
public class PrefixCounter {

	static class TrieNode {
		TrieNode[] children = new TrieNode[26];  // 'a'..'z'
		boolean isEnd;                       // does a word end here?
		int count;                            // number of words passing through here
	}

	TrieNode root = new TrieNode();

	void insert(String word) { if (search(word)) return;
		TrieNode d = root;
		d.count++;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (d.children[i] == null) d.children[i] = new TrieNode();
			d = d.children[i];
			d.count++;          // one more word passed
		}
		d.isEnd = true;
	}

	// So that the counters do not swell when the same word is inserted twice, insert calls search first
	boolean search(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			node = node.children[ch - 'a'];
			if (node == null) return false;
		}
		return node.isEnd;
	}

	int countPrefix(String prefix) {
		TrieNode d = root;
		for (char ch : prefix.toCharArray()) {
			d = d.children[ch - 'a'];
			if (d == null) return 0;
		}
		return d.count;
	}

	public static void main(String[] args) {
		PrefixCounter t = new PrefixCounter();
		for (String w : new String[] {"and", "ant", "dad", "do"}) t.insert(w);
		System.out.println(t.root.count);         // 4
		System.out.println(t.countPrefix("an"));  // 2
		System.out.println(t.countPrefix("da"));  // 1
		System.out.println(t.countPrefix("x"));   // 0
		t.insert("ant");                          // already present: counters do not change
		System.out.println(t.countPrefix("an"));  // 2
	}
}

/* Expected output:
4
2
1
0
2
*/
