class Solution {
    public boolean canPartition(int[] arr) {
      int n=arr.length;
      int sum=0;  
      for(int i=0;i<n;i++){
      sum+=arr[i];
      }
      int k=0;
      if(sum%2==1){
        return false;
      } else{
         k=sum/2;
      }
      int dp[][]=new int[n][k+1];
      for(int nums[]:dp) Arrays.fill(nums,-1);
      return func(n-1,arr,dp,k);
    }
    private boolean func(int i,int[] arr,int[][] dp,int target){
        if(i==0) return arr[i]==target;
        if(target==0) return true;
        if(dp[i][target]!=-1) return dp[i][target]==1;
        boolean notTaken=func(i-1,arr,dp,target);
        boolean taken=false;
        if(arr[i]<=target){
            taken=func(i-1,arr,dp,target-arr[i]);
        }
        dp[i][target]=taken || notTaken ? 1 : 0;
        return dp[i][target]==1;
    }
}