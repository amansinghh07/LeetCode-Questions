class Solution {
    int ans=0;
    int rows,cols;
    public int uniquePathsIII(int[][] grid) {
        rows=grid.length;
        cols=grid[0].length;
        int startRow=0;
        int startCol=0;
        int emptyCells=0;
        for(int i=0;i<rows;i++){
            for(int j=0;j<cols;j++){
                if(grid[i][j] != -1){
                    emptyCells++;
                }
                if(grid[i][j] ==1){
                    startRow=i;
                    startCol=j;
                }
            }
        }
        dfs(grid,startRow,startCol,emptyCells);
        return ans;
    }
    private void dfs(int[][] grid,int row,int col,int remaining){
        if(row<0 || row >= rows || col<0 || col>= cols || grid[row][col] == -1){
            return;
        }
        if(grid[row][col] == 2){
            if(remaining == 1){
                ans++;
            }
            return;
        }
        grid[row][col]=-1;
        dfs(grid,row+1,col,remaining-1);
        dfs(grid,row-1,col,remaining-1);
        dfs(grid,row,col+1,remaining-1);
        dfs(grid,row,col-1,remaining-1);
        grid[row][col]=0;
    }
}