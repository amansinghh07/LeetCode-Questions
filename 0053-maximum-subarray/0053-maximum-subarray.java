class Solution {
    public int maxSubArray(int[] nums) {
        int n=nums.length;
        int sum=0;
        int maxSum=Integer.MIN_VALUE;
        int i=0;
        while(i<n){
            sum+=nums[i];
             if(sum>maxSum) maxSum=sum;
             if(sum<0) sum=0;
            i++;
        }
        return maxSum;
    }
}