class Solution {

    Integer[] dp;
    int[] stone;

    public int solve(int i) {

        if (i >= stone.length)
            return 0;

        if (dp[i] != null)
            return dp[i];

        int sum = 0;
        int ans = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && i + k < stone.length; k++) {
            sum += stone[i + k];
            ans = Math.max(ans, sum - solve(i + k + 1));
        }

        return dp[i] = ans;
    }

    public String stoneGameIII(int[] stoneValue) {

        stone = stoneValue;
        dp = new Integer[stone.length];

        int diff = solve(0);

        if (diff > 0)
            return "Alice";

        if (diff < 0)
            return "Bob";

        return "Tie";
    }
}