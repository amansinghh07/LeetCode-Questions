class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] ans = new long[k];

        // dp[r] = number of subarrays ending at previous index
        // whose product % k == r
        long[] dp = new long[k];

        for (int num : nums) {
            int val = num % k;

            long[] newDp = new long[k];

            // Start a new subarray with nums[i]
            newDp[val]++;

            // Extend every previous subarray
            for (int r = 0; r < k; r++) {
                if (dp[r] > 0) {
                    int newRem = (r * val) % k;
                    newDp[newRem] += dp[r];
                }
            }

            // All subarrays ending at current index
            for (int r = 0; r < k; r++) {
                ans[r] += newDp[r];
            }

            dp = newDp;
        }

        return ans;
    }
}