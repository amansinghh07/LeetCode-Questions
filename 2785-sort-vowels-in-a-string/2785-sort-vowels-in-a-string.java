class Solution {
    public String sortVowels(String s) {
        int n=s.length();
        List<Character>vowels=new ArrayList<>();
        for(char ch:s.toCharArray()){
            if(isVowel(ch)){
                vowels.add(ch);
            }
        }
        Collections.sort(vowels);
        StringBuilder result=new StringBuilder();
        int j=0;
        for(char ch:s.toCharArray()){
            if(isVowel(ch)){
              result.append(vowels.get(j++));
            }else{
                result.append(ch);
            }
        }
        return result.toString();
    }
    private boolean isVowel(char ch){
        return "AEIOUaeiou".indexOf(ch)!=-1;
    }
}