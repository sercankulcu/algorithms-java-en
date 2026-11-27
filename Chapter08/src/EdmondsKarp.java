import java.util.LinkedList;
import java.util.Queue;

// Edmonds-Karp: the BFS version of Ford-Fulkerson; each time the augmenting path with the fewest edges is chosen.
// The running time does not depend on the capacities: O(V * E^2).
public class EdmondsKarp {

	static int V;
	static int[][] res;   // residual capacity matrix

	static boolean bfs(int src, int sink, int[] parent) {
		boolean[] visited = new boolean[V];
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		visited[src] = true;
		parent[src] = -1;
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (int v = 0; v < V; v++)
				if (!visited[v] && res[u][v] > 0) {
					queue.add(v);
					parent[v] = u;
					visited[v] = true;
				}
		}
		return visited[sink];
	}

	static int edmondsKarp(int s, int t) {
		int maxFlow = 0;
		int[] parent = new int[V];
		while (bfs(s, t, parent)) {
			int flow = Integer.MAX_VALUE;
			for (int v = t; v != s; v = parent[v])
				flow = Math.min(flow, res[parent[v]][v]);
			for (int v = t; v != s; v = parent[v]) {
				int u = parent[v];
				res[u][v] -= flow;  // forward
				res[v][u] += flow;  // backward
			}
			maxFlow += flow;
		}
		return maxFlow;
	}

	public static void main(String[] args) {
		// s=0, 1..4, t=5
		V = 6;
		res = new int[V][V];
		int[][] edges = { {0, 1, 16}, {0, 2, 13}, {1, 2, 10}, {2, 1, 4}, {1, 3, 12},
						  {3, 2, 9}, {2, 4, 14}, {4, 3, 7}, {3, 5, 20}, {4, 5, 4} };
		for (int[] e : edges) res[e[0]][e[1]] = e[2];

		int[] parent = new int[V];
		System.out.println(bfs(0, 5, parent) + " " + parent[5] + " " + parent[3] + " " + parent[1]);
		// true 3 1 0  (the first path is s -> 1 -> 3 -> t, bottleneck 12)

		System.out.println(edmondsKarp(0, 5));
		// 23  (s13t +12, s24t +4, s243t +7)
	}
}

/* Expected output:
true 3 1 0
23
*/
