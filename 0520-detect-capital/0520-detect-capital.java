class Solution {
    public boolean detectCapitalUse(String word) {
        int capitalCount=0;
        for(char ch:word.toCharArray()){
            if(Character.isUpperCase(ch))
            capitalCount++;
        }
        int n=word.length();
        return capitalCount==n || capitalCount==0 || capitalCount==1 && Character.isUpperCase(word.charAt(0));
    }
}