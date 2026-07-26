class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        Map<Integer,Integer>freq=new HashMap<>();
        freq.put(0,1);
        int prefixSum=0,result=0;
        for(int num:nums){
            prefixSum+=num;
            int rem=prefixSum % k;
            if(rem<0) rem+=k;
            if(freq.containsKey(rem)){
                result+=freq.get(rem);
            }
            freq.put(rem,freq.getOrDefault(rem,0)+1);
        }
        return result;
    }
}