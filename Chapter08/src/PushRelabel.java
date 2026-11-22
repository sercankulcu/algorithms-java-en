import java.util.ArrayList;
import java.util.List;

// Push-relabel: start with a preflow; excess flow is pushed from higher to lower vertices.
// A vertex that cannot push gets a greater height; the generic version takes O(V^2 * E).
public class PushRelabel {

	static class Node {
		String name;
		int height, excess;
		List<Edge> edges = new ArrayList<>();
		Node(String name) { this.name = name; }
	}

	static class Edge {
		Node to;
		int cap, flow;
		Edge reverse;         // backward edges are kept with capacity 0, their flow becomes negative
		Edge(Node to, int cap) { this.to = to; this.cap = cap; }
	}

	static void addEdge(Node u, Node v, int c) {
		Edge forward = new Edge(v, c), backward = new Edge(u, 0);
		forward.reverse = backward; backward.reverse = forward;
		u.edges.add(forward);
		v.edges.add(backward);
	}

	static void updateReverse(Edge e, int flow) {
		e.reverse.flow -= flow;
	}

	static boolean push(Node n) {
		for (Edge e : n.edges) {
			if (n.height > e.to.height
					&& e.flow != e.cap) {
				int flow = Math.min(
					e.cap - e.flow, n.excess);
				n.excess -= flow;
				e.to.excess += flow;
				e.flow += flow;
				updateReverse(e, flow);
				return true;   // pushed
			}
		}
		return false;
	}

	static void relabel(Node n) {
		int minH = Integer.MAX_VALUE;
		for (Edge e : n.edges) {
			// only edges in the residual network
			if (e.flow != e.cap
					&& e.to.height < minH) {
				minH = e.to.height;
				n.height = minH + 1;
			}
		}
	}

	// PUSH_RELABEL: preflow, then while an active vertex remains: if (!push(n)) relabel(n)
	static int pushRelabel(List<Node> nodes, Node source, Node sink) {
		source.height = nodes.size();
		for (Edge e : source.edges) {          // preflow: saturate the source edges
			int c = e.cap;
			e.flow = c;
			updateReverse(e, c);
			e.to.excess += c;
			source.excess -= c;
		}
		while (true) {
			Node n = null;                       // active vertex: excess > 0, other than source and sink
			for (Node x : nodes)
				if (x != source && x != sink && x.excess > 0) { n = x; break; }
			if (n == null) break;
			if (!push(n)) relabel(n);
		}
		return sink.excess;
	}

	public static void main(String[] args) {
		String[] names = { "s", "A", "B", "C", "D", "t" };
		List<Node> nodes = new ArrayList<>();
		for (String a : names) nodes.add(new Node(a));
		Node s = nodes.get(0), A = nodes.get(1), B = nodes.get(2),
			 C = nodes.get(3), D = nodes.get(4), t = nodes.get(5);
		addEdge(s, A, 13); addEdge(s, B, 10); addEdge(B, A, 3);
		addEdge(A, C, 6);  addEdge(A, t, 7);  addEdge(C, D, 10);
		addEdge(D, t, 5);

		System.out.println(pushRelabel(nodes, s, t));   // 12  (7 + 5 entering t)
		System.out.println(-s.excess);                  // 12  (net outflow from the source: 9 + 3)
	}
}

/* Expected output:
12
12
*/
