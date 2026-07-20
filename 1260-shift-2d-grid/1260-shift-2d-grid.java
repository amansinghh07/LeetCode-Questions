class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int n= grid.length;
        int m=grid[0].length;
        int ans[][]=new int[n][m];
        int size=n*m;
        k%=size;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
                int oI=i*m+j;
                int nI=(oI+k)%size;
                int row=nI/m;
                int col=nI%m;
                ans[row][col]=grid[i][j];
            }
        }
        List<List<Integer>>res=new ArrayList<>();
        for(int i=0;i<n;i++){
            List<Integer>row=new ArrayList<>();
            for(int j=0;j<m;j++){
                row.add(ans[i][j]);
            }
            res.add(row);
        }
        return res;
    }
}