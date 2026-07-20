class Solution {
    public int longestMountain(int[] arr) {
        int n=arr.length;
        int res=0;
        for(int i=1;i<n;i++){
            int count=1;
            int j=i;
            boolean flag=false;
            while(j<n && arr[j]>arr[j-1]){
                j++;
                count++;
            }
            while(i!=j && j<n && arr[j]<arr[j-1]){
                j++;
                count++;
                flag=true;
            }
            if(i!=j && flag && count>2){
                res=Math.max(res,count);
                j--;
            }
            i=j;
        }
        return res;
    }
}