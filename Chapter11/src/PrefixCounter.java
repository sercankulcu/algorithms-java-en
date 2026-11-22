// Prefix counter: in every node the field "count" holds the number of words passing through that node.
// prefixCount O(P); the price is one int per node.
public class PrefixCounter {

	static class TrieNode {
		TrieNode[] child = new TrieNode[26];  // 'a'..'z'
		boolean isWord;                       // does a word end here?
		int count;                            // number of words passing through here
	}

	TrieNode root = new TrieNode();

	void insert(String word) { if (search(word)) return;
		TrieNode node = root;
		node.count++;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (node.child[i] == null) node.child[i] = new TrieNode();
			node = node.child[i];
			node.count++;          // one more word passed
		}
		node.isWord = true;
	}

	// So that the counters do not swell when the same word is inserted twice, insert calls search first
	boolean search(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			node = node.child[ch - 'a'];
			if (node == null) return false;
		}
		return node.isWord;
	}

	int prefixCount(String prefix) {
		TrieNode node = root;
		for (char ch : prefix.toCharArray()) {
			node = node.child[ch - 'a'];
			if (node == null) return 0;
		}
		return node.count;
	}

	public static void main(String[] args) {
		PrefixCounter t = new PrefixCounter();
		for (String w : new String[] {"and", "ant", "dad", "do"}) t.insert(w);
		System.out.println(t.root.count);         // 4
		System.out.println(t.prefixCount("an"));  // 2
		System.out.println(t.prefixCount("da"));  // 1
		System.out.println(t.prefixCount("x"));   // 0
		t.insert("ant");                          // already present: counters do not change
		System.out.println(t.prefixCount("an"));  // 2
	}
}

/* Expected output:
4
2
1
0
2
*/
