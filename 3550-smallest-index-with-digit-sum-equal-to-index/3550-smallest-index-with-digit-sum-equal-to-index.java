class Solution {
    public int smallestIndex(int[] nums) {
        int n=nums.length;
        int ans=-1;
        for(int i=0;i<n;i++){
            int sum=0;
            int var=nums[i];
            while(var>0){
                int mod=var%10;
                sum+=mod;
                var/=10;
            }
            if(sum==i){
                return i;
            }
        }
        return ans;
    }
}