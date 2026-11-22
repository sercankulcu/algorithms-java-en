import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;
import java.util.PriorityQueue;

// Dijkstra: single-source shortest paths in a graph with non-negative weights.
// With a priority queue and lazy deletion it takes O((V + E) log V); the path is rebuilt from prev[].
public class Dijkstra {

	static class Edge {
		int to, weight;
		Edge(int to, int weight) { this.to = to; this.weight = weight; }
	}

	static final int INF = Integer.MAX_VALUE;
	static int V;                          // vertices 1..V, index 0 is unused
	static List<List<Edge>> adj;
	static int[] lastDist, lastPrev;       // added to carry the result to main

	static void dijkstra(int source) {
		int[] dist = new int[V + 1];
		int[] prev = new int[V + 1];
		boolean[] visited = new boolean[V + 1];
		Arrays.fill(dist, INF);
		dist[source] = 0;
		PriorityQueue<int[]> pq =
			new PriorityQueue<>((a, b) -> a[1] - b[1]);
		pq.add(new int[] { source, 0 });
		while (!pq.isEmpty()) {
			int u = pq.poll()[0];
			if (visited[u]) continue; // stale entry
			visited[u] = true;
			for (Edge e : adj.get(u)) {
				int v = e.to, w = e.weight;
				if (!visited[v]
						&& dist[u] + w < dist[v]) {
					dist[v] = dist[u] + w;
					prev[v] = u;
					pq.add(new int[] { v, dist[v] });
				}
			}
		}
		lastDist = dist; lastPrev = prev;   // added
	}

	static void printPath(int[] prev, int[] dist,
						  int source, int target) {
		if (dist[target] == INF) {   // unreachable
			System.out.print("no path"); return;
		}
		Deque<Integer> stack = new ArrayDeque<>();
		for (int v = target; v != source; v = prev[v])
			stack.push(v);         // from the target back
		stack.push(source);
		while (!stack.isEmpty())
			System.out.print(stack.pop() + " ");
	}

	static void buildGraph(int vertexCount, int[][] edges, boolean undirected) {
		V = vertexCount;
		adj = new ArrayList<>();
		for (int i = 0; i <= V; i++) adj.add(new ArrayList<>());
		for (int[] e : edges) {
			adj.get(e[0]).add(new Edge(e[1], e[2]));
			if (undirected) adj.get(e[1]).add(new Edge(e[0], e[2]));
		}
	}

	public static void main(String[] args) {
		// Example 2: directed graph, source 5
		int[][] example2 = { {5, 2, 4}, {5, 4, 2}, {4, 2, 1}, {4, 3, 1},
							 {2, 1, 3}, {3, 4, 2}, {1, 3, 6}, {1, 4, 3} };
		buildGraph(5, example2, false);
		dijkstra(5);
		System.out.println(Arrays.toString(Arrays.copyOfRange(lastDist, 1, 6)));
		// [6, 3, 3, 2, 0]
		System.out.println(Arrays.toString(Arrays.copyOfRange(lastPrev, 1, 6)));
		// [2, 4, 4, 5, 0]  (the source has no predecessor: 0)
		printPath(lastPrev, lastDist, 5, 1);
		System.out.println();   // 5 4 2 1

		// Example 1: undirected graph, source 1
		int[][] example1 = { {1, 2, 7}, {1, 3, 9}, {1, 6, 14}, {2, 3, 10}, {2, 4, 15},
							 {3, 4, 11}, {3, 6, 2}, {4, 5, 6}, {5, 6, 9} };
		buildGraph(6, example1, true);
		dijkstra(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(lastDist, 1, 7)));
		// [0, 7, 9, 20, 20, 11]
		printPath(lastPrev, lastDist, 1, 5);
		System.out.println();   // 1 3 6 5

		// Unreachable target: in a directed graph with 3 vertices no edge leads to 3
		buildGraph(3, new int[][] { {1, 2, 4} }, false);
		dijkstra(1);
		printPath(lastPrev, lastDist, 1, 3);
		System.out.println();   // no path
	}
}

/* Expected output:
[6, 3, 3, 2, 0]
[2, 4, 4, 5, 0]
5 4 2 1
[0, 7, 9, 20, 20, 11]
1 3 6 5
no path
*/
