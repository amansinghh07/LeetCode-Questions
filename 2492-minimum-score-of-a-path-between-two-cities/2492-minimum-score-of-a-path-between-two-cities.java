class Solution {
      int ans=Integer.MAX_VALUE;
    public int minScore(int n, int[][] roads) {
        List<int[]>[] adjList=new ArrayList[n+1];
        boolean[] visited=new boolean[n+1];     
        for(int i=1;i<=n;i++){
            adjList[i]=new ArrayList<>();
        }
        for(int[] road:roads){
            adjList[road[0]].add(new int[]{road[1],road[2]});
            adjList[road[1]].add(new int[]{road[0],road[2]});
        }
        dfs(1,adjList,visited);
        return ans;
    }
    private void dfs(int node,List<int[]>[] adjList,boolean[] vis){
        vis[node]=true;
        for(int next[]:adjList[node]){
            ans=Math.min(ans,next[1]);
            if(!vis[next[0]]){
                dfs(next[0],adjList,vis);
            }
        }
    }
}