class Solution {
    int m,n;
    int maxGold=0;
    public int getMaximumGold(int[][] grid) {
        m=grid.length;
        n=grid[0].length;
        for(int i=0;i<m;i++){
            for(int j=0;j<n;j++){
                if(grid[i][j]>0){
                    dfs(grid,i,j,0);
                }
            }
        }
        return maxGold;
    }
    private void dfs(int[][] grid,int row,int col,int gold){
        if(row<0 || row>=m || col<0 || col>=n || grid[row][col] == 0){
            return;
        }
        gold+=grid[row][col];
        maxGold=Math.max(gold,maxGold);
        int value=grid[row][col];
        grid[row][col]=0;
        dfs(grid,row+1,col,gold);
        dfs(grid,row-1,col,gold);
        dfs(grid,row,col-1,gold);
        dfs(grid,row,col+1,gold);
        grid[row][col]=value;
    }
}