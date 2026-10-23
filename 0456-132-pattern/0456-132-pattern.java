class Solution {
    public boolean find132pattern(int[] nums) {
        int n=nums.length;
        int nums3=-(int)1e9;
        Stack<Integer>st=new Stack<>();
        for(int i=n-1;i>=0;i--){
            if(nums[i]<nums3){
                return true;
            }
            while(!st.isEmpty() && st.peek()<nums[i]){
                nums3=st.pop();
            }
            st.push(nums[i]);
        }
        return false;
    }
}