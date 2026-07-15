class Solution {
    public int findKthLargest(int[] nums, int k) {
        Arrays.sort(nums);
        int i=0,j=nums.length-1;
        while(i<j){
            int temp=nums[i];
            nums[i]=nums[j];
            nums[j]=temp;
            i++;
            j--;
        }
        return nums[k-1];
    }
}