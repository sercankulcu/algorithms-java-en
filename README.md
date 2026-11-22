# Algorithms in Java

Java code for the undergraduate **Algorithms** course at Giresun University, Department of Computer Engineering. Each `ChapterNN/src` folder holds chapter NN of the course as small, runnable programs. Class, method and variable names are the ones used in the English edition of the course (lecture notes and slides), so each program can be read side by side with them. Pseudocode from the notes is completed into working Java with the same names.

Course page, lecture notes, slides and study questions: <https://sercankulcu.github.io/teaching/algorithms/?lang=en>

The same programs with Turkish identifiers, for the Turkish edition of the course, are in [algorithms-java](https://github.com/sercankulcu/algorithms-java). The course continues in **Advanced Algorithms** (divide and conquer, greedy algorithms, dynamic programming, backtracking, branch and bound, approximation, randomized, online and parallel algorithms); its code is in [advanced-algorithms-java](https://github.com/sercankulcu/advanced-algorithms-java).

## Chapters

| Folder | Chapter | Programs |
|---|---|---|
| `Chapter01` | Introduction | Average of three numbers, step counting (O(1), O(n)), primality test, matrix multiplication, permutations, Fibonacci (plain and memoized), Euclid's algorithm |
| `Chapter02` | Algorithm Complexity | Growth of the complexity functions f1–f6, running-time measurement and step counting for linear search |
| `Chapter03` | Sorting Algorithms | Bubble, insertion, selection and Shell sort; merge sort, quicksort (with the pseudocode `PARTITION`/`QUICKSORT`), heapsort, counting sort, radix sort, Timsort |
| `Chapter04` | Searching Algorithms | Linear search with a sentinel, binary search (iterative, recursive, first occurrence), hash-table search with linear probing, interpolation, jump and exponential search |
| `Chapter05` | Graph Traversal | Adjacency lists, BFS with shortest paths, recursive and stack-based DFS, bipartite test, Kahn's topological sort, strongly connected components (Tarjan, Kosaraju) |
| `Chapter06` | Shortest Paths | Dijkstra with path reconstruction, Bellman-Ford with negative-cycle detection, Floyd-Warshall, A* |
| `Chapter07` | Minimum Spanning Trees | Union-find with path compression, Kruskal, Prim (matrix and priority queue), Borůvka, reverse-delete |
| `Chapter08` | Network Flow | Ford-Fulkerson, Edmonds-Karp, Dinic, push-relabel |
| `Chapter09` | String Matching and Compression | Brute force, KMP, Boyer-Moore, Horspool, Rabin-Karp; RLE, LZW, LZ78 |
| `Chapter10` | Advanced String Algorithms | Lexicographic and LSD radix string sorting, regular expressions, finite-state machines, pattern automata, number parsing, Levenshtein distance, suffix array, Burrows-Wheeler transform |
| `Chapter11` | Tries | Trie (wildcard search, autocomplete, delete), prefix counter, binary trie with longest-prefix match, ternary search trie, suffix trie |

## Running the examples

Requires **JDK 21**.

Every file is a standalone program with a `main` method in the default package, and no program waits for keyboard input. To compile and run one chapter:

```bash
cd Chapter03/src
javac -d ../bin *.java
java -cp ../bin QuickSort
```

Each chapter folder is also an Eclipse project (File → Import → Existing Projects into Workspace).

Some examples keep the Turkish words, names and cities used in the course (for instance the trie words *kar*, *kalem*, *kas*), because the results shown in the notes depend on them.

## Automatic check

Every program ends with its expected output in a `/* Expected output: … */` comment. `python ci/run_examples.py` compiles every chapter, runs every program and compares its output with that block; the GitHub Actions workflow runs it on every push. A program whose output changes from run to run (timings) says so above its expected output and only has to run.

```bash
python ci/run_examples.py              # every chapter
python ci/run_examples.py Chapter04    # one chapter
```

## Contact

For questions or suggestions, contact [Sercan Külcü](https://github.com/sercankulcu) or open an issue on GitHub.
