class Pair{
    int row,col;
    public Pair(int row,int col){
        this.row=row;
        this.col=col;
    }
}
class Solution {
    private int delRow[]={-1,0,1,0};
    private int delCol[]={0,1,0,-1};
    public int numIslands(char[][] grid) {
        int n=grid.length;
        int m=grid[0].length;
        boolean vis[][]=new boolean[n][m];
        int cnt=0;
        Queue<Pair>q=new LinkedList<>();
        for(int k=0;k<n;k++){
            for(int j=0;j<m;j++){
                if(!vis[k][j] && grid[k][j]=='1'){
                   cnt++;
                   bfs(k,j,vis,grid,q);
                }
            }
        }
        return cnt;
    }
    private boolean isValid(int i,int j,int n,int m){
        if(i<0 || i>=n) return false;
        if(j<0 || j>=m) return false;
        return true;
    }
    private void bfs(int i,int j,boolean[][] vis,char[][] grid,Queue<Pair>q){
        q.add(new Pair(i,j));
         vis[i][j]=true;
        while(!q.isEmpty()){
            Pair node=q.poll();
            int row=node.row;
            int col=node.col;
            for(int k=0;k<4;k++){
                int nRow=row+delRow[k];
                int nCol=col+delCol[k];
                if(isValid(nRow,nCol,grid.length,grid[0].length) && grid[nRow][nCol]=='1' && !vis[nRow][nCol] ){
                    vis[nRow][nCol]=true;
                    q.add(new Pair(nRow,nCol));
                }
            }
        }
    }
}