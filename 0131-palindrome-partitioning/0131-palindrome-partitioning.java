class Solution {
    public List<List<String>> partition(String s) {
        int n=s.length();
        List<List<String>>ans=new ArrayList<>();
        List<String>current=new ArrayList<>();
        func(0,s,ans,current,n);
        return ans;
    }
    private void func(int ind,String s,List<List<String>>ans,List<String>current,int n){
        if(ind==n){
            ans.add(new ArrayList<>(current));
            return;
        }
        for(int i=ind;i<n;i++){
            if(isPalindrome(s,ind,i)){
                current.add(s.substring(ind,i+1));
                func(i+1,s,ans,current,n);
                current.remove(current.size()-1);
            }
        }
    }
    private boolean isPalindrome(String s,int start,int end){
        while(start<=end){ if(s.charAt(start++)!=s.charAt(end--)) return false;}
        return true;
    }
}