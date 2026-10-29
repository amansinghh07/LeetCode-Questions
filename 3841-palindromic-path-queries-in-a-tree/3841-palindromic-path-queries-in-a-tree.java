
import java.util.*;

class Solution {
    List<Integer>[] adj;
    int[][] up;
    int[] depth, tin, tout, parent, prefix;
    int timer, LOG;
    char[] chars;
    Fenwick bit;

    class Fenwick {
        int[] tree;
        int n;

        Fenwick(int n) {
            this.n = n;
            tree = new int[n + 2];
        }

        void update(int i, int val) {
            for (; i <= n; i += i & -i) {
                tree[i] ^= val;
            }
        }

        int query(int i) {
            int res = 0;
            for (; i > 0; i -= i & -i) {
                res ^= tree[i];
            }
            return res;
        }

        void rangeUpdate(int l, int r, int val) {
            update(l, val);
            update(r + 1, val);
        }
    }

    public List<Boolean> palindromePath(int n, int[][] edges,
                                        String s, String[] queries) {
        chars = s.toCharArray();
        adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        LOG = 1;
        while ((1 << LOG) <= n) LOG++;

        up = new int[LOG][n];
        depth = new int[n];
        tin = new int[n];
        tout = new int[n];
        parent = new int[n];
        prefix = new int[n];

        Arrays.fill(parent, -1);

        // Iterative DFS to avoid stack overflow.
        int[] stack = new int[n];
        int[] next = new int[n];
        int top = 0;

        stack[0] = 0;
        parent[0] = 0;
        prefix[0] = 1 << (chars[0] - 'a');
        tin[0] = ++timer;

        while (top >= 0) {
            int u = stack[top];

            if (next[top] < adj[u].size()) {
                int v = adj[u].get(next[top]++);

                if (v == parent[u]) continue;

                parent[v] = u;
                depth[v] = depth[u] + 1;
                prefix[v] = prefix[u] ^
                            (1 << (chars[v] - 'a'));

                tin[v] = ++timer;
                stack[++top] = v;
                next[top] = 0;
            } else {
                tout[u] = timer;
                top--;
            }
        }

        // Binary lifting table.
        for (int i = 0; i < n; i++) {
            up[0][i] = parent[i];
        }

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        bit = new Fenwick(n);
        List<Boolean> ans = new ArrayList<>();

        for (String q : queries) {
            String[] p = q.split(" ");

            if (p[0].equals("update")) {
                int u = Integer.parseInt(p[1]);
                char c = p[2].charAt(0);

                int oldMask = 1 << (chars[u] - 'a');
                int newMask = 1 << (c - 'a');
                int delta = oldMask ^ newMask;

                bit.rangeUpdate(tin[u], tout[u], delta);
                chars[u] = c;

            } else {
                int u = Integer.parseInt(p[1]);
                int v = Integer.parseInt(p[2]);

                int lca = getLCA(u, v);

                int maskU = prefix[u] ^ bit.query(tin[u]);
                int maskV = prefix[v] ^ bit.query(tin[v]);

                int mask = maskU ^ maskV ^
                           (1 << (chars[lca] - 'a'));

                ans.add((mask & (mask - 1)) == 0);
            }
        }

        return ans;
    }

    private int getLCA(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u;
            u = v;
            v = temp;
        }

        int diff = depth[u] - depth[v];

        for (int k = 0; k < LOG; k++) {
            if ((diff & (1 << k)) != 0) {
                u = up[k][u];
            }
        }

        if (u == v) return u;

        for (int k = LOG - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }

        return parent[u];
    }
}
