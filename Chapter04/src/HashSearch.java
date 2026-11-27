// Search with hashing: the key goes straight to its bucket, average O(1).
// Collisions are resolved by linear probing; worst case O(n) when keys cluster.
public class HashSearch {

	static int hash(String key,
					int m) {
		int sum = 0;
		for (char k : key.toCharArray())
			sum += k;           // Unicode code
		return sum % m;       // m: table size
	}

	// Helper: insertion with linear probing (while the table is not full)
	static void hashInsert(Integer[] table, int key) {
		int m = table.length;     // m > 0
		int idx = Math.floorMod(key, m);
		while (table[idx] != null)
			idx = (idx + 1) % m;
		table[idx] = key;
	}

	static int hashSearch(Integer[] table, int key) {
		int m = table.length;     // m > 0
		int idx = Math.floorMod(key, m);   // hash
		while (table[idx] != null) {
			if (table[idx] == key)
				return idx;          // found
			idx = (idx + 1) % m; // next bucket
		}
		return -1;                 // empty bucket: not present
	}

	public static void main(String[] args) {
		System.out.println(hash("veli", 10));   // 2 (sum 432)
		System.out.println(hash("ali", 10));    // 0
		System.out.println(hash("ila", 10));    // 0 (collides with ali)

		Integer[] table = new Integer[10];
		int[] keys = {16, 12, 45, 29, 6, 122, 5, 58, 75};
		for (int x : keys)
			hashInsert(table, x);
		System.out.println(java.util.Arrays.toString(table));
		// [58, 75, 12, 122, null, 45, 16, 6, 5, 29]
		System.out.println(hashSearch(table, 75));   // 1 (7 probes)
		System.out.println(hashSearch(table, 14));   // -1 (bucket 4 is empty)
	}
}

/* Expected output:
2
0
0
[58, 75, 12, 122, null, 45, 16, 6, 5, 29]
1
-1
*/
