class Solution {
    public String[] createGrid(int m, int n) {
        String[] ans=new String[m];
        for(int i=0;i<m;i++){
            char[] row=new char[n];
            for(int j=0;j<n;j++){
                row[j]='#';
            }
            ans[i]=new String(row);
        }
        char[] firstRow=ans[0].toCharArray();
        for(int i=0;i<n;i++){
         firstRow[i]='.';
        }
        ans[0]=new String(firstRow);
        for(int i=1;i<m;i++){
        char[] row=ans[i].toCharArray();
        row[n-1]='.';
        ans[i]=new String(row);
        }
     return ans;
    }
}