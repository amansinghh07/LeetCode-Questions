class Solution {
    public int search(int[] nums, int target) {
        int n=nums.length;
        int left=0,right=n-1;
        return helper(left,right,nums,target);
    }
    private int helper(int left,int right,int[] nums,int target){
        if(left>right) return -1;
        int mid=left+(right-left)/2;
        if(nums[mid]==target) return mid;
        if(nums[mid]<target) return helper(mid+1,right,nums,target);
        return  helper(left,mid-1,nums,target);
    }
}