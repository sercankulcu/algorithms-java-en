import java.util.Arrays;

// O(n!) permutation generation: every element is brought to position k in turn, the rest is arranged recursively.
public class Permutation {

	static void swap(int[] arr, int i, int j) {
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}

	static void permute(int[] arr, int k) {
		if (k == arr.length) {           // one permutation is ready
			System.out.println(Arrays.toString(arr));
			return;
		}
		for (int i = k; i < arr.length; i++) {
			swap(arr, k, i);               // bring element i to position k
			permute(arr, k + 1);           // arrange the rest
			swap(arr, k, i);               // restore
		}
	}

	public static void main(String[] args) {
		permute(new int[]{1, 2, 3}, 0);
		// [1, 2, 3]
		// [1, 3, 2]
		// [2, 1, 3]
		// [2, 3, 1]
		// [3, 2, 1]
		// [3, 1, 2]
	}
}

/* Expected output:
[1, 2, 3]
[1, 3, 2]
[2, 1, 3]
[2, 3, 1]
[3, 2, 1]
[3, 1, 2]
*/
