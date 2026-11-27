// Longest-prefix match with a binary trie (IP routing): the deepest route seen along the path wins.
// Time O(address length).
public class BinaryTrie {

	static class BitNode {
		BitNode[] children = new BitNode[2];  // '0', '1'
		String route;                      // route attached to this prefix (null if none)
	}

	BitNode root = new BitNode();

	// prefix* -> route entry
	void addRoute(String prefix, String route) {
		BitNode node = root;
		for (char b : prefix.toCharArray()) {
			int i = b - '0';
			if (node.children[i] == null) node.children[i] = new BitNode();
			node = node.children[i];
		}
		node.route = route;
	}

	String longestMatch(String addr) {
		BitNode d = root;
		String best = null;
		for (int k = 0; k < addr.length(); k++) {
			if (d.route != null) best = d.route;  // longer prefix
			d = d.children[addr.charAt(k) - '0'];
			if (d == null) return best;
		}
		return d.route != null ? d.route : best;
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
