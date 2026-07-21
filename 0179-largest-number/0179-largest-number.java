class Solution {
    public String largestNumber(int[] nums) {
        int n=nums.length;
        String[] ans=new String[n];
        for(int i=0;i<n;i++){
            ans[i]=String.valueOf(nums[i]);
        }
        Arrays.sort(ans,(a,b)->(b+a).compareTo(a+b));
        if(ans[0].equals("0")) return "0";
        StringBuilder sb=new StringBuilder();
        for(String s:ans){
            sb.append(s);
        }
        return sb.toString();
    }
}