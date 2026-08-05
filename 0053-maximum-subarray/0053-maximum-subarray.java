class Solution {

    public int maxSubArray(int[] nums) {
        return helper(nums, 0, 0, Integer.MIN_VALUE);
    }

    private int helper(int[] nums, int index, int currentSum, int maxSum) {

        if (index == nums.length)
            return maxSum;

        currentSum += nums[index];

        maxSum = Math.max(maxSum, currentSum);

        if (currentSum < 0)
            currentSum = 0;

        return helper(nums, index + 1, currentSum, maxSum);
    }
}