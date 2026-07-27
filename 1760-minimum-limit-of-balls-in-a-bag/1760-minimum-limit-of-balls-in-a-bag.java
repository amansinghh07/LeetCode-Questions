class Solution {
    public int minimumSize(int[] nums, int maxOperations) {
       int n=nums.length;
       int low=1;
       int high=0;
       for(int num:nums){
        high=Math.max(high,num);
       }
       while(low<=high){
        int mid=low+(high-low)/2;
        long operations=0;
        for(int num:nums)
            operations+=(num-1)/mid;
            if(operations <= maxOperations){
                high=mid-1;
            } else{
                low=mid+1;
            }
        } 
        return low;
       } 
    }