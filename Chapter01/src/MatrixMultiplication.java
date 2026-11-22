import java.util.Arrays;

// O(n^3) matrix multiplication: n^2 entries, each needs n multiplications.
public class MatrixMultiplication {

	static int[][] multiply(int[][] A, int[][] B) {
		if (B.length == 0 || (A.length > 0 && A[0].length != B.length))
			throw new IllegalArgumentException("dimension");
		int n = A.length, m = B[0].length, p = B.length;
		int[][] result = new int[n][m];
		for (int i = 0; i < n; i++)
			for (int j = 0; j < m; j++) {
				int entry = 0;
				for (int k = 0; k < p; k++)
					entry += A[i][k] * B[k][j];
				result[i][j] = entry;
			}
		return result;
	}

	public static void main(String[] args) {
		int[][] A = {{1, 2}, {3, 4}};
		int[][] B = {{5, 6}, {7, 8}};
		System.out.println(Arrays.deepToString(multiply(A, B)));   // [[19, 22], [43, 50]]
	}
}

/* Expected output:
[[19, 22], [43, 50]]
*/
