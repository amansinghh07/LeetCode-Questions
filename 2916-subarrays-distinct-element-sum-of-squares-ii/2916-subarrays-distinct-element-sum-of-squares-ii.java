
class Solution {
    private static final long MOD = 1_000_000_007L;

    private long[] sum;
    private long[] sumSq;
    private long[] lazy;
    private int n;

    public int sumCounts(int[] nums) {
        n = nums.length;

        sum = new long[4 * n];
        sumSq = new long[4 * n];
        lazy = new long[4 * n];

        int[] last = new int[100001];
        java.util.Arrays.fill(last, -1);

        long ans = 0;

        for (int i = 0; i < n; i++) {
            int x = nums[i];
            int left = last[x] + 1;

            // Increment distinct counts for starts in [left, i].
            update(1, 0, n - 1, left, i);

            // Sum of squared distinct counts for all subarrays
            // ending at index i.
            ans = (ans + query(1, 0, n - 1, 0, i)) % MOD;

            last[x] = i;
        }

        return (int) ans;
    }

    private void apply(int node, int start, int end) {
        long len = end - start + 1L;

        sumSq[node] = (
            sumSq[node] + 2L * sum[node] + len
        ) % MOD;

        sum[node] = (sum[node] + len) % MOD;
        lazy[node] = (lazy[node] + 1) % MOD;
    }

    private void push(int node, int start, int end) {
        if (lazy[node] == 0 || start == end) {
            return;
        }

        int mid = start + (end - start) / 2;
        long delta = lazy[node];

        applyDelta(node * 2, start, mid, delta);
        applyDelta(node * 2 + 1, mid + 1, end, delta);

        lazy[node] = 0;
    }

    private void applyDelta(
        int node, int start, int end, long delta
    ) {
        long len = end - start + 1L;

        sumSq[node] = (
            sumSq[node]
            + 2L * delta % MOD * sum[node] % MOD
            + delta * delta % MOD * len % MOD
        ) % MOD;

        sum[node] = (sum[node] + delta * len) % MOD;
        lazy[node] = (lazy[node] + delta) % MOD;
    }

    private void update(
        int node, int start, int end, int left, int right
    ) {
        if (right < start || end < left) {
            return;
        }

        if (left <= start && end <= right) {
            apply(node, start, end);
            return;
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        update(node * 2, start, mid, left, right);
        update(node * 2 + 1, mid + 1, end, left, right);

        sum[node] = (sum[node * 2] + sum[node * 2 + 1]) % MOD;

        sumSq[node] = (
            sumSq[node * 2] + sumSq[node * 2 + 1]
        ) % MOD;
    }

    private long query(
        int node, int start, int end, int left, int right
    ) {
        if (right < start || end < left) {
            return 0;
        }

        if (left <= start && end <= right) {
            return sumSq[node];
        }

        push(node, start, end);

        int mid = start + (end - start) / 2;

        long a = query(node * 2, start, mid, left, right);
        long b = query(node * 2 + 1, mid + 1, end, left, right);

        return (a + b) % MOD;
    }
}
