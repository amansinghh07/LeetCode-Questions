class Solution {
    public int smallestNumber(int n, int t) {
        for(int i=n;i<=100;i++){
        int prod=1;
        int idx=i;
        while(idx>0){
            int rem=idx%10;
            prod*=rem;
            idx/=10;
        }
        if(prod%t==0) return i;
        }
        return -1;
    }
}