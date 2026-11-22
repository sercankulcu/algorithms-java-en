import java.util.ArrayList;
import java.util.List;

// Reverse delete: edges are tried in descending weight order; an edge whose removal does not disconnect the graph is deleted.
// One connectivity check per edge: O(E * (V + E)).
public class ReverseDelete {

	static class Edge {
		int u, v, w;
		Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public String toString() { return "" + NAMES.charAt(u) + NAMES.charAt(v) + "(" + w + ")"; }
	}

	static final String NAMES = "ABCDEFG";

	static int find(int[] parent, int i) {
		if (parent[i] != i)
			parent[i] = find(parent, parent[i]); // path compression
		return parent[i];
	}

	// Are all vertices in one component using the remaining edges? (disjoint sets are rebuilt from scratch)
	static boolean isConnected(int V, List<Edge> edges) {
		int[] parent = new int[V];
		for (int i = 0; i < V; i++) parent[i] = i;
		int components = V;
		for (Edge e : edges) {
			int x = find(parent, e.u), y = find(parent, e.v);
			if (x != y) { parent[x] = y; components--; }
		}
		return components == 1;
	}

	static void reverseDelete(int V, List<Edge> edges) {
		edges.sort((a, b) -> b.w - a.w); // descending
		int i = 0;
		while (i < edges.size()) {
			Edge e = edges.remove(i);     // delete tentatively
			if (!isConnected(V, edges)) {
				edges.add(i, e);            // put back
				i++;
			}
		}
	}

	public static void main(String[] args) {
		// A-G: 7 vertices, 11 edges, total 90
		String[] uv = { "AB", "AD", "BC", "BD", "BE", "CE", "DE", "DF", "EF", "EG", "FG" };
		int[] w =     {  7,    5,    8,    9,    7,    5,    15,   6,    8,    9,    11  };
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < uv.length; i++)
			edges.add(new Edge(NAMES.indexOf(uv[i].charAt(0)), NAMES.indexOf(uv[i].charAt(1)), w[i]));
		reverseDelete(NAMES.length(), edges);
		System.out.println(edges);     // [EG(9), AB(7), BE(7), DF(6), AD(5), CE(5)]
		int total = 0;
		for (Edge e : edges) total += e.w;
		System.out.println(total); // 39
	}
}

/* Expected output:
[EG(9), AB(7), BE(7), DF(6), AD(5), CE(5)]
39
*/
