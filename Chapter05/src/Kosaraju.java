import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Kosaraju: strongly connected components with two DFS passes, O(V + E).
// Pass 1 pushes vertices on a stack in finishing order; pass 2 searches the transpose graph from the popped vertices.
public class Kosaraju {

	static int n;
	static List<Integer>[] adj, rev;
	static boolean[] visited;
	static Deque<Integer> stack = new ArrayDeque<>();

	static void kosaraju() {
		for (int v = 0; v < n; v++)  // pass 1
			if (!visited[v]) dfs1(v);
		Arrays.fill(visited, false); // pass 2
		while (!stack.isEmpty()) {
			int v = stack.pop();
			if (!visited[v]) {   // new SCC
				dfs2(v); System.out.println(); }
		}
	}

	static void dfs1(int v) {
		visited[v] = true;
		for (int w : adj[v])
			if (!visited[w]) dfs1(w);
		stack.push(v);       // v finished
	}

	static void dfs2(int v) {     // DFS on the transpose graph
		visited[v] = true;
		System.out.print(v + " ");
		for (int w : rev[v])
			if (!visited[w]) dfs2(w);
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void buildGraph(int vertexCount, int[][] edges) {
		n = vertexCount;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) adj[e[0]].add(e[1]);
		rev = new List[n];
		for (int v = 0; v < n; v++)
			rev[v] = new ArrayList<>();
		for (int v = 0; v < n; v++)
			for (int w : adj[v])
				rev[w].add(v);       // edge v -> w becomes w -> v
		visited = new boolean[n];
	}

	public static void main(String[] args) {
		// Graph from the Tarjan trace: 0->1, 1->2, 2->0, 2->3, 3->4, 4->3
		buildGraph(5, new int[][] { {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3} });
		kosaraju();
		// 0 2 1
		// 3 4
	}
}

/* Expected output:
0 2 1
3 4
*/
