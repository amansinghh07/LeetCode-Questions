class Solution {
    public int numJewelsInStones(String jewels, String stones) {
        int cnt=0;
        int freq[]=new int[58];
        for(char ch:jewels.toCharArray()){
            freq[ch-'A']++;
        }
        for(char ch:stones.toCharArray()){
            if(freq[ch-'A']>0) cnt++;
        }
        return cnt;
    }
}