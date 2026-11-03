class Solution {
    public int minMoves(int[] nums) {
      int n=nums.length;
      int min=-(int)1e9;
      for(int num:nums){
        min=Math.max(min,num);
      }
      int moves=0;
      for(int num:nums){
        moves+=Math.abs(num-min);
      } 
      return moves; 
    }
}