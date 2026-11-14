
class Solution {
    public int minimumSumSubarray(List<Integer> nums, int l, int r) {

        int n = nums.size();

        // Step 1: Build prefix sum array
        int[] prefix = new int[n + 1];

        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + nums.get(i);
        }

        int ans = Integer.MAX_VALUE;

        // Step 2: Try every starting index
        for (int i = 0; i < n; i++) {

            // Step 3: Try every valid subarray length
            for (int len = l; len <= r; len++) {

                // Ensure subarray stays within array bounds
                if (i + len > n) {
                    break;
                }

                // Calculate subarray sum
                int sum = prefix[i + len] - prefix[i];

                // We need the minimum positive sum
                if (sum > 0) {
                    ans = Math.min(ans, sum);
                }
            }
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}
