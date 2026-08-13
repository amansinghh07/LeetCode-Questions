class Solution {

    static class Node {
        int len, prefix, suffix, best;
        char leftChar, rightChar;

        Node() {}

        Node(char c) {
            len = prefix = suffix = best = 1;
            leftChar = rightChar = c;
        }
    }

    private char[] arr;
    private Node[] tree;

    public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
        int n = s.length();
        arr = s.toCharArray();
        tree = new Node[4 * n];

        build(1, 0, n - 1);

        int k = queryIndices.length;
        int[] ans = new int[k];

        for (int i = 0; i < k; i++) {
            update(1, 0, n - 1, queryIndices[i], queryCharacters.charAt(i));
            ans[i] = tree[1].best;
        }

        return ans;
    }

    // Build segment tree
    private void build(int idx, int l, int r) {
        if (l == r) {
            tree[idx] = new Node(arr[l]);
            return;
        }

        int mid = (l + r) / 2;
        build(idx * 2, l, mid);
        build(idx * 2 + 1, mid + 1, r);

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    // Point update
    private void update(int idx, int l, int r, int pos, char c) {
        if (l == r) {
            arr[pos] = c;
            tree[idx] = new Node(c);
            return;
        }

        int mid = (l + r) / 2;

        if (pos <= mid) {
            update(idx * 2, l, mid, pos, c);
        } else {
            update(idx * 2 + 1, mid + 1, r, pos, c);
        }

        tree[idx] = merge(tree[idx * 2], tree[idx * 2 + 1]);
    }

    // Merge two nodes
    private Node merge(Node a, Node b) {
        Node res = new Node();

        res.len = a.len + b.len;
        res.leftChar = a.leftChar;
        res.rightChar = b.rightChar;

        // prefix
        res.prefix = a.prefix;
        if (a.prefix == a.len && a.rightChar == b.leftChar) {
            res.prefix = a.len + b.prefix;
        }

        // suffix
        res.suffix = b.suffix;
        if (b.suffix == b.len && a.rightChar == b.leftChar) {
            res.suffix = b.len + a.suffix;
        }

        // best
        res.best = Math.max(a.best, b.best);
        if (a.rightChar == b.leftChar) {
            res.best = Math.max(res.best, a.suffix + b.prefix);
        }

        return res;
    }
}