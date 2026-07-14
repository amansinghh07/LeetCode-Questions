class Solution {

    int MOD = 1_000_000_007;
    int[][][] dp;
    int[] nums;
    int n;

    public int subsequencePairCount(int[] nums) {
        this.nums = nums;
        n = nums.length;

        dp = new int[n][201][201];

        for (int i = 0; i < n; i++)
            for (int j = 0; j <= 200; j++)
                Arrays.fill(dp[i][j], -1);

        return solve(0, 0, 0);
    }

    int solve(int idx, int g1, int g2) {

        if (idx == n) {
            return (g1 != 0 && g1 == g2) ? 1 : 0;
        }

        if (dp[idx][g1][g2] != -1)
            return dp[idx][g1][g2];

        long ans = 0;

        // skip
        ans += solve(idx + 1, g1, g2);

        // put in seq1
        int ng1 = (g1 == 0) ? nums[idx] : gcd(g1, nums[idx]);
        ans += solve(idx + 1, ng1, g2);

        // put in seq2
        int ng2 = (g2 == 0) ? nums[idx] : gcd(g2, nums[idx]);
        ans += solve(idx + 1, g1, ng2);

        return dp[idx][g1][g2] = (int) (ans % MOD);
    }

    int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}