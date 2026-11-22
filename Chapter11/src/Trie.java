import java.util.ArrayList;
import java.util.List;

// Trie (prefix tree): insert, search, prefix query, wildcard search, autocomplete, longest common prefix, delete.
// Insert/search O(L), prefix query O(P); alphabet 'a'..'z' (M = 26).
public class Trie {

	public static class TrieNode {
		TrieNode[] child;  // 'a'..'z'
		boolean isWord;    // does a word end here?

		public TrieNode() {
			isWord = false;
			child = new TrieNode[26];
		}
	}

	TrieNode root = new TrieNode();

	public void insert(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (node.child[i] == null)
				node.child[i] = new TrieNode();
			node = node.child[i];
		}
		node.isWord = true;
	}

	public boolean search(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (node.child[i] == null)
				return false;
			node = node.child[i];
		}
		return node.isWord;
	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (char ch : prefix.toCharArray()) {
			int i = ch - 'a';
			if (node.child[i] == null)
				return false;
			node = node.child[i];
		}
		return true;  // search: node.isWord
	}

	// '.' matches any letter; call: wildcard(root, pattern, 0)
	boolean wildcard(TrieNode node, String pat, int j) {
		if (node == null) return false;
		if (j == pat.length()) return node.isWord;
		char ch = pat.charAt(j);
		if (ch != '.')
			return wildcard(node.child[ch - 'a'], pat, j + 1);
		for (int i = 0; i < 26; i++)
			if (wildcard(node.child[i], pat, j + 1)) return true;
		return false;
	}

	// node at the end of the prefix path; null if there is no such path
	TrieNode findNode(String prefix) {
		TrieNode node = root;
		for (char ch : prefix.toCharArray()) {
			node = node.child[ch - 'a'];
			if (node == null) return null;
		}
		return node;
	}

	List<String> complete(String prefix) {
		List<String> result = new ArrayList<>();
		TrieNode node = findNode(prefix);
		if (node != null)
			collect(node, new StringBuilder(prefix), result);
		return result;
	}

	void collect(TrieNode node, StringBuilder path,
			List<String> result) {
		if (node.isWord) result.add(path.toString());
		for (int i = 0; i < 26; i++)
			if (node.child[i] != null) {
				path.append((char) ('a' + i));
				collect(node.child[i], path, result);
				path.setLength(path.length() - 1);
			}
	}

	String longestCommonPrefix() {
		StringBuilder sb = new StringBuilder();
		TrieNode node = root;
		while (!node.isWord) {
			int only = -1, kids = 0;
			for (int i = 0; i < 26; i++)
				if (node.child[i] != null) { kids++; only = i; }
			if (kids != 1) break;  // branch or leaf
			sb.append((char) ('a' + only));
			node = node.child[only];
		}
		return sb.toString();
	}

	// are all 26 children null? O(M)
	boolean isEmpty(TrieNode node) {
		for (TrieNode c : node.child)
			if (c != null) return false;
		return true;
	}

	// true: the parent can delete node; call: delete(root, word, 0)
	boolean delete(TrieNode node, String word, int j) {
		if (j == word.length()) {        // word ended
			if (!node.isWord) return false;
			node.isWord = false;
			return isEmpty(node);          // no children?
		}
		int i = word.charAt(j) - 'a';
		TrieNode c = node.child[i];
		if (c == null) return false;     // word not present
		if (delete(c, word, j + 1)) {
			node.child[i] = null;          // remove the child
			return !node.isWord && isEmpty(node);
		}
		return false;
	}

	public static void main(String[] args) {
		Trie t = new Trie();
		for (String w : new String[] {"and", "ant", "dad", "do"}) t.insert(w);
		System.out.println(t.search("dad") + " " + t.startsWith("dad"));  // true true
		System.out.println(t.search("da") + " " + t.startsWith("da"));    // false true
		System.out.println(t.search("an") + " " + t.startsWith("an"));    // false true
		System.out.println(t.search("dog") + " " + t.startsWith("dog"));  // false false

		System.out.println(t.wildcard(t.root, "d.d", 0));   // true
		System.out.println(t.wildcard(t.root, ".n.", 0));   // true
		System.out.println(t.wildcard(t.root, ".o", 0));    // true
		System.out.println(t.wildcard(t.root, "b..", 0));   // false
		System.out.println(t.wildcard(t.root, "....", 0));  // false

		t.delete(t.root, "dad", 0);
		System.out.println(t.complete(""));  // [and, ant, do]

		Trie t2 = new Trie();
		for (String w : new String[] {"a", "in", "inn", "tea", "ted", "ten", "to"}) t2.insert(w);
		System.out.println(t2.complete("te"));  // [tea, ted, ten]

		Trie t3 = new Trie();
		for (String w : new String[] {"kar", "kart", "karton"}) t3.insert(w);
		System.out.println(t3.longestCommonPrefix());  // kar

		Trie t4 = new Trie();
		for (String w : new String[] {"kart", "karton"}) t4.insert(w);
		System.out.println(t4.longestCommonPrefix());  // kart
	}
}

/* Expected output:
true true
false true
false true
false false
true
true
true
false
false
[and, ant, do]
[tea, ted, ten]
kar
kart
*/
