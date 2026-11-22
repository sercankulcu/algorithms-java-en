// Longest-prefix match with a binary trie (IP routing): the deepest route seen along the path wins.
// Time O(address length).
public class BinaryTrie {

	static class BitNode {
		BitNode[] child = new BitNode[2];  // '0', '1'
		String route;                      // route attached to this prefix (null if none)
	}

	BitNode root = new BitNode();

	// prefix* -> route entry
	void addRoute(String prefix, String route) {
		BitNode node = root;
		for (char b : prefix.toCharArray()) {
			int i = b - '0';
			if (node.child[i] == null) node.child[i] = new BitNode();
			node = node.child[i];
		}
		node.route = route;
	}

	String longestMatch(String addr) {
		BitNode node = root;
		String best = null;
		for (int k = 0; k < addr.length(); k++) {
			if (node.route != null) best = node.route;  // longer prefix
			node = node.child[addr.charAt(k) - '0'];
			if (node == null) return best;
		}
		return node.route != null ? node.route : best;
	}

	public static void main(String[] args) {
		BinaryTrie t = new BinaryTrie();
		t.addRoute("0", "A");
		t.addRoute("01", "B");
		t.addRoute("011", "C");
		t.addRoute("1", "D");
		t.addRoute("110", "E");
		System.out.println(t.longestMatch("0110"));  // C
		System.out.println(t.longestMatch("1001"));  // D
		System.out.println(t.longestMatch("1101"));  // E
	}
}

/* Expected output:
C
D
E
*/
