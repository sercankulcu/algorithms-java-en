import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

// Depth-first search (DFS): recursive and stack versions, O(V + E).
// The stack version pushes neighbors in order, so the visiting order comes out reversed.
public class DFS {

	static int n;
	static List<Integer>[] adj;

	static void dfs(int v, boolean[] visited) {
		visited[v] = true;
		System.out.print(v + " ");
		for (int w : adj[v])
			if (!visited[w])
				dfs(w, visited);
	}

	static void dfsStack(int s) {
		boolean[] visited = new boolean[n];
		Deque<Integer> stack = new ArrayDeque<>();
		stack.push(s);
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited[v]) {
				visited[v] = true;
				System.out.print(v + " ");
				for (int w : adj[v])
					stack.push(w);
			}
		}
	}

	// Directed graph: edges are written to the adjacency lists in the order they are added
	@SuppressWarnings({"unchecked", "rawtypes"})
	static void buildGraph(int vertexCount, int[][] edges) {
		n = vertexCount;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) adj[e[0]].add(e[1]);
	}

	public static void main(String[] args) {
		// Trace graph: adj[0] = [1, 2, 7], adj[2] = [0, 3, 4]; 6 and 7 have no outgoing edges
		int[][] edges = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 0},
						  {2, 3}, {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		buildGraph(8, edges);

		dfs(0, new boolean[n]);
		System.out.println();   // 0 1 2 3 5 6 4 7

		dfsStack(0);
		System.out.println();   // 0 7 2 4 6 3 5 1
	}
}

/* Expected output:
0 1 2 3 5 6 4 7
0 7 2 4 6 3 5 1
*/
