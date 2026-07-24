class Solution {
    public int longestOnes(int[] nums, int k) {
        int n=nums.length;
        int zeroes=0;
        int l=0,r=0;
        int maxOnes=0;
        while(r<n){
            if(nums[r]==0){
                zeroes++;
            }
            while(zeroes>k){
               if(nums[l]==0){
                zeroes--;
               }
               l++;
            }if(zeroes<=k){
                maxOnes=Math.max(maxOnes,(r-l)+1);
                r++;
            }
        }
        return maxOnes;
    }
}