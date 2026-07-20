class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n=arr.length;
        Arrays.sort(arr);
        List<List<Integer>>ans=new ArrayList<>();
        int minDiff=(int)1e9;
        for(int i=1;i<n;i++){
           minDiff=Math.min(minDiff,arr[i]-arr[i-1]);
        }
        for(int i=1;i<n;i++){
            if(arr[i]-arr[i-1]==minDiff){
                ans.add(new ArrayList<>(Arrays.asList(arr[i-1],arr[i])));
            }
        }
        return ans;
    }
}