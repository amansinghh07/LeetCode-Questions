class Solution {
    public List<Integer> findAnagrams(String s, String p) {
        int k=p.length();
        int n=s.length();
        List<Integer>ans=new ArrayList<>();
        if(n<k) return ans;
        int[] freq=new int[26];
        int[] window=new int[26];
        for(char c:p.toCharArray())
        freq[c-'a']++;
        int left=0,right=0;
        while(right<n){
            window[s.charAt(right)-'a']++;
            if(right-left+1>k){
                window[s.charAt(left)-'a']--;
                left++;
            }
            if(right-left+1==k){
            if(Arrays.equals(freq,window)){
                ans.add(left);
            }
            }
            right++;
        }
        return ans;
    }
}