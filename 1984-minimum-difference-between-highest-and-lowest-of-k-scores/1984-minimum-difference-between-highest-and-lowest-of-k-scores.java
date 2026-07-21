class Solution {
    public int minimumDifference(int[] nums, int k) {
        int n=nums.length;
        if(1==k) return 0;
        int minDiff=Integer.MAX_VALUE;
        Arrays.sort(nums);
        for(int i=0;i<=n-k;i++)
        {
            minDiff=Math.min(minDiff,nums[i+k-1]-nums[i]);
        }
        return minDiff;
    }
}