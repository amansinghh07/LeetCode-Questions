class Solution {
    public int findMaxLength(int[] nums) {
        int n=nums.length;
        Map<Integer,Integer>mpp=new HashMap<>();
        mpp.put(0,-1);
        int prefixSum=0;
        int maxLen=0;
        for(int i=0;i<n;i++){
            if(nums[i]==0){
                prefixSum--;
            }else{
                prefixSum++;
            }
            if(mpp.containsKey(prefixSum)){
                maxLen=Math.max(maxLen,i-mpp.get(prefixSum));
            }else{
                mpp.put(prefixSum,i);
            }
        }
        return maxLen;
    }
}