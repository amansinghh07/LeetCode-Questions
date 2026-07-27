class Solution {
    public char nextGreatestLetter(char[] letters, char target) {
        int n=letters.length;
        int low=0,high=n-1;
        while(low<=high){
            int mid=(low+high)/2;
            char midChar=letters[mid];
            if(midChar>target)
                high=mid-1;
            else
                low=mid+1;
        }
        return letters[low%n];
    }
}