class Solution {
    public int maxScore(int[] cardPoints, int k) {
        int n=cardPoints.length;
        int lSum=0;
        int maxSum=0;
        int right=n-1;
        int rSum=0;
        for(int i=0;i<k;i++){
            lSum+=cardPoints[i];
        }
        maxSum=lSum;
        for(int i=k-1;i>=0;i--){
            lSum-=cardPoints[i];
            rSum+=cardPoints[right];
            right--;
            maxSum=Math.max(maxSum,lSum+rSum);
        }
      return maxSum;
    }
}