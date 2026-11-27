import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Kruskal: edges are tried in ascending weight order; an edge that closes no cycle enters the MST.
// Union-Find does the cycle check; sorting dominates, O(E log E).
public class Kruskal {

	static class Edge implements Comparable<Edge> {
		int u, v, w;
		Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public int compareTo(Edge o) { return Integer.compare(w, o.w); }
		public String toString() { return "(" + u + "," + v + ")=" + w; }
	}

	static List<Edge> lastTree;   // added to carry the result to main

	static int find(int[] parent, int i) {
		if (parent[i] != i)
			parent[i] = find(parent, parent[i]); // path compression
		return parent[i];
	}

	static void kruskal(int V, List<Edge> es) {
		Collections.sort(es);      // ascending
		int[] parent = new int[V + 1];
		for (int i = 1; i <= V; i++) parent[i] = i;
		List<Edge> tree = new ArrayList<>();
		int k = 0;
		while (tree.size() < V - 1) {
			Edge e = es.get(k++);
			int x = find(parent, e.u);
			int y = find(parent, e.v);
			if (x != y) {               // different trees
				tree.add(e);
				parent[x] = y;            // union
			}
		}
		lastTree = tree;              // added
	}

	public static void main(String[] args) {
		// Graph with five vertices (1..5)
		List<Edge> edges = new ArrayList<>();
		int[][] list = { {1, 3, 2}, {2, 5, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 4}, {1, 4, 6} };
		for (int[] e : list) edges.add(new Edge(e[0], e[1], e[2]));
		kruskal(5, edges);
		System.out.println(lastTree);   // [(1,3)=2, (2,5)=2, (1,2)=3, (3,4)=4]
		int total = 0;
		for (Edge e : lastTree) total += e.w;
		System.out.println(total);    // 11  ((2,3) is skipped because it closes a cycle, (1,4) is never examined)
	}
}

/* Expected output:
[(1,3)=2, (2,5)=2, (1,2)=3, (3,4)=4]
11
*/
