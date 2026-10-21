class Solution {
    public int minOperations(String s) {
        int n=s.length();
        boolean sorted=true;
        for(int i=1;i<n;i++){
            if(s.charAt(i-1)>s.charAt(i)){
                sorted=false;
                break;
            }
        }
        if(sorted) return 0;
        if(n==2) return -1;
        char min='z';
        char max='a';
        for(char ch:s.toCharArray()){
            min=(char) Math.min(min,ch);
            max=(char) Math.max(max,ch);
        }
        char first=s.charAt(0);
        char last=s.charAt(n-1);
        if(first==min || last==max) return 1;
         for (int i = 1; i < n - 1; i++) {
            char ch = s.charAt(i);

            if (ch == min || ch == max) {
                return 2;
            }
        }
        return 3;
    }
}