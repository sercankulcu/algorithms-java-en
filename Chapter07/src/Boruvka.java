import java.util.ArrayList;
import java.util.List;

// Boruvka: in every round each component picks its cheapest outgoing edge and all of them are added together.
// The number of components at least halves each round: at most log V rounds, O(E log V).
public class Boruvka {

	static class Edge {
		int u, v, w;
		Edge(int u, int v, int w) { this.u = u; this.v = v; this.w = w; }
		public String toString() { return "" + NAMES.charAt(u) + NAMES.charAt(v) + "(" + w + ")"; }
	}

	static final String NAMES = "ABCDEF";

	static int find(int[] parent, int i) {
		if (parent[i] != i)
			parent[i] = find(parent, parent[i]); // path compression
		return parent[i];
	}

	// BORUVKA(G): the loop from the slides, with initialization and a return value added
	static List<Edge> boruvka(int V, List<Edge> edges) {
		int[] parent = new int[V];
		for (int i = 0; i < V; i++) parent[i] = i;
		List<Edge> tree = new ArrayList<>();
		int componentCount = V;
		while (componentCount > 1) {
			Edge[] cheapest = new Edge[V];
			for (Edge e : edges) {            // 1) find the cheapest edges
				int r1 = find(parent, e.u), r2 = find(parent, e.v);
				if (r1 == r2) continue;         // same component
				if (cheapest[r1] == null || e.w < cheapest[r1].w)
					cheapest[r1] = e;
				if (cheapest[r2] == null || e.w < cheapest[r2].w)
					cheapest[r2] = e;
			}
			boolean added = false;
			for (Edge e : cheapest) {         // 2) add them all
				if (e == null) continue;
				int r1 = find(parent, e.u), r2 = find(parent, e.v);
				if (r1 == r2) continue;         // already added
				tree.add(e);
				parent[r1] = r2;                // union
				componentCount--;
				added = true;
			}
			if (!added) break;  // forest
		}
		return tree;
	}

	public static void main(String[] args) {
		// Graph A-F, the edges are scanned in this order
		String[] uv = { "AB", "BC", "AD", "BD", "BE", "BF", "CF", "DE", "EF" };
		int[] w =     {  1,    5,    4,    7,    3,    6,    9,    8,    2  };
		List<Edge> edges = new ArrayList<>();
		for (int i = 0; i < uv.length; i++)
			edges.add(new Edge(NAMES.indexOf(uv[i].charAt(0)), NAMES.indexOf(uv[i].charAt(1)), w[i]));
		List<Edge> tree = boruvka(NAMES.length(), edges);
		System.out.println(tree);   // [AB(1), BC(5), AD(4), EF(2), BE(3)]
		int total = 0;
		for (Edge e : tree) total += e.w;
		System.out.println(total); // 15  (4 edges in round 1, BE in round 2)

		// Disconnected graph {A-B, C-D}: no edge is added in the second round, a minimum spanning forest is returned
		List<Edge> forestEdges = new ArrayList<>();
		forestEdges.add(new Edge(0, 1, 2));
		forestEdges.add(new Edge(2, 3, 5));
		System.out.println(boruvka(4, forestEdges));   // [AB(2), CD(5)]
	}
}

/* Expected output:
[AB(1), BC(5), AD(4), EF(2), BE(3)]
15
[AB(2), CD(5)]
*/
