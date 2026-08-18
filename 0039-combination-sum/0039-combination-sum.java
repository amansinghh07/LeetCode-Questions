class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        int n=candidates.length;
        List<List<Integer>>ans=new ArrayList<>();
        List<Integer>currSum=new ArrayList<>();
        func(candidates,target,0,ans,currSum,n);
        return ans;
    }
    private void func(int[] candidates,int target,int i,List<List<Integer>>ans,List<Integer>currSum,int n){
        if(i==n && target==0){
            ans.add(new ArrayList<>(currSum));
            return;
        }
        if(target==0) {
            ans.add(new ArrayList<>(currSum));
            return;
        }
        if(target<0 || i==n) return;
        currSum.add(candidates[i]);
        func(candidates,target-candidates[i],i,ans,currSum,n);
        currSum.remove(currSum.size()-1);
        func(candidates,target,i+1,ans,currSum,n);
    }
}