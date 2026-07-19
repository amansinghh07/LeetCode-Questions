class Solution {
    public boolean isBipartite(int[][] graph) {
        int n=graph.length;
        int color[]=new int[n];
        Arrays.fill(color,-1);
        for(int i=0;i<n;i++){
            if(color[i]==-1){
                if(!bfs(i,graph,color)) return false;
            }
        }
        return true;
    }
    private boolean bfs(int start,int[][] graph,int[] color){
        color[start]=0;
        Queue<Integer>q=new LinkedList<>();
        q.offer(start);
        while(!q.isEmpty()){
            int node=q.poll();
            for(int it:graph[node]){
                if(color[it]==-1){
                color[it]=1-color[node];
                q.offer(it);
        }
        else if(color[it]==color[node]){
            return false;
        }
        }
    }
    return true;
}
}