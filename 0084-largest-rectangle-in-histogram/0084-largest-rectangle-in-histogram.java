class Solution {
    public int largestRectangleArea(int[] heights) {
      int nse,pse,area;
      int maxArea=0;
      Stack<Integer>st=new Stack<>();
      for(int i=0;i<heights.length;i++){
          while(!st.isEmpty() && heights[st.peek()]>=heights[i]){
            int ind=st.pop();
            pse=(st.isEmpty())?-1:st.peek();
            nse=i;
            area=heights[ind]*(nse-pse-1);
            maxArea=Math.max(area,maxArea);
          }
          st.push(i);
      }
      while(!st.isEmpty()){
        nse=heights.length;
        int ind=st.pop();
        pse=(st.isEmpty())?-1:st.peek();
        area=heights[ind]*(nse-pse-1);
        maxArea=Math.max(area,maxArea);
      }
      return maxArea;
    }
}