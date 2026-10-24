class Solution {
    public int minimumCost(int[] cost) {
        int n=cost.length;
        if(n==1) return cost[0];
        if(n==2) return cost[0]+cost[1];
       Arrays.sort(cost);
       Stack<Integer>st=new Stack<>();
       int minCost=0;
       for(int i=n-1;i>=0;i--){
         if(st.size()==2){
            while(!st.isEmpty()){
                minCost+=st.pop();
            }
            continue;
         }
        st.push(cost[i]);
       }
         while (!st.isEmpty()) {
            minCost += st.pop();
        }
       return minCost;
    }
}