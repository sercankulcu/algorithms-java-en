import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Kahn's algorithm: topological sort by queueing vertices with in-degree 0, O(V + E).
public class Kahn {

	static int n;
	static List<Integer>[] adj;

	static void kahn() {
		int[] indeg = new int[n];
		for (int v = 0; v < n; v++)
			for (int w : adj[v]) indeg[w]++;
		Queue<Integer> queue = new LinkedList<>();
		for (int v = 0; v < n; v++)
			if (indeg[v] == 0) queue.add(v);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			System.out.print(v + " ");
			for (int w : adj[v])
				if (--indeg[w] == 0) queue.add(w);
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	public static void main(String[] args) {
		// DAG: the graph from the DFS trace with the edge 2 -> 0 removed
		int[][] edges = { {0, 1}, {0, 2}, {0, 7}, {1, 2}, {2, 3},
						  {2, 4}, {3, 5}, {4, 6}, {5, 6} };
		n = 8;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) adj[e[0]].add(e[1]);
		kahn();
		System.out.println();   // 0 1 7 2 3 4 5 6
	}
}

/* Expected output:
0 1 7 2 3 4 5 6
*/
