import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Breadth-first search (BFS): visits the graph layer by layer, O(V + E).
// The dist[] and parent[] arrays give shortest paths in an unweighted graph.
public class BFS {

	static int n;
	static List<Integer>[] adj;
	static int[] dist, parent;

	static void bfs(int s) {
		boolean[] visited = new boolean[n];
		Queue<Integer> queue = new LinkedList<>();
		visited[s] = true; queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v + " ");
			for (int w : adj[v])
				if (!visited[w]) {
					visited[w] = true;
					queue.add(w);
				}
		}
	}

	static void bfsDist(int s) {
		Arrays.fill(dist, -1);
		Arrays.fill(parent, -1); dist[s] = 0;
		Queue<Integer> queue = new LinkedList<>();
		queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : adj[v])
				if (dist[w] == -1) {  // new
					dist[w] = dist[v] + 1;
					parent[w] = v;
					queue.add(w);
				}
		}
	}

	static List<Integer> path(int[] parent, int s, int t) {
		LinkedList<Integer> path = new LinkedList<>();
		for (int v = t; v != -1; v = parent[v])
			path.addFirst(v);   // from the target back
		if (path.getFirst() != s)
			return null;       // t is unreachable
		return path;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void buildGraph(int vertexCount, int[][] edges, boolean undirected) {
		n = vertexCount;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) {
			adj[e[0]].add(e[1]);
			if (undirected) adj[e[1]].add(e[0]);
		}
		dist = new int[n];
		parent = new int[n];
	}

	public static void main(String[] args) {
		// the directed graph from the DFS trace
		int[][] directed = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 0},
							 {2, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		buildGraph(8, directed, false);
		bfs(0);
		System.out.println();   // 0 1 2 7 3 4 5 6

		// undirected tree numbered 1 to 8 (0 is unused): 1-2, 1-3, 1-4, 1-5, 2-6, 2-7, 3-8
		int[][] tree = { {1, 2}, {1, 3}, {1, 4}, {1, 5}, {2, 6}, {2, 7}, {3, 8} };
		buildGraph(9, tree, true);
		bfsDist(1);
		System.out.println(Arrays.toString(Arrays.copyOfRange(dist, 1, 9)));
		// [0, 1, 1, 1, 1, 2, 2, 2]
		System.out.println(Arrays.toString(Arrays.copyOfRange(parent, 1, 9)));
		// [-1, 1, 1, 1, 1, 2, 2, 3]
		System.out.println(path(parent, 1, 8));   // [1, 3, 8]
		System.out.println(path(parent, 1, 0));   // null (0 is unreachable)
	}
}

/* Expected output:
0 1 2 7 3 4 5 6
[0, 1, 1, 1, 1, 2, 2, 2]
[-1, 1, 1, 1, 1, 2, 2, 3]
[1, 3, 8]
null
*/
