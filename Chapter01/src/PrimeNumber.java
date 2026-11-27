// O(sqrt n) primality test: only the odd divisors 3, 5, 7, ... < sqrt(n) + 1 are tried.
public class PrimeNumber {

	static boolean isPrime(int n) {
		if (n <= 1)
			return false;
		else if (n == 2)
			return true;
		else if (n % 2 == 0)
			return false;
		int limit = (int) Math.sqrt(n) + 1;
		for (int i = 3; i < limit; i += 2)
			if (n % i == 0)
				return false;
		return true;
	}

	public static void main(String[] args) {
		System.out.println(isPrime(97));   // true (only 4 divisions: 3, 5, 7, 9)
		System.out.println(isPrime(1));    // false
		System.out.println(isPrime(2));    // true
		System.out.println(isPrime(91));   // false (7 * 13)
	}
}

/* Expected output:
true
false
true
false
*/
