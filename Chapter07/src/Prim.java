import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.PriorityQueue;

// Prim: the tree grows from one vertex; at each step the vertex joined to the tree by the cheapest edge is added.
// O(V^2) with an adjacency matrix; O(E log V) with a lazy priority queue.
public class Prim {

	static class Edge {
		int v, w;
		Edge(int v, int w) { this.v = v; this.w = w; }
	}

	static final int INF = Integer.MAX_VALUE;
	static int[] lastParent;      // added to carry the result of prim(G, src) to main

	// Among the vertices not in the tree, the one with the smallest key (the smaller index on a tie)
	static int minKey(int[] key, boolean[] inTree) {
		int min = Integer.MAX_VALUE, u = -1;
		for (int v = 0; v < key.length; v++)
			if (!inTree[v] && key[v] < min) {
				min = key[v];
				u = v;
			}
		return u;
	}

	static void prim(int[][] G, int src) {
		int V = G.length;
		int[] key = new int[V];
		int[] parent = new int[V];
		boolean[] inTree = new boolean[V];
		Arrays.fill(key, Integer.MAX_VALUE);
		key[src] = 0;
		parent[src] = -1;
		for (int s = 0; s < V - 1; s++) {
			int u = minKey(key, inTree);
			inTree[u] = true;
			for (int v = 0; v < V; v++)
				if (G[u][v] != 0 && !inTree[v]
						&& G[u][v] < key[v]) {
					key[v] = G[u][v];
					parent[v] = u;
				}
		}
		lastParent = parent;   // added
	}

	// Fields used by the priority queue version (vertices 1..V)
	static List<List<Edge>> adj;
	static int[] key, parent;
	static boolean[] inTree;

	static void primPQ(int src) {
		Arrays.fill(key, INF);
		key[src] = 0;
		PriorityQueue<int[]> pq =
				new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { src, 0 });
		while (!pq.isEmpty()) {
			int u = pq.poll()[0];
			if (inTree[u]) continue;  // stale
			inTree[u] = true;
			for (Edge e : adj.get(u))
				if (!inTree[e.v] && e.w < key[e.v]) {
					key[e.v] = e.w; parent[e.v] = u;
					pq.add(new int[] { e.v, e.w });
				}
		}
	}

	public static void main(String[] args) {
		// Adjacency matrix of graph A-F (0: no edge), source B (index 1)
		//              A  B  C  D  E  F
		int[][] G = { { 0, 1, 0, 3, 0, 0 },    // A
					  { 1, 0, 3, 2, 6, 5 },    // B
					  { 0, 3, 0, 0, 4, 4 },    // C
					  { 3, 2, 0, 0, 3, 0 },    // D
					  { 0, 6, 4, 3, 0, 2 },    // E
					  { 0, 5, 4, 0, 2, 0 } };  // F
		prim(G, 1);
		int total = 0;
		for (int v = 0; v < G.length; v++)
			if (lastParent[v] != -1) {
				System.out.print((char) ('A' + lastParent[v]) + "" + (char) ('A' + v) + " ");
				total += G[lastParent[v]][v];
			}
		System.out.println("w(T) = " + total);   // BA BC BD DE EF w(T) = 11

		// Undirected graph with five vertices (1..5), source 1
		int V = 5;
		adj = new ArrayList<>();
		for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
		int[][] list = { {1, 3, 2}, {2, 5, 2}, {1, 2, 3}, {2, 3, 4}, {3, 4, 4}, {1, 4, 6} };
		for (int[] e : list) {
			adj.get(e[0]).add(new Edge(e[1], e[2]));
			adj.get(e[1]).add(new Edge(e[0], e[2]));
		}
		key = new int[V + 1];
		parent = new int[V + 1];
		inTree = new boolean[V + 1];
		primPQ(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(key, 1, V + 1)));   // [0, 3, 2, 4, 2]
		System.out.println(Arrays.toString(Arrays.copyOfRange(parent, 2, V + 1)));       // [1, 1, 3, 2]
		// MST: 1-2 (3), 1-3 (2), 3-4 (4), 2-5 (2); total 11
	}
}

/* Expected output:
BA BC BD DE EF w(T) = 11
[0, 3, 2, 4, 2]
[1, 1, 3, 2]
*/
