
import java.util.*;

class Solution {

    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long sumLeft = 0;
    long sumRight = 0;

    int leftSize = 0;
    int rightSize = 0;

    // Add an element to a multiset
    private void addLeft(int num) {
        left.put(num, left.getOrDefault(num, 0) + 1);
        sumLeft += num;
        leftSize++;
    }

    private void addRight(int num) {
        right.put(num, right.getOrDefault(num, 0) + 1);
        sumRight += num;
        rightSize++;
    }

    // Remove one occurrence
    private void removeLeft(int num) {
        int count = left.get(num);

        if (count == 1) {
            left.remove(num);
        } else {
            left.put(num, count - 1);
        }

        sumLeft -= num;
        leftSize--;
    }

    private void removeRight(int num) {
        int count = right.get(num);

        if (count == 1) {
            right.remove(num);
        } else {
            right.put(num, count - 1);
        }

        sumRight -= num;
        rightSize--;
    }

    // Rebalance both halves
    private void balance() {

        while (leftSize > rightSize + 1) {
            int num = left.lastKey();

            removeLeft(num);
            addRight(num);
        }

        while (leftSize < rightSize) {
            int num = right.firstKey();

            removeRight(num);
            addLeft(num);
        }
    }

    private void add(int num) {

        if (left.isEmpty() || num <= left.lastKey()) {
            addLeft(num);
        } else {
            addRight(num);
        }

        balance();
    }

    private void remove(int num) {

        if (left.containsKey(num)) {
            removeLeft(num);
        } else {
            removeRight(num);
        }

        balance();
    }

    // Cost of making all elements equal
    // Median = maximum element in left
    private long getCost() {

        long median = left.lastKey();

        return median * leftSize - sumLeft
             + sumRight - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {

        int n = nums.length;

        long[] cost = new long[n - x + 1];

        // Calculate cost of every window of size x
        for (int i = 0; i < n; i++) {

            add(nums[i]);

            if (i >= x) {
                remove(nums[i - x]);
            }

            if (i >= x - 1) {
                cost[i - x + 1] = getCost();
            }
        }

        // DP
        long INF = Long.MAX_VALUE / 4;

        long[][] dp = new long[k + 1][n + 1];

        for (int j = 1; j <= k; j++) {
            Arrays.fill(dp[j], INF);
        }

        // dp[0][i] = 0
        for (int i = 1; i <= n; i++) {

            for (int j = 1; j <= k; j++) {

                // Skip current element
                dp[j][i] = dp[j][i - 1];

                // Select window ending at i-1
                if (i >= x && dp[j - 1][i - x] != INF) {

                    dp[j][i] = Math.min(
                        dp[j][i],
                        dp[j - 1][i - x] + cost[i - x]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
