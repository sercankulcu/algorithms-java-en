// Floyd-Warshall: shortest paths between all pairs of vertices, dynamic programming, Theta(V^3).
// prev[i][j]: the vertex before j on a shortest path from i to j; d[i][i] < 0 means a negative cycle.
public class FloydWarshall {

	static final int INF = Integer.MAX_VALUE;
	static int V;
	static int[][] prev;

	// FLOYD_WARSHALL initialization: diagonal 0, the weight if there is an edge, infinity otherwise; prev[i][j] = i
	static int[][] initialize(int vertexCount, int[][] edges) {
		V = vertexCount;
		int[][] d = new int[V][V];
		prev = new int[V][V];
		for (int u = 0; u < V; u++)
			for (int v = 0; v < V; v++) {
				d[u][v] = (u == v) ? 0 : INF;
				prev[u][v] = u;
			}
		for (int[] e : edges)
			d[e[0]][e[1]] = e[2];
		return d;
	}

	static void floydWarshall(int[][] d) {  // V x V
		for (int k = 0; k < V; k++)       // via
			for (int i = 0; i < V; i++)     // from
				for (int j = 0; j < V; j++)   // to
					if (d[i][k] != INF
							&& d[k][j] != INF
							&& d[i][k] + d[k][j] < d[i][j]) {
						d[i][j] = d[i][k] + d[k][j];
						prev[i][j] = prev[k][j];
					}
		for (int i = 0; i < V; i++)
			if (d[i][i] < 0)
				throw new IllegalStateException(
						"Negative cycle");
	}

	static void print(int[][] d) {
		for (int[] row : d) {
			StringBuilder s = new StringBuilder();
			for (int x : row)
				s.append(String.format("%4s", x == INF ? "inf" : String.valueOf(x)));
			System.out.println(s);
		}
	}

	public static void main(String[] args) {
		// Code trace: 0->1 (3), 0->3 (7), 1->0 (8), 1->2 (2), 2->0 (5), 2->3 (1), 3->0 (2)
		int[][] d = initialize(4, new int[][] { {0, 1, 3}, {0, 3, 7}, {1, 0, 8}, {1, 2, 2},
												{2, 0, 5}, {2, 3, 1}, {3, 0, 2} });
		floydWarshall(d);
		print(d);
		//    0   3   5   6
		//    5   0   2   3
		//    3   6   0   1
		//    2   5   7   0
		System.out.println(prev[1][3] + " " + prev[1][2]);   // 2 1  (path 1 -> 2 -> 3)

		// The graph of Dijkstra Example 2 (vertices 1..5, here with indices 0..4)
		d = initialize(5, new int[][] { {4, 1, 4}, {4, 3, 2}, {3, 1, 1}, {3, 2, 1},
										{1, 0, 3}, {2, 3, 2}, {0, 2, 6}, {0, 3, 3} });
		floydWarshall(d);
		print(d);
		//    0   4   4   3 inf
		//    3   0   7   6 inf
		//    6   3   0   2 inf
		//    4   1   1   0 inf
		//    6   3   3   2   0
		System.out.println(prev[4][0] + 1);   // 2  (Pi[5][1] = 2: path 5 -> 4 -> 2 -> 1)
	}
}

/* Expected output:
   0   3   5   6
   5   0   2   3
   3   6   0   1
   2   5   7   0
2 1
   0   4   4   3 inf
   3   0   7   6 inf
   6   3   0   2 inf
   4   1   1   0 inf
   6   3   3   2   0
2
*/
