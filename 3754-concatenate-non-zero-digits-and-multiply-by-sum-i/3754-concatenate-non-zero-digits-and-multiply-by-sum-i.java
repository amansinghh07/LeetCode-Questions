class Solution {
    public long sumAndMultiply(int n) {
        String str=String.valueOf(n);
        StringBuilder sb=new StringBuilder();
        int sum=0;
        for(char ch:str.toCharArray()){
            int digit=ch-'0';
            if(digit>0){
                sb.append(ch);
                sum+=digit; 
            }
        }
        if(sb.length()==0) return 0;
        long ans=Long.parseLong(sb.toString());
        return ans*sum;
    }
}