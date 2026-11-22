import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.List;

// Tarjan: strongly connected components (SCC) with a single DFS, O(V + E).
// index = discovery order, low = low-link value; a vertex with low == index is the root of a component.
public class Tarjan {

	static int n, counter;
	static List<Integer>[] adj;
	static int[] index, low;          // index 0: undefined
	static boolean[] onStack;
	static Deque<Integer> stack = new ArrayDeque<>();

	// TARJAN_SCC: scc is started from every vertex whose index is undefined
	static void tarjan() {
		index = new int[n];
		low = new int[n];
		onStack = new boolean[n];
		counter = 0;
		for (int v = 0; v < n; v++)
			if (index[v] == 0)
				scc(v);
	}

	static void scc(int v) {
		index[v] = low[v] = ++counter;
		stack.push(v); onStack[v] = true;
		for (int w : adj[v]) {
			if (index[w] == 0) {
				scc(w);
				low[v] = Math.min(low[v], low[w]);
			} else if (onStack[w])
				low[v] = Math.min(low[v], index[w]);
		}
		if (low[v] == index[v]) {
			int w;
			do {
				w = stack.pop(); onStack[w] = false;
				System.out.print(w + " ");
			} while (w != v);
			System.out.println();
		}
	}

	@SuppressWarnings({"unchecked", "rawtypes"})
	static void buildGraph(int vertexCount, int[][] edges) {
		n = vertexCount;
		adj = new List[n];
		for (int v = 0; v < n; v++) adj[v] = new ArrayList<>();
		for (int[] e : edges) adj[e[0]].add(e[1]);
	}

	public static void main(String[] args) {
		// Trace graph: 0->1, 1->2, 2->0, 2->3, 3->4, 4->3 (adj[2] = [0, 3])
		buildGraph(5, new int[][] { {0, 1}, {1, 2}, {2, 0}, {2, 3}, {3, 4}, {4, 3} });
		tarjan();
		// 4 3
		// 2 1 0
		System.out.println(Arrays.toString(low));   // [1, 1, 1, 4, 4]
	}
}

/* Expected output:
4 3
2 1 0
[1, 1, 1, 4, 4]
*/
