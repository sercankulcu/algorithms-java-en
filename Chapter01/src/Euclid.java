// Euclid's algorithm: gcd(a, b) = gcd(b, a % b). The number of divisions is O(log min(a, b)).
public class Euclid {

	static int gcd(int a, int b) {
		if (b == 0)
			return a;
		return gcd(b, a % b);
	}

	public static void main(String[] args) {
		System.out.println(gcd(1599, 650));   // 13 (5 divisions)
		System.out.println(gcd(48, 18));      // 6
	}
}

/* Expected output:
13
6
*/
