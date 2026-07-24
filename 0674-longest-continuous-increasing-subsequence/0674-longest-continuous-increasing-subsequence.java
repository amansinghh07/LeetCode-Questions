class Solution {
    public int findLengthOfLCIS(int[] nums) {
       int n=nums.length;
       int i=0,j=1;
       int maxLength=1;
       while(j<n){
         if(nums[j]>nums[j-1]){
            maxLength=Math.max(maxLength,(j-i)+1);
            j++;
         }
         else if(nums[j]<=nums[j-1]){
            i=j;
            j++;
         }
       }
       return maxLength; 
    }
}