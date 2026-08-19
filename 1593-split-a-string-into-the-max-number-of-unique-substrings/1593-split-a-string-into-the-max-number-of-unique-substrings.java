class Solution {
    public int maxUniqueSplit(String s) {
        Set<String>used=new HashSet<>();
        return backtrack(s,0,used);
    }
    private int backtrack(String s,int start,Set<String>used){
        if(start==s.length()){
           return used.size();
        }
        int max=0;
        for(int end=start+1;end<=s.length();end++){
            String sub=s.substring(start,end);
            if(used.contains(sub)) continue;
            used.add(sub);
            max=Math.max(max,backtrack(s,end,used));
            used.remove(sub);
        }
        return max;
    }
}