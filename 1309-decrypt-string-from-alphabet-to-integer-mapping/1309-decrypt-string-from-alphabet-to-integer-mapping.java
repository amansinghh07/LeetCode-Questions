class Solution {
    public String freqAlphabets(String s) {
     StringBuilder ans=new StringBuilder();
     int i=s.length()-1;
     while(i>=0){
        if(s.charAt(i)=='#'){
        int num=Integer.parseInt(s.substring(i-2,i));
        ans.append((char)('a'+num-1));
        i-=3;
        }else{
            int num=Integer.parseInt(s.substring(i,i+1));
            ans.append((char)('a'+num-1));
            i--;
        }
     }
     return ans.reverse().toString();   
    }
}