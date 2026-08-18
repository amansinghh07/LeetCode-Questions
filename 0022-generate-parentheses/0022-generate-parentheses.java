class Solution {
    public List<String> generateParenthesis(int n) {
        List<String>ans=new ArrayList<>();
        generate(0,0,"",ans,n);
        return ans;
    }
    private void generate(int open,int close,String current,List<String> ans,int n){
        if(open==close && open+close==2*n){
          ans.add(current);
          return;
        }
        if(open < n){
            generate(open+1,close,current+'(',ans,n);
        }
        if(close < open){
            generate(open,close+1,current+')',ans,n);
        }
    }
}