class Solution {
    public boolean checkString(String s) {
        int n=s.length();
        boolean start=false;
        for(char ch:s.toCharArray()){
            if(ch=='a' && start==true){
                return false;
            }else if(ch=='b'){
                start=true;
            }
        }
        return true;
    }
}