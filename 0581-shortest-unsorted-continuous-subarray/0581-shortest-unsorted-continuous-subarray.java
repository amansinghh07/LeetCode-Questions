class Solution {
    public int findUnsortedSubarray(int[] nums) {
      int n=nums.length;
      int first=-1,last=-1;
      for(int i=0;i<n-1;i++){
        if(nums[i]>nums[i+1]){
            first=i;
            break;
        }
        }
        if(first==-1) return 0;
        for(int i=n-1;i>0;i--){
        if(nums[i]<nums[i-1]){
            last=i;
            break;
        }
      }
      int min=Integer.MAX_VALUE;
      int max=Integer.MIN_VALUE;
      for(int i=first;i<=last;i++){
        min=Math.min(min,nums[i]);
        max=Math.max(max,nums[i]);
      }
      while(first>0 && nums[first-1]>min){
        first--;
      }
      while(last<n-1 && nums[last+1]<max){
        last++;
      }
      return last-first+1;
    }
}