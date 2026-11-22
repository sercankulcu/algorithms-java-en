import java.util.ArrayList;
import java.util.List;

// Storing a graph in memory: adjacency list, O(V + E) memory and setup.
// An undirected edge is added to both lists; the lists have total length 2E.
public class AdjacencyList {

	@SuppressWarnings({"unchecked", "rawtypes"})
	static List<Integer>[] build(int n, int[][] edges) {
		List<Integer>[] adj = new List[n];
		for (int v = 0; v < n; v++)
			adj[v] = new ArrayList<>();
		for (int[] e : edges) {
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);  // undirected: add the reverse too
		}
		return adj;
	}

	public static void main(String[] args) {
		// Undirected graph with four vertices: 0-1, 0-2, 1-2, 2-3
		int[][] edges = { {0, 1}, {0, 2}, {1, 2}, {2, 3} };
		List<Integer>[] adj = build(4, edges);
		for (int v = 0; v < adj.length; v++) {
			StringBuilder s = new StringBuilder(v + ":");
			for (int i = 0; i < adj[v].size(); i++)
				s.append(i == 0 ? " " : ", ").append(adj[v].get(i));
			System.out.println(s);
		}
		// 0: 1, 2
		// 1: 0, 2
		// 2: 0, 1, 3
		// 3: 2
	}
}

/* Expected output:
0: 1, 2
1: 0, 2
2: 0, 1, 3
3: 2
*/
