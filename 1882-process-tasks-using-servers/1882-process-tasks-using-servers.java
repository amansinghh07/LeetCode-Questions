class Solution {
    public int[] assignTasks(int[] servers, int[] tasks) {

        int n = servers.length;
        int m = tasks.length;

        // Free servers: minimum weight, then minimum index
        PriorityQueue<Integer> free = new PriorityQueue<>((a, b) -> {
            if (servers[a] != servers[b]) {
                return Integer.compare(servers[a], servers[b]);
            }
            return Integer.compare(a, b);
        });

        // Busy servers: earliest available time
        // [availableTime, serverIndex]
        PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) ->
            Long.compare(a[0], b[0])
        );

        // Initially, all servers are free
        for (int i = 0; i < n; i++) {
            free.offer(i);
        }

        int[] ans = new int[m];
        long time = 0;

        for (int i = 0; i < m; i++) {

            // Task i arrives at second i
            time = Math.max(time, i);

            // Release all servers that have finished
            while (!busy.isEmpty() && busy.peek()[0] <= time) {
                long[] server = busy.poll();
                free.offer((int) server[1]);
            }

            // If no server is free, jump to the earliest
            // time when a server becomes available
            if (free.isEmpty()) {
                time = busy.peek()[0];

                while (!busy.isEmpty() && busy.peek()[0] <= time) {
                    long[] server = busy.poll();
                    free.offer((int) server[1]);
                }
            }

            // Select the best available server
            int serverIndex = free.poll();

            ans[i] = serverIndex;

            // Mark server as busy
            busy.offer(new long[]{
                time + tasks[i],
                serverIndex
            });
        }

        return ans;
    }
}