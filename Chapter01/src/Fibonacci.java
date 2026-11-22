// O(2^n) plain recursive Fibonacci and the O(n) memoized version.
public class Fibonacci {

	static final int N = 92;            // largest n that fits in a long

	static long fib(int n) {           // plain recursion
		if (n <= 1) return n;
		return fib(n - 1) + fib(n - 2);
	}

	static long[] memo = new long[N + 1]; // 0: not computed yet
	static long fibMemo(int n) {       // memoization
		if (n <= 1) return n;
		if (memo[n] == 0)
			memo[n] = fibMemo(n - 1) + fibMemo(n - 2);
		return memo[n];
	}

	public static void main(String[] args) {
		System.out.println(fib(10));          // 55
		System.out.println(fib(30));          // 832040
		System.out.println(fibMemo(50));      // 12586269025
		System.out.println(fibMemo(90));      // 2880067194370816120
		System.out.println(fibMemo(92));      // 7540113804746346429 (largest F(n) that fits in a long)
	}
}

/* Expected output:
55
832040
12586269025
2880067194370816120
7540113804746346429
*/
