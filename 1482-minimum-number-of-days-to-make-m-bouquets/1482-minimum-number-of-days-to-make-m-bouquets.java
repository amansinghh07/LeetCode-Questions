class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n=bloomDay.length;
        if(n<(m*k)) return -1;
        int low=1;
        int high=0;
        int ans=-1;
        for(int i:bloomDay){
          high=Math.max(high,i);
        }
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(bloomDay,m,k,mid)){
                ans=mid;
                high=mid-1;
            }else{
                low=mid+1;
            }
        }
        return ans;
    }
    private boolean isPossible(int[] bloomDay,int m,int k,int days){
      int cnt=0;
      int noOfB=0;
      for(int i:bloomDay){
        if(i<=days){
            cnt++;
        }else{
            noOfB+=cnt/k;
            cnt=0;
        }
      }
      noOfB+=cnt/k;
      return noOfB>=m;
    }
}