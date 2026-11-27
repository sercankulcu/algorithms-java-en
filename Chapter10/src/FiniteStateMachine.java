// DFA simulation: one table lookup per character; the accepting state is checked at the end.
// Time O(n), no backtracking. Alphabet {0, 1}.
public class FiniteStateMachine {

	static boolean accepts(int[][] delta, boolean[] accept,
			String s) {
		int state = 0;                     // start state
		for (char c : s.toCharArray())
			state = delta[state][c - '0'];    // single step
		return accept[state];              // is the final state accepting?
	}

	public static void main(String[] args) {
		// strings that "end in 1": q0 -> 0, q1 -> 1; F = {q1}
		int[][] g1 = {{0, 1}, {0, 1}};
		boolean[] k1 = {false, true};
		System.out.println(accepts(g1, k1, "1011"));  // true
		System.out.println(accepts(g1, k1, "10"));    // false

		// Binary alphabet example: A=0, B=1, C=2, D=3 (accepting), E=4, F=5
		int[][] g2 = {
			{4, 1},   // A: 0 -> E, 1 -> B
			{1, 2},   // B: 0 -> B, 1 -> C
			{1, 3},   // C: 0 -> B, 1 -> D
			{3, 0},   // D: 0 -> D, 1 -> A
			{4, 5},   // E: 0 -> E, 1 -> F
			{5, 0}    // F: 0 -> F, 1 -> A
		};
		boolean[] k2 = {false, false, false, true, false, false};
		System.out.println(accepts(g2, k2, "10011"));  // true
	}
}

/* Expected output:
true
false
true
*/
