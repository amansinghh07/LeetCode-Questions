import java.util.*;

class Solution {
    public long maxAlternatingSum(int[] nums) {
        Arrays.sort(nums);

        long ans = 0;
        int n = nums.length;

        // Sort by absolute value instead
        Integer[] arr = new Integer[n];
        for (int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        Arrays.sort(arr, (a, b) ->
            Long.compare((long) a * a, (long) b * b));

        for (int i = 0; i < n; i++) {
            long square = (long) arr[i] * arr[i];

            if (i < n / 2) {
                ans -= square;
            } else {
                ans += square;
            }
        }

        return ans;
    }
}