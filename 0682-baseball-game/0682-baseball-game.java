class Solution {
    public int calPoints(String[] operations) {
        Stack<Integer>st=new Stack<>();
        for(String op: operations){
            if(op.equals("C")){
                if(!st.isEmpty()){
                    st.pop();
                }
            }
           else if(op.equals("D")){
            int num=st.peek();
            st.push(num*2);
           }
           else if(op.equals("+")){
            int num=st.pop();
            int sNum=st.peek();
            st.push(num);
            st.push(num+sNum);
           }else{
            st.push(Integer.parseInt(op));
           }
        }
        int sum=0;
        while(!st.isEmpty()){
            sum+=st.pop();
        }
    return sum;
    }
}