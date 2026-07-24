class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n=nums.length;
        if(n==1) return (double)nums[0]/k;
        int l=0,r=0;
        double val = Double.NEGATIVE_INFINITY;
        int sum=0;
        while(r<n){
            int limit=l+k;
        while(r<limit){
            sum+=nums[r];
            r++;
        }
        val=Math.max(val,(double)sum/k);
        sum-=nums[l];
        l++;
        }
        return val;
    }
}