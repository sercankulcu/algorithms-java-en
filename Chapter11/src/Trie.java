import java.util.ArrayList;
import java.util.List;

// Trie (prefix tree): insert, search, prefix query, wildcard search, autocomplete, longest common prefix, delete.
// Insert/search O(L), prefix query O(P); alphabet 'a'..'z' (M = 26).
public class Trie {

	public static class TrieNode {
		TrieNode[] children;  // 'a'..'z'
		boolean isEnd;    // does a word end here?

		public TrieNode() {
			isEnd = false;
			children = new TrieNode[26];
		}
	}

	TrieNode root = new TrieNode();

	public void insert(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (node.children[i] == null)
				node.children[i] = new TrieNode();
			node = node.children[i];
		}
		node.isEnd = true;
	}

	public boolean search(String word) {
		TrieNode node = root;
		for (char ch : word.toCharArray()) {
			int i = ch - 'a';
			if (node.children[i] == null)
				return false;
			node = node.children[i];
		}
		return node.isEnd;
	}

	public boolean startsWith(String prefix) {
		TrieNode node = root;
		for (char ch : prefix.toCharArray()) {
			int i = ch - 'a';
			if (node.children[i] == null)
				return false;
			node = node.children[i];
		}
		return true;  // search: node.isEnd
	}

	// '.' matches any letter; call: wildcard(root, pattern, 0)
	boolean wildcard(TrieNode d, String s, int j) {
		if (d == null) return false;
		if (j == s.length()) return d.isEnd;
		char ch = s.charAt(j);
		if (ch != '.')
			return wildcard(d.children[ch - 'a'], s, j + 1);
		for (int i = 0; i < 26; i++)
			if (wildcard(d.children[i], s, j + 1)) return true;
		return false;
	}

	// node at the end of the prefix path; null if there is no such path
	TrieNode findNode(String prefix) {
		TrieNode node = root;
		for (char ch : prefix.toCharArray()) {
			node = node.children[ch - 'a'];
			if (node == null) return null;
		}
		return node;
	}

	List<String> complete(String prefix) {
		List<String> result = new ArrayList<>();
		TrieNode d = findNode(prefix);
		if (d != null)
			collect(d, new StringBuilder(prefix), result);
		return result;
	}

	void collect(TrieNode d, StringBuilder path,
			List<String> result) {
		if (d.isEnd) result.add(path.toString());
		for (int i = 0; i < 26; i++)
			if (d.children[i] != null) {
				path.append((char) ('a' + i));
				collect(d.children[i], path, result);
				path.setLength(path.length() - 1);
			}
	}

	String longestCommonPrefix() {
		StringBuilder sb = new StringBuilder();
		TrieNode d = root;
		while (!d.isEnd) {
			int only = -1, count = 0;
			for (int i = 0; i < 26; i++)
				if (d.children[i] != null) { count++; only = i; }
			if (count != 1) break;  // branch or leaf
			sb.append((char) ('a' + only));
			d = d.children[only];
		}
		return sb.toString();
	}

	// are all 26 children null? O(M)
	boolean isEmpty(TrieNode d) {
		for (TrieNode c : d.children)
			if (c != null) return false;
		return true;
	}

	// true: the parent can delete d; call: delete(root, word, 0)
	boolean delete(TrieNode d, String k, int j) {
		if (j == k.length()) {        // word ended
			if (!d.isEnd) return false;
			d.isEnd = false;
			return isEmpty(d);          // no children?
		}
		int i = k.charAt(j) - 'a';
		TrieNode c = d.children[i];
		if (c == null) return false;     // word not present
		if (delete(c, k, j + 1)) {
			d.children[i] = null;          // remove the child
			return !d.isEnd && isEmpty(d);
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
