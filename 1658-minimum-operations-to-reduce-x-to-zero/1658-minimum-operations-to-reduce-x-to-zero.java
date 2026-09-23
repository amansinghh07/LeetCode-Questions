class Solution {
    public int minOperations(int[] nums, int x) {

        int n = nums.length;

        int totalSum = 0;

        for (int num : nums) {
            totalSum += num;
        }

        int target = totalSum - x;

        // We need to remove everything
        if (target < 0) {
            return -1;
        }

        // Entire array is the subarray we want to keep
        if (target == 0) {
            return n;
        }

        int left = 0;
        int windowSum = 0;
        int maxLength = -1;

        for (int right = 0; right < n; right++) {

            windowSum += nums[right];

            // Shrink window
            while (left <= right && windowSum > target) {
                windowSum -= nums[left];
                left++;
            }

            // Found a valid subarray
            if (windowSum == target) {
                maxLength = Math.max(
                    maxLength,
                    right - left + 1
                );
            }
        }

        if (maxLength == -1) {
            return -1;
        }

        return n - maxLength;
    }
}