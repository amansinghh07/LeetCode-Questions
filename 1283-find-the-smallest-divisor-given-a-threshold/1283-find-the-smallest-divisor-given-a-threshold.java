class Solution {
    public int smallestDivisor(int[] nums, int threshold) {
        int low=1;
        int high=0;
        for(int num:nums)
        high=Math.max(high,num);
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(nums,threshold,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    private boolean isPossible(int[] nums,int threshold,int limit){
        int sum=0;
        for(int num:nums){
            sum+=(int)Math.ceil((double)num/limit);
            if(sum>threshold){
                break;
            }
        }
        return sum<=threshold;
    }
}