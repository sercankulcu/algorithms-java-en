// Jump search: jump ahead in blocks of size sqrt(n), then search linearly inside the block.
// O(sqrt n) comparisons; the array must be sorted.
public class JumpSearch {

	static int jumpSearch(int[] arr, int element,
						  int n) { // n <= arr.length
		if (n == 0) return -1;
		int block = (int) Math.sqrt(n);
		int jump = block, position = 0;
		while (arr[Math.min(jump, n) - 1] < element) {
			position = jump; jump += block; // jump
			if (position >= n) return -1;
		}
		while (arr[position] < element) {  // linear
			position++;
			if (position == Math.min(jump, n)) return -1;
		}
		if (arr[position] == element) return position;
		return -1;
	}

	public static void main(String[] args) {
		int[] arr = {2, 9, 30, 32, 38, 47, 61, 69, 79, 81};
		System.out.println(jumpSearch(arr, 69, 10));   // 7
		System.out.println(jumpSearch(arr, 50, 10));   // -1
		System.out.println(jumpSearch(arr, 99, 10));   // -1
	}
}

/* Expected output:
7
-1
-1
*/
