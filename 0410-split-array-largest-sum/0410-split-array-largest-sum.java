class Solution {
    public int splitArray(int[] nums, int k) {
        int low=0;
        int high=0;
        for(int i:nums){
            low=Math.max(i,low);
            high+=i; 
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            int partition=splitArray(nums,k,mid);
            if(partition<=k){
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return low;
    }
    private int splitArray(int[] nums,int k,int limit){
     int currSum=0;
     int cnt=1;
     for(int num:nums){
        if(currSum+num<=limit){
            currSum+=num;
        }else{
            currSum=num;
            cnt++;
        }
     }
     return cnt;
    }
}