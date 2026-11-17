
class Solution {
    public int minimumChanges(String s, int k) {

        int n = s.length();
        int INF = 1000000000;

        // Step 1: Precompute minimum cost of every substring
        int[][] cost = new int[n][n + 1];

        for (int start = 0; start < n; start++) {
            for (int end = start + 2; end <= n; end++) {

                int len = end - start;
                int minChanges = INF;

                // Try every proper divisor
                for (int d = 1; d < len; d++) {

                    if (len % d != 0) {
                        continue;
                    }

                    int changes = 0;

                    // Process all d groups
                    for (int offset = 0; offset < d; offset++) {

                        int left = start + offset;

                        int right = start + offset
                                + ((len - 1 - offset) / d) * d;

                        // Compare symmetric characters
                        while (left < right) {

                            if (s.charAt(left) != s.charAt(right)) {
                                changes++;
                            }

                            left += d;
                            right -= d;
                        }
                    }

                    minChanges = Math.min(minChanges, changes);
                }

                cost[start][end] = minChanges;
            }
        }

        // Step 2: Partition DP
        int[][] dp = new int[k + 1][n + 1];

        for (int[] row : dp) {
            java.util.Arrays.fill(row, INF);
        }

        dp[0][0] = 0;

        for (int p = 1; p <= k; p++) {

            for (int i = 2 * p; i <= n; i++) {

                // Try every possible partition point
                for (int j = 2 * (p - 1); j <= i - 2; j++) {

                    if (dp[p - 1][j] == INF) {
                        continue;
                    }

                    dp[p][i] = Math.min(
                        dp[p][i],
                        dp[p - 1][j] + cost[j][i]
                    );
                }
            }
        }

        return dp[k][n];
    }
}
