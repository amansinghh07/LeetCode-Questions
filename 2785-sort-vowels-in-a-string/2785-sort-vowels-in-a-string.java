class Solution {
    public String sortVowels(String s) {
        StringBuilder vowels=new StringBuilder();
        int n=s.length();
        for(char ch:s.toCharArray()){
            if(isVowel(ch)){
                vowels.append(ch);
            }
        }
        char[] arr=vowels.toString().toCharArray();
        Arrays.sort(arr);
        char[] result=s.toCharArray();
        int j=0;
        for(int i=0;i<n;i++){
            if(isVowel(result[i])){
                result[i]=arr[j];
                j++;
            }
        }
        return String.valueOf(result);
    }
    private boolean isVowel(char ch){
        return "AEIOUaeiou".indexOf(ch)!=-1;
    }
}