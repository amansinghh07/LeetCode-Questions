class Solution {
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k<=1) return 0;
      int n=nums.length;
      int l=0,r=0;
      int count=0;
      int prod=1;
      while(r<n){
      prod*=nums[r];
      while(prod>=k){
        prod /=nums[l];
        l++;
      }
      count+=(r-l)+1;  
      r++;
      }
      return count;  
    }
}