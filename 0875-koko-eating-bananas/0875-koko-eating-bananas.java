class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        int low=1,high=0;
        for(int p:piles){
            high=Math.max(high,p);
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(canEat(piles,h,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    private boolean canEat(int[] piles,int limit,int k){
        long hrs=0;
        for(int num:piles)
            hrs += (int)Math.ceil((double)num / k);
        return hrs<=limit;
    }
}