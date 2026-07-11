class Solution {

    int nodes;
    int edges;

    public int countCompleteComponents(int n, int[][] edgesArr) {

        List<List<Integer>> adj = new ArrayList<>();

        for(int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for(int[] e : edgesArr){
            adj.get(e[0]).add(e[1]);
            adj.get(e[1]).add(e[0]);
        }

        boolean[] vis = new boolean[n];
        int ans = 0;

        for(int i = 0; i < n; i++){

            if(!vis[i]){

                nodes = 0;
                edges = 0;

                dfs(i, adj, vis);

                edges /= 2;

                if(edges == nodes * (nodes - 1) / 2)
                    ans++;
            }
        }

        return ans;
    }

    private void dfs(int node, List<List<Integer>> adj, boolean[] vis){

        vis[node] = true;
        nodes++;

        edges += adj.get(node).size();

        for(int nei : adj.get(node)){
            if(!vis[nei])
                dfs(nei, adj, vis);
        }
    }
}