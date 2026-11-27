// Ternary search trie (TST): every node holds a character and left (<), middle (=), right (>) links.
// Search O(L + log N) expected; memory 3 links per node.
public class TernarySearchTrie {

	static class TstNode {
		char c;
		TstNode left, mid, right;
		boolean end;   // does a word end here?

		TstNode(char c) {
			this.c = c;
		}
	}

	TstNode root;

	void insert(String s) {
		root = insert(root, s, 0);
	}

	TstNode insert(TstNode node, String s, int i) {
		char ch = s.charAt(i);
		if (node == null) node = new TstNode(ch);
		if (ch < node.c) node.left = insert(node.left, s, i);
		else if (ch > node.c) node.right = insert(node.right, s, i);
		else if (i < s.length() - 1) node.mid = insert(node.mid, s, i + 1);
		else node.end = true;
		return node;
	}

	boolean search(String s) {
		TstNode d = root;
		int i = 0;
		while (d != null) {
			char ch = s.charAt(i);
			if (ch < d.c) d = d.left;
			else if (ch > d.c) d = d.right;
			else if (i == s.length() - 1) return d.end;
			else { d = d.mid; i++; }
		}
		return false;
	}

	public static void main(String[] args) {
		TernarySearchTrie t = new TernarySearchTrie();
		for (String w : new String[] {"kar", "kal", "at", "ve", "kas"}) t.insert(w);
		System.out.println(t.search("kas"));  // true
		System.out.println(t.search("al"));   // false
		System.out.println(t.search("ka"));   // false
	}
}

/* Expected output:
true
false
false
*/
