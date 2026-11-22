// Suffix trie: all suffixes of the text are inserted into a trie; every substring is a path from the root.
// Construction O(n^2) time and nodes; substring query O(m).
public class SuffixTrie {

	static class TrieNode {
		TrieNode[] child = new TrieNode[26];  // 'a'..'z'
		boolean isWord;                       // does a suffix end here?
	}

	static TrieNode suffixTrie(String t) {
		TrieNode root = new TrieNode();
		for (int s = 0; s < t.length(); s++) {
			TrieNode node = root;
			for (int j = s; j < t.length(); j++) {
				int i = t.charAt(j) - 'a';
				if (node.child[i] == null)
					node.child[i] = new TrieNode();
				node = node.child[i];
			}
			node.isWord = true;  // suffix ends
		}
		return root;
	}

	// is p a path from the root? (is p a substring of the text?)
	static boolean isSubstring(TrieNode root, String p) {
		TrieNode node = root;
		for (char ch : p.toCharArray()) {
			node = node.child[ch - 'a'];
			if (node == null) return false;
		}
		return true;
	}

	static int countNodes(TrieNode node) {
		int count = 1;
		for (TrieNode c : node.child)
			if (c != null) count += countNodes(c);
		return count;
	}

	public static void main(String[] args) {
		TrieNode root = suffixTrie("abab");
		System.out.println(countNodes(root));         // 8
		System.out.println(isSubstring(root, "ba"));  // true
		System.out.println(isSubstring(root, "bb"));  // false
	}
}

/* Expected output:
8
true
false
*/
