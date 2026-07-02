class Solution {
    private int nR[]={-1,0,1,0};
    private int nC[]={0,1,0,-1};
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int n=grid.size();
        int m=grid.get(0).size();
        int startHealth=health-grid.get(0).get(0);
        if(startHealth<=0) return false;
        int best[][]=new int[n][m];
        for(int row[]:best){
            Arrays.fill(row,-1);
        }
        best[0][0]=startHealth;
        Queue<int[]>q=new LinkedList<>();
        q.offer(new int[]{0,0,startHealth});
        while(!q.isEmpty()){
            int arr[]=q.poll();
            int row=arr[0];
            int col=arr[1];
            int currHealth=arr[2];
            if(row==n-1 && col==m-1) return true;
            for(int i=0;i<4;i++){
                int nRow=row+nR[i];
                int nCol=col+nC[i];
                if(!isValid(nRow,nCol,n,m))
                    continue;
                   int newHealth=currHealth-grid.get(nRow).get(nCol);
                   if(newHealth<=0) continue;
                    if(newHealth>best[nRow][nCol]){
                        best[nRow][nCol]=newHealth;
                        q.offer(new int[]{nRow,nCol,newHealth});
                    }
            }
        }
        return false;
    }
    private boolean isValid(int i,int j,int n,int m){
        if(i<0 || i>=n) return false;
        if(j<0 || j>=m) return false;
        return true;
    }
}