
class Solution {
    public int climbStairs(int n, int[] costs) {

        int[] dp = new int[n + 1];

        Arrays.fill(dp, Integer.MAX_VALUE);

        dp[0] = 0;

        // Calculate minimum cost for every step
        for (int i = 1; i <= n; i++) {

            // Try jumping 1, 2 or 3 steps
            for (int jump = 1; jump <= 3; jump++) {

                int prev = i - jump;

                if (prev >= 0) {

                    int newCost = costs[i - 1] + jump * jump;

                    dp[i] = Math.min(
                        dp[i],
                        dp[prev] + newCost
                    );
                }
            }
        }

        return dp[n];
    }
}
