// Run-length encoding (RLE): consecutive equal characters are written as "count + character".
// Time O(n): every character is read once.
public class RLE {

	static String rleEncode(String s) {
		StringBuilder result = new StringBuilder();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			int count = 1;
			while (i + 1 < n &&
				s.charAt(i + 1) == c) {
				count++;
				i++;
			}
			result.append(count).append(c);
		}
		return result.toString();
	}

	public static void main(String[] args) {
		System.out.println(rleEncode("AAAAABBBCCCCDDD"));  // 5A3B4C3D
		System.out.println(rleEncode("AAAAABB"));          // 5A2B
	}
}

/* Expected output:
5A3B4C3D
5A2B
*/
