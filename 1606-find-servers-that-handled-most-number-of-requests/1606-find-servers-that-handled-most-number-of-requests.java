class Solution {
    public List<Integer> busiestServers(int k, int[] arrival, int[] load) {

        TreeSet<Integer> available = new TreeSet<>();

        // Initially, all servers are available
        for (int i = 0; i < k; i++) {
            available.add(i);
        }

        // Stores {finishTime, serverId}
        PriorityQueue<long[]> busy = new PriorityQueue<>(
            (a, b) -> Long.compare(a[0], b[0])
        );

        int[] count = new int[k];
        int maxRequests = 0;

        for (int i = 0; i < arrival.length; i++) {

            // 1. Release servers whose requests have finished
            while (!busy.isEmpty() &&
                   busy.peek()[0] <= arrival[i]) {

                long[] server = busy.poll();
                available.add((int) server[1]);
            }

            // 2. If no server is available, drop the request
            if (available.isEmpty()) {
                continue;
            }

            // 3. Find the first available server >= i % k
            int preferred = i % k;
            Integer server = available.ceiling(preferred);

            // 4. Circular wrapping: start from server 0
            if (server == null) {
                server = available.first();
            }

            // 5. Assign request to the selected server
            available.remove(server);

            count[server]++;
            maxRequests = Math.max(maxRequests, count[server]);

            // 6. Mark server as busy until finish time
            long finishTime = (long) arrival[i] + load[i];

            busy.offer(new long[]{finishTime, server});
        }

        // 7. Collect all busiest servers
        List<Integer> ans = new ArrayList<>();

        for (int i = 0; i < k; i++) {
            if (count[i] == maxRequests) {
                ans.add(i);
            }
        }

        return ans;
    }
}