class Solution {
    public int maxDistance(int[] position, int m) {
        int low=1,high=0;
        int n=position.length;
        Arrays.sort(position);
        high=position[n-1]-position[0];
        int ans=1;
        while(low<=high){
            int mid=low+(high-low)/2;
            if(isPossible(position,m,mid)){
                ans=mid;
                low=mid+1;
            }else{
                high=mid-1;
            }
        }
        return ans;
    }
    private boolean isPossible(int[] position,int m,int dist){
        int balls=1;
        int last=position[0];
        for(int i=1;i<position.length;i++){
            if(position[i]-last>=dist){
                balls++;
                last=position[i];
            }
        }
        return balls>=m;
    }
}