class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        int l=0;
        double val = Double.NEGATIVE_INFINITY;
        int sum=0;
        for(int r=0;r<n;r++){
        sum+=nums[r];    
        if(r-l+1==k){
        val=Math.max(val,(double)sum/k);
        sum-=nums[l];
        l++;
        }
        }
        return val;
    }
}