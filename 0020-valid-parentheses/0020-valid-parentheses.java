class Solution {
   public boolean isValid(String str) {
        Stack<Character>st=new Stack<>();
        for(char ch:str.toCharArray()){
            if(ch=='(' || ch=='{' || ch=='['){
                st.push(ch);
            }else{
            if(st.isEmpty()){
                return false;
                }
            int top=st.pop();
            if(ch==')' && top!='('|| ch=='}' && top!='{' || ch==']' && top!='['){
                return false;
            }
            }
        }
        return st.isEmpty();
    }
}