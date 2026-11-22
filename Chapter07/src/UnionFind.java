import java.util.Arrays;

// Union-Find (disjoint sets): find reaches the root with path compression, union links two roots.
// With path compression the amortized cost of a sequence of operations is almost constant.
public class UnionFind {

	static int find(int[] parent, int i) {
		if (parent[i] != i)
			parent[i] = find(parent, parent[i]); // path compression
		return parent[i];
	}

	static void union(int[] parent, int x, int y) {
		parent[find(parent, x)] = find(parent, y);
	}

	public static void main(String[] args) {
		int[] parent = { 0, 1, 2, 3, 4 };   // every element is in its own set
		union(parent, 0, 1);
		union(parent, 1, 2);
		union(parent, 2, 3);
		System.out.println(Arrays.toString(parent));   // [1, 2, 3, 3, 4]  (chain 0 -> 1 -> 2 -> 3)
		System.out.println(find(parent, 0));           // 3
		System.out.println(Arrays.toString(parent));   // [3, 3, 3, 3, 4]  (path compressed)
		union(parent, 4, 0);
		System.out.println(Arrays.toString(parent));   // [3, 3, 3, 3, 3]
	}
}

/* Expected output:
[1, 2, 3, 3, 4]
3
[3, 3, 3, 3, 4]
[3, 3, 3, 3, 3]
*/
