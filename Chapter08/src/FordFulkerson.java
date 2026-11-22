// Ford-Fulkerson: flow equal to the bottleneck is sent along augmenting paths found by DFS in the residual network.
// O(E * |f*|) for integer capacities; backward edges allow earlier flow to be undone.
public class FordFulkerson {

	static int V;
	static int[][] residual;   // residual capacity matrix; capacities at the start, backward edges 0

	static boolean dfs(int u, int sink,
					   boolean[] visited, int[] parent) {
		visited[u] = true;
		if (u == sink) return true;
		for (int v = 0; v < V; v++) {
			if (!visited[v] && residual[u][v] > 0) {
				parent[v] = u;
				if (dfs(v, sink, visited, parent))
					return true;
			}
		}
		return false;   // no path to the sink from this branch
	}

	// FORD_FULKERSON: find a path until no augmenting path is left, send the bottleneck amount
	static int fordFulkerson(int source, int sink, String names) {
		int maxFlow = 0;
		int[] parent = new int[V];
		while (dfs(source, sink, new boolean[V], parent)) {
			int delta = Integer.MAX_VALUE;       // smallest residual capacity on the path
			for (int v = sink; v != source; v = parent[v])
				delta = Math.min(delta, residual[parent[v]][v]);
			StringBuilder path = new StringBuilder();
			for (int v = sink; v != source; v = parent[v]) {
				int u = parent[v];
				residual[u][v] -= delta;
				residual[v][u] += delta;           // backward edge
				path.insert(0, " " + names.charAt(v));
			}
			maxFlow += delta;
			System.out.println(names.charAt(source) + "" + path + "  +" + delta + "  |f| = " + maxFlow);
		}
		return maxFlow;
	}

	static void buildNetwork(int vertexCount, int[][] edges) {
		V = vertexCount;
		residual = new int[V][V];
		for (int[] e : edges) residual[e[0]][e[1]] = e[2];
	}

	public static void main(String[] args) {
		// Example 1: S=0, A=1, B=2, C=3, D=4, T=5; neighbors are tried in the order S, A, B, C, D, T
		buildNetwork(6, new int[][] { {0, 1, 4}, {0, 3, 3}, {1, 2, 4}, {2, 3, 3},
									  {2, 5, 2}, {3, 4, 6}, {4, 5, 6} });
		System.out.println(fordFulkerson(0, 5, "SABCDT"));
		// S A B C D T  +3  |f| = 3
		// S A B T  +1  |f| = 4
		// S C B T  +1  |f| = 5   (A is a dead end; backward edges C -> B and B -> A)
		// S C D T  +2  |f| = 7
		// 7

		// Example 2: S=0, 1..4, T=5
		buildNetwork(6, new int[][] { {0, 1, 10}, {0, 3, 10}, {1, 2, 4}, {1, 3, 2}, {1, 4, 8},
									  {3, 4, 9}, {4, 2, 6}, {2, 5, 10}, {4, 5, 10} });
		System.out.println(fordFulkerson(0, 5, "S1234T"));
		// S 1 2 T  +4  |f| = 4
		// S 1 3 4 2 T  +2  |f| = 6
		// S 1 4 2 T  +4  |f| = 10
		// S 3 1 4 T  +2  |f| = 12   (backward edge 3 -> 1)
		// S 3 4 T  +7  |f| = 19
		// 19  (min cut {S, 3}: 10 + 9)
	}
}

/* Expected output:
S A B C D T  +3  |f| = 3
S A B T  +1  |f| = 4
S C B T  +1  |f| = 5
S C D T  +2  |f| = 7
7
S 1 2 T  +4  |f| = 4
S 1 3 4 2 T  +2  |f| = 6
S 1 4 2 T  +4  |f| = 10
S 3 1 4 T  +2  |f| = 12
S 3 4 T  +7  |f| = 19
19
*/
