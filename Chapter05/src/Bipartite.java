import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

// Bipartiteness test with BFS: the layers are colored with two colors, O(V + E).
// Two neighbors of the same color mean an odd-length cycle: the graph is not bipartite.
public class Bipartite {

	static int n;
	static List<Integer>[] adj;
	static int[] color;
	static Queue<Integer> queue;

	static boolean isBipartite(int s) {
		Arrays.fill(color, -1);
		color[s] = 0; queue.add(s);
		while (!queue.isEmpty()) {
			int v = queue.poll();
			for (int w : adj[v])
				if (color[w] == -1) {
					color[w] = 1 - color[v];
					queue.add(w);
				} else if (color[w] == color[v])
					return false;  // same color
		}
		return true;
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void buildGraph(int vertexCount, int[][] edges) {
		n = vertexCount;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) {       // undirected
			adj[e[0]].add(e[1]);
			adj[e[1]].add(e[0]);
		}
		color = new int[n];
		queue = new LinkedList<>();
	}

	public static void main(String[] args) {
		// cycle 0-1-3-2 and triangle 3-4-5
		buildGraph(6, new int[][] { {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5}, {4, 5} });
		System.out.println(isBipartite(0));   // false (4 and 5 have the same color)
		System.out.println(Arrays.toString(color));   // [0, 1, 1, 0, 1, 1]

		// Without the triangle edge 4-5 only an even-length cycle is left
		buildGraph(6, new int[][] { {0, 1}, {0, 2}, {1, 3}, {2, 3}, {3, 4}, {3, 5} });
		System.out.println(isBipartite(0));   // true
	}
}

/* Expected output:
false
[0, 1, 1, 0, 1, 1]
true
*/
