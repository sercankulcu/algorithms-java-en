import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

// A* search: the open-list vertex with the smallest f = g + h is expanded.
// If h is admissible, the path found when the target is removed is shortest; with h = 0 it turns into Dijkstra.
public class AStar {

	static class Edge {
		int v; double w;
		Edge(int v, double w) { this.v = v; this.w = w; }
	}

	static final double INF = Double.POSITIVE_INFINITY;
	static List<Edge>[] adj;
	static double[] g, h;
	static int[] parent;

	static double aStar(int s, int t) {
		Arrays.fill(g, INF); g[s] = 0;
		Queue<Integer> open = new PriorityQueue<>(
			Comparator.comparingDouble(v -> g[v] + h[v]));
		open.add(s);
		while (!open.isEmpty()) {
			int u = open.poll();      // smallest f
			if (u == t) return g[t];  // goal
			for (Edge e : adj[u])
				if (g[u] + e.w < g[e.v]) {
					g[e.v] = g[u] + e.w; parent[e.v] = u;
					open.remove(e.v); open.add(e.v);
				}
		}
		return INF;
	}

	static final String NAMES = "sabcdet";   // s=0, a=1, b=2, c=3, d=4, e=5, t=6

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		int n = NAMES.length();
		adj = new List[n];
		for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
		String[] uv = { "sa", "ab", "bc", "ct", "sd", "de", "et" };
		double[] w =  { 1.5,  2,    3,    4,    2,    3,    2 };
		for (int i = 0; i < uv.length; i++) {          // undirected
			int u = NAMES.indexOf(uv[i].charAt(0)), v = NAMES.indexOf(uv[i].charAt(1));
			adj[u].add(new Edge(v, w[i]));
			adj[v].add(new Edge(u, w[i]));
		}
		g = new double[n];
		h = new double[] { 5, 4, 2, 4, 4.5, 2, 0 };    // estimated distance to the target
		parent = new int[n];

		int s = NAMES.indexOf('s'), t = NAMES.indexOf('t');
		System.out.println(aStar(s, t));   // 7.0
		StringBuilder path = new StringBuilder();
		for (int v = t; v != s; v = parent[v]) path.insert(0, " -> " + NAMES.charAt(v));
		System.out.println("s" + path);       // s -> d -> e -> t
		System.out.println(g[NAMES.indexOf('c')]);   // 6.5 (c stayed on the open list, never expanded)
	}
}

/* Expected output:
7.0
s -> d -> e -> t
6.5
*/
