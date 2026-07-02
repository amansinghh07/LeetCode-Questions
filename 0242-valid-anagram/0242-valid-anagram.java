class Solution {
    public boolean isAnagram(String s, String t) {
       if(s.length()!=t.length()) return false;
        int[] count=new int[26];
        char[] sArr=s.toCharArray();
        char[] tArr=t.toCharArray();
        for(char c:sArr){
            count[(c-'a')]++;
        }
        for(char c:tArr){
            count[c-'a']--;
        }
        for(int i:count){
            if(i!=0) return false;
        }
        return true;
    }
}