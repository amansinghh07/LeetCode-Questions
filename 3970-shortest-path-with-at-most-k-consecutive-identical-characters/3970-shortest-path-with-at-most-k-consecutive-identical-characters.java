import java.util.*;

class Solution {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        // adjacency list via arrays
        int m = edges.length;
        int[] head = new int[n];
        Arrays.fill(head, -1);
        int[] nxt = new int[m], to = new int[m], wt = new int[m];
        for (int i = 0; i < m; i++) {
            int u = edges[i][0];
            to[i] = edges[i][1];
            wt[i] = edges[i][2];
            nxt[i] = head[u];
            head[u] = i;
        }

        char[] c = labels.toCharArray();
        int K = k + 1; // run in [1..k]
        int[] dist = new int[n * K];
        Arrays.fill(dist, Integer.MAX_VALUE);

        // encode (dist, state) into one long: dist in high bits
        PriorityQueue<Long> pq = new PriorityQueue<>();
        dist[0 * K + 1] = 0;
        pq.add((long) 0 << 32 | (0 * K + 1));

        while (!pq.isEmpty()) {
            long top = pq.poll();
            int d = (int) (top >>> 32);
            int state = (int) (top & 0xFFFFFFFFL);
            if (d > dist[state]) continue;

            int u = state / K;
            int run = state % K;
            if (u == n - 1) return d; // first time popping any state of target is optimal

            for (int e = head[u]; e != -1; e = nxt[e]) {
                int v = to[e];
                int nr = (c[v] == c[u]) ? run + 1 : 1;
                if (nr > k) continue;
                int ns = v * K + nr;
                int nd = d + wt[e];
                if (nd < dist[ns]) {
                    dist[ns] = nd;
                    pq.add((long) nd << 32 | ns);
                }
            }
        }
        return -1;
    }
}