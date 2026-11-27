import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Dinic: BFS builds the level graph, a level-restricted DFS sends a blocking flow.
// At most V - 1 phases; with the next[] (resume position) array a phase takes O(V * E), in total O(V^2 * E).
public class Dinic {

	static class Edge {
		int to, cap, flow, rev;
		Edge(int to, int cap, int rev) {
			this.to = to; this.cap = cap; this.rev = rev;
		}
	}

	static int V;
	static List<Edge>[] adj;
	static int[] level;

	// The forward edge goes to u's list, the backward edge with capacity 0 to v's list
	static void addEdge(int u, int v, int c) {
		adj[u].add(new Edge(v, c, adj[v].size()));
		adj[v].add(new Edge(u, 0, adj[u].size() - 1));
	}

	// BFS: levels along edges with positive residual capacity; was the sink reached?
	static boolean bfs(int src, int sink) {
		Arrays.fill(level, -1);
		level[src] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(src);
		while (!queue.isEmpty()) {
			int u = queue.poll();
			for (Edge e : adj[u])
				if (level[e.to] < 0 && e.cap - e.flow > 0) {
					level[e.to] = level[u] + 1;
					queue.add(e.to);
				}
		}
		return level[sink] >= 0;
	}

	static int dfs(int u, int t, int flow, int[] next) {
		if (u == t) return flow;
		for (; next[u] < adj[u].size(); next[u]++) {
			Edge e = adj[u].get(next[u]);
			if (level[e.to] == level[u] + 1 && e.flow < e.cap) {
				int d = dfs(e.to, t, Math.min(flow, e.cap - e.flow), next);
				if (d > 0) {
					e.flow += d;                           // forward edge
					adj[e.to].get(e.rev).flow -= d;        // backward edge
					return d;
				}
			}
		}
		return 0;   // no level path left from u to t
	}

	// DINIC: while a level graph can be built, send a blocking flow
	static int dinic(int source, int sink) {
		int maxFlow = 0, phase = 0;
		while (bfs(source, sink)) {
			int[] next = new int[V];              // reset in every phase
			int phaseFlow = 0, delta;
			while ((delta = dfs(source, sink, Integer.MAX_VALUE, next)) > 0)
				phaseFlow += delta;
			maxFlow += phaseFlow;
			System.out.println("Phase " + (++phase) + ": +" + phaseFlow + ", next = "
					+ Arrays.toString(next) + ", |f| = " + maxFlow);
		}
		return maxFlow;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		// s=0, 1..4, t=5; edges are added in this order (the order in the adjacency lists determines the next[] trace)
		V = 6;
		adj = new List[V];
		for (int i = 0; i < V; i++) adj[i] = new ArrayList<>();
		level = new int[V];
		int[][] edges = { {0, 1, 10}, {0, 2, 10}, {1, 3, 4}, {1, 2, 2}, {1, 4, 8},
						  {2, 4, 9}, {4, 3, 6}, {3, 5, 10}, {4, 5, 10} };
		for (int[] e : edges) addEdge(e[0], e[1], e[2]);
		System.out.println(dinic(0, 5));
		// Phase 1: +14, next = [2, 3, 3, 2, 4, 0], |f| = 14   ({s,1,3,t} 4, {s,1,4,t} 6, {s,2,4,t} 4)
		// Phase 2: +5, next = [2, 4, 3, 2, 2, 0], |f| = 19    ({s,2,4,3,t} 5)
		// 19
	}
}

/* Expected output:
Phase 1: +14, next = [2, 3, 3, 2, 4, 0], |f| = 14
Phase 2: +5, next = [2, 4, 3, 2, 2, 0], |f| = 19
19
*/
