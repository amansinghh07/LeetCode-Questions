class Solution {

    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {

        int n = online.length;

        List<int[]>[] graph = new ArrayList[n];
        List<Integer>[] topoGraph = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            graph[i] = new ArrayList<>();
            topoGraph[i] = new ArrayList<>();
        }

        int[] indegree = new int[n];

        int maxEdge = 0;

        for (int[] e : edges) {
            graph[e[0]].add(new int[]{e[1], e[2]});
            topoGraph[e[0]].add(e[1]);
            indegree[e[1]]++;
            maxEdge = Math.max(maxEdge, e[2]);
        }

        // Topological order
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (indegree[i] == 0) q.offer(i);
        }

        List<Integer> topo = new ArrayList<>();

        while (!q.isEmpty()) {
            int u = q.poll();
            topo.add(u);

            for (int v : topoGraph[u]) {
                if (--indegree[v] == 0)
                    q.offer(v);
            }
        }

        int lo = 0;
        int hi = maxEdge;
        int ans = -1;

        while (lo <= hi) {

            int mid = lo + (hi - lo) / 2;

            if (can(mid, graph, topo, online, k, n)) {
                ans = mid;
                lo = mid + 1;
            } else {
                hi = mid - 1;
            }
        }

        return ans;
    }

    private boolean can(int limit,
                        List<int[]>[] graph,
                        List<Integer> topo,
                        boolean[] online,
                        long k,
                        int n) {

        long INF = Long.MAX_VALUE / 4;

        long[] dist = new long[n];
        Arrays.fill(dist, INF);

        dist[0] = 0;

        for (int u : topo) {

            if (dist[u] == INF)
                continue;

            // Intermediate offline nodes cannot be used.
            if (u != 0 && u != n - 1 && !online[u])
                continue;

            for (int[] edge : graph[u]) {

                int v = edge[0];
                int cost = edge[1];

                if (cost < limit)
                    continue;

                if (v != n - 1 && !online[v])
                    continue;

                if (dist[v] > dist[u] + cost) {
                    dist[v] = dist[u] + cost;
                }
            }
        }

        return dist[n - 1] <= k;
    }
}