class Solution {
    public int maximumProduct(int[] nums) {
        int n=nums.length;
        Arrays.sort(nums);
        int leftSide=nums[0]*nums[1]*nums[n-1];
        int rightSide=nums[n-3]*nums[n-2]*nums[n-1];
        return Math.max(leftSide,rightSide);
    }
}