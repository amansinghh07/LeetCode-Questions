class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String,List<String>>groupedAnagrams=new HashMap<>();
        for(String words:strs){
            char[] chars=words.toCharArray();
            Arrays.sort(chars);
            String sortedStr=new String(chars);
            if(groupedAnagrams.containsKey(sortedStr)){
                groupedAnagrams.get(sortedStr).add(words);
            }else{
                List<String>anagramList=new ArrayList<>();
                anagramList.add(words);
                groupedAnagrams.put(sortedStr,anagramList);
            }
        }
        return new ArrayList<>(groupedAnagrams.values());
    }
}