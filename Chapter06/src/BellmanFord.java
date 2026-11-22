import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// Bellman-Ford: all edges are relaxed V - 1 times; works with negative edges, O(V * E).
// If an edge can still be relaxed in an extra checking pass, there is a negative cycle.
public class BellmanFord {

	static class Edge {
		int from, to, weight;
		Edge(int from, int to, int weight) { this.from = from; this.to = to; this.weight = weight; }
	}

	static final int INF = Integer.MAX_VALUE;
	static int V;
	static List<Edge> edges;
	static int[] lastDist;   // added to carry the result to main

	static void bellmanFord(int source) {
		int[] dist = new int[V];
		int[] prev = new int[V];
		lastDist = dist;                          // added
		Arrays.fill(dist, INF);
		dist[source] = 0;
		for (int i = 1; i < V; i++) {
			for (Edge e : edges) {
				int u = e.from, v = e.to, w = e.weight;
				if (dist[u] != INF
						&& dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					prev[v] = u;
				}
			}
		}
		for (Edge e : edges) { // check
			int u = e.from, v = e.to, w = e.weight;
			if (dist[u] != INF
					&& dist[u] + w < dist[v])
				throw new IllegalStateException(
						"Negative cycle");
		}
	}

	static void buildGraph(int vertexCount, int[][] list) {
		V = vertexCount;
		edges = new ArrayList<>();
		for (int[] e : list) edges.add(new Edge(e[0], e[1], e[2]));
	}

	static String format(int[] d) {
		StringBuilder s = new StringBuilder("[");
		for (int i = 0; i < d.length; i++)
			s.append(i > 0 ? ", " : "").append(d[i] == INF ? "inf" : String.valueOf(d[i]));
		return s.append("]").toString();
	}

	public static void main(String[] args) {
		// The edge list is relaxed in this order: (2,1,-2), (0,1,4), (0,2,5), (1,3,3)
		buildGraph(4, new int[][] { {2, 1, -2}, {0, 1, 4}, {0, 2, 5}, {1, 3, 3} });
		bellmanFord(0);
		System.out.println(format(lastDist));   // [0, 3, 5, 6]

		// Negative cycle: S -> A (2), A -> B (1), B -> C (-3), C -> A (1); S=0, A=1, B=2, C=3
		buildGraph(4, new int[][] { {0, 1, 2}, {1, 2, 1}, {2, 3, -3}, {3, 1, 1} });
		try {
			bellmanFord(0);
		} catch (IllegalStateException e) {
			System.out.println(e.getMessage());   // Negative cycle
		}
		System.out.println(format(lastDist));   // [0, -1, 1, -2]  (after V - 1 = 3 rounds)
	}
}

/* Expected output:
[0, 3, 5, 6]
Negative cycle
[0, -1, 1, -2]
*/
