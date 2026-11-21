
class Solution {

    List<Integer>[] graph;
    List<Integer>[] newGraph;
    int[] newParent;
    int[] last;
    int[] ans;

    public int[] findSubtreeSizes(int[] parent, String s) {

        int n = parent.length;

        graph = new ArrayList[n];
        newGraph = new ArrayList[n];

        newParent = new int[n];
        last = new int[26];
        ans = new int[n];

        Arrays.fill(last, -1);

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            newGraph[i] = new ArrayList<>();
        }

        // Step 1: Build original tree
        for (int i = 1; i < n; i++) {
            graph[parent[i]].add(i);
        }

        // Step 2: Find new parents
        dfs(0, parent, s);

        // Step 3: Build modified tree
        for (int i = 1; i < n; i++) {
            newGraph[newParent[i]].add(i);
        }

        // Step 4: Calculate subtree sizes
        calculate(0);

        return ans;
    }

    void dfs(int u, int[] parent, String s) {

        int ch = s.charAt(u) - 'a';

        int previous = last[ch];

        if (previous != -1) {
            newParent[u] = previous;
        } else {
            newParent[u] = parent[u];
        }

        last[ch] = u;

        for (int v : graph[u]) {
            dfs(v, parent, s);
        }

        // Backtracking
        last[ch] = previous;
    }

    int calculate(int u) {

        int size = 1;

        for (int v : newGraph[u]) {
            size += calculate(v);
        }

        ans[u] = size;

        return size;
    }
}
