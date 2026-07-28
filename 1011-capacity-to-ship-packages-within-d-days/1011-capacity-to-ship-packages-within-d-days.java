class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int low=0;
        int high=0;
        for(int num: weights){
            low=Math.max(low,num);
            high+=num;
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(canWePlace(weights,days,mid)){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    private boolean canWePlace(int[] weights,int limit,int capacity){
        int currentWeight=0;
        int days=1;
        for(int i:weights){
            if(currentWeight+i<=capacity)
             currentWeight+=i;
             else{
                days++;
                currentWeight=i;
             }
        }
        return days<=limit;
    }
}