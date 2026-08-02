class Solution {
    public boolean checkInclusion(String s1, String s2) {
        int k=s1.length();
        int n=s2.length();
        if(k>n) return false;
        int freq[]=new int[26];
        int windowFreq[]=new int[26];
        int left=0;
        for(char c:s1.toCharArray())
        freq[c-'a']++;
        for(int right=0;right<n;right++){
            windowFreq[s2.charAt(right)-'a']++;
            if(right-left+1>k){
                windowFreq[s2.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==k){
                if(Arrays.equals(windowFreq,freq)){
                    return true;
                }
            }
        }
        return false;
    }
}