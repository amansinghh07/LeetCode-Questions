class Solution {
    public int strStr(String haystack, String needle) {
        int ans=-1;
        int n=haystack.length();
        int m=needle.length();
        if(m>n) return ans;
        int left=0,right=m-1;
        while(right<n){
            String windowStr=haystack.substring(left,right+1);
            if(needle.equals(windowStr)){
                return left;
            }else{
                left++;
                right++;
            }
        }
        return ans;
    }
}