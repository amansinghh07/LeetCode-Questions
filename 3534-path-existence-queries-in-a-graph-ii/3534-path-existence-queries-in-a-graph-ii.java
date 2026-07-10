class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;

        Arrays.sort(order, (a, b) -> Integer.compare(nums[a], nums[b]));

        int[] values = new int[n];
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            values[i] = nums[order[i]];
            pos[order[i]] = i;
        }

        // next[]
        int[] next = new int[n];
        int r = 0;
        for (int l = 0; l < n; l++) {
            while (r + 1 < n && values[r + 1] - values[l] <= maxDiff) {
                r++;
            }
            next[l] = r;
        }

        // connected components
        int[] comp = new int[n];
        int id = 0;
        comp[0] = 0;
        for (int i = 1; i < n; i++) {
            if (next[i - 1] < i) id++;
            comp[i] = id;
        }

        int LOG = 17;
        while ((1 << LOG) <= n) LOG++;

        int[][] up = new int[LOG][n];
        up[0] = next;

        for (int k = 1; k < LOG; k++) {
            for (int i = 0; i < n; i++) {
                up[k][i] = up[k - 1][up[k - 1][i]];
            }
        }

        int[] ans = new int[queries.length];

        for (int qi = 0; qi < queries.length; qi++) {

            int u = pos[queries[qi][0]];
            int v = pos[queries[qi][1]];

            if (u > v) {
                int t = u;
                u = v;
                v = t;
            }

            if (u == v) {
                ans[qi] = 0;
                continue;
            }

            if (comp[u] != comp[v]) {
                ans[qi] = -1;
                continue;
            }

            int cur = u;
            int jumps = 0;

            for (int k = LOG - 1; k >= 0; k--) {
                if (up[k][cur] < v) {
                    cur = up[k][cur];
                    jumps += 1 << k;
                }
            }

            ans[qi] = jumps + 1;
        }

        return ans;
    }
}