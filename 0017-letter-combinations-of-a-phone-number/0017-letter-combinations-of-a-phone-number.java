class Solution {
    private final String map[]=new String[]{
        "",
        "",
        "abc",
        "def",
        "ghi",
        "jkl",
        "mno",
        "pqrs",
        "tuv",
        "wxyz"
    };
    public List<String> letterCombinations(String digits) {
        List<String>ans=new ArrayList<>();
        int n=digits.length();
        if(n==0) return ans;
        func(0,digits,ans,"");
        return ans;
    }
    private void func(int ind,String digits,List<String>ans,String curr){
        if(ind==digits.length()){
            ans.add(curr);
            return;
        }
        String s=map[digits.charAt(ind)-'0'];
        for(int i=0;i<s.length();i++){
            func(ind+1,digits,ans,curr+s.charAt(i));
        }
    }
}