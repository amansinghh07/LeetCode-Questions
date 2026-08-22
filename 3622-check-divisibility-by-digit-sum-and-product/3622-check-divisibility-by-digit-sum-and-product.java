class Solution {
    public boolean checkDivisibility(int n) {
       int sum=0,prod=1;
       int temp=n;
       while(temp>0){
        int mod=temp%10;
        sum+=mod;
        prod*=mod;
        temp=temp/10;
       } 
       return n%(prod+sum)==0;
    }
}