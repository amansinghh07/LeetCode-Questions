class Solution {
    public int countIslands(int[][] grid, int k) {
        int n=grid.length;
        int m=grid[0].length;
        int count=0;
        boolean[][] vis=new boolean[n][m];
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                if(grid[i][j]!=0 && !vis[i][j]){
                    long sum=dfs(i,j,grid,n,m,vis);
                    if(sum%k==0){
                        count++;
                    }
                }
            }
        }
        return count;
    }
    private long dfs(int r,int c,int[][] grid,int n,int m,boolean[][] vis){
        if(r>=n || r<0 || c<0 || c>=m || grid[r][c]==0 || vis[r][c]==true){
            return 0;
        }
        vis[r][c]=true;
        long sum=grid[r][c];
        sum+=dfs(r+1,c,grid,n,m,vis);
        sum+=dfs(r-1,c,grid,n,m,vis);
        sum+=dfs(r,c+1,grid,n,m,vis);
        sum+=dfs(r,c-1,grid,n,m,vis);
    return sum;
    }
}