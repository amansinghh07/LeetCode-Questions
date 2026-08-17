class Solution {
    public int stoneGameV(int[] stoneValue) {

        int n = stoneValue.length;

        // prefix[i] = sum of stoneValue[0 ... i-1]
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        // dp[l][r] = maximum score obtainable from l...r
        int[][] dp = new int[n][n];

        // length = current interval length
        for (int len = 2; len <= n; len++) {

            for (int l = 0; l + len <= n; l++) {

                int r = l + len - 1;

                for (int i = l; i < r; i++) {

                    int leftSum = prefix[i + 1] - prefix[l];
                    int rightSum = prefix[r + 1] - prefix[i + 1];

                    if (leftSum < rightSum) {

                        dp[l][r] = Math.max(
                            dp[l][r],
                            leftSum + dp[l][i]
                        );

                    } else if (leftSum > rightSum) {

                        dp[l][r] = Math.max(
                            dp[l][r],
                            rightSum + dp[i + 1][r]
                        );

                    } else {

                        dp[l][r] = Math.max(
                            dp[l][r],
                            Math.max(
                                leftSum + dp[l][i],
                                rightSum + dp[i + 1][r]
                            )
                        );
                    }
                }
            }
        }

        return dp[0][n - 1];
    }
}