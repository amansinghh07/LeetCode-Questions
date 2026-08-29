class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
       int n=temperatures.length;
       int temps[]=new int[n];
       Stack<Integer>st=new Stack<>();
       for(int i=0;i<n;i++){
        while(!st.isEmpty() && temperatures[st.peek()]<temperatures[i]){
            int ind=st.pop();
            temps[ind]=i-ind;
        }
        st.push(i);
       }
       return temps;
    }
}