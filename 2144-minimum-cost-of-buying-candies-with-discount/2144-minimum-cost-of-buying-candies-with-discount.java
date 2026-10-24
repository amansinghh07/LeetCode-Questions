class Solution {
    public int minimumCost(int[] cost) {
        int n=cost.length;
        Arrays.sort(cost);
        int minCost=0;
        for(int i=n-1;i>=0;i--){
            if((n-1-i)%3!=2){
                minCost+=cost[i];
            }
        }
        return minCost;
    }
}