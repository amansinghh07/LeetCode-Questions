class Solution {
    public int maximumCandies(int[] candies, long k) {
        int low=1;
        int high=0;
        for(int i:candies){
            high=Math.max(high,i);
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(candies,k,mid)){
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return high;
    }
    private boolean isPossible(int[] candies,long k,int mid){
        long cnt=0;
        for(int i:candies){
           cnt+=i/mid;
           if(cnt>=k) return true;
        }
        return false;
    }
}