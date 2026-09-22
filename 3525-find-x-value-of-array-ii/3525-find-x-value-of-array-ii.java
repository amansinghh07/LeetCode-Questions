class Solution {

    static class Node {
        int prod;
        int[] cnt;

        Node(int k) {
            cnt = new int[k];
        }
    }

    int k;
    Node[] tree;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;

        int n = nums.length;

        tree = new Node[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int q = 0; q < queries.length; q++) {

            int index = queries[q][0];
            int value = queries[q][1];
            int start = queries[q][2];
            int x = queries[q][3];

            // Persistent update
            update(1, 0, n - 1, index, value);

            // Query [start, n-1]
            Node result = query(1, 0, n - 1, start, n - 1);

            ans[q] = result.cnt[x];
        }

        return ans;
    }

    // ---------------------------------------------------------
    // Build
    // ---------------------------------------------------------

    private void build(int node, int left, int right, int[] nums) {

        if (left == right) {
            tree[node] = new Node(k);

            int rem = nums[left] % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        build(node * 2, left, mid, nums);
        build(node * 2 + 1, mid + 1, right, nums);

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // Merge
    // ---------------------------------------------------------

    private Node merge(Node a, Node b) {

        Node result = new Node(k);

        // Product of entire segment
        result.prod = (a.prod * b.prod) % k;

        // Prefixes completely inside A
        for (int r = 0; r < k; r++) {
            result.cnt[r] += a.cnt[r];
        }

        // Prefixes that contain all of A
        // and then some prefix of B
        for (int r = 0; r < k; r++) {

            if (b.cnt[r] == 0) {
                continue;
            }

            int newRemainder = (a.prod * r) % k;

            result.cnt[newRemainder] += b.cnt[r];
        }

        return result;
    }

    // ---------------------------------------------------------
    // Update
    // ---------------------------------------------------------

    private void update(
            int node,
            int left,
            int right,
            int index,
            int value) {

        if (left == right) {

            tree[node] = new Node(k);

            int rem = value % k;

            tree[node].prod = rem;
            tree[node].cnt[rem] = 1;

            return;
        }

        int mid = left + (right - left) / 2;

        if (index <= mid) {
            update(node * 2, left, mid, index, value);
        } else {
            update(node * 2 + 1, mid + 1, right, index, value);
        }

        tree[node] = merge(tree[node * 2], tree[node * 2 + 1]);
    }

    // ---------------------------------------------------------
    // Query
    // ---------------------------------------------------------

    private Node query(
            int node,
            int left,
            int right,
            int queryLeft,
            int queryRight) {

        // Completely inside query range
        if (queryLeft <= left && right <= queryRight) {
            return tree[node];
        }

        int mid = left + (right - left) / 2;

        // Completely in right half
        if (queryLeft > mid) {
            return query(
                    node * 2 + 1,
                    mid + 1,
                    right,
                    queryLeft,
                    queryRight
            );
        }

        // Completely in left half
        if (queryRight <= mid) {
            return query(
                    node * 2,
                    left,
                    mid,
                    queryLeft,
                    queryRight
            );
        }

        // Overlap both sides
        Node a = query(
                node * 2,
                left,
                mid,
                queryLeft,
                queryRight
        );

        Node b = query(
                node * 2 + 1,
                mid + 1,
                right,
                queryLeft,
                queryRight
        );

        return merge(a, b);
    }
}