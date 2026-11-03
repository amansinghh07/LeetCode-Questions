class Solution {
    public int minMoves(int[] nums) {
      int n=nums.length;
      int max=-(int)1e9;
      for(int num:nums){
        max=Math.max(max,num);
      }
      int moves=0;
      for(int num:nums){
        moves+=Math.abs(num-max);
      } 
      return moves; 
    }
}