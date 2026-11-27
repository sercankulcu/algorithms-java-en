// Number parsing with an FSM: the numbers in a text are extracted using the states START, DIGIT, DECIMAL.
// Time O(n). Flaw: a trailing dot is part of the number too ("5.").
public class NumberParsing {

	enum State { START, DIGIT, DECIMAL }

	static void findNumbers(String s) {
		State d = State.START;
		StringBuilder buf = new StringBuilder();
		for (char c : s.toCharArray()) {
			boolean digit = Character.isDigit(c);
			if (digit) {
				if (d == State.START) d = State.DIGIT;
				buf.append(c);
			} else if (c == '.' && d != State.DECIMAL) {
				d = State.DECIMAL;
				buf.append(c);
			} else if (d != State.START) {  // number ended
				System.out.println(buf);
				buf.setLength(0);
				d = State.START;
			}
		}
		if (d != State.START) System.out.println(buf);
	}

	public static void main(String[] args) {
		findNumbers("$20.99 and 5.");
		// 20.99
		// 5.
	}
}

/* Expected output:
20.99
5.
*/
