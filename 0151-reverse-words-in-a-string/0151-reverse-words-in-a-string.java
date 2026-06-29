class Solution {
       public String reverseWords(String s) {
        int n = s.length(); 
        List<String> words = new ArrayList<>();
        int start, end;
        
        int i = 0;
        while (i < n) {
            
            while (i < n && s.charAt(i) == ' ') i++;
            
            if (i >= n) break;
            
            start = i;
            while (i < n && s.charAt(i) != ' ') i++;
            
            end = i - 1; 
            String wordFound = s.substring(start, end + 1);
            words.add(wordFound);
        }
        
        StringBuilder ans = new StringBuilder();
        for (int j = words.size() - 1; j >= 0; j--) {
            ans.append(words.get(j));
            
            // Adding spaces in between words
            if (j != 0) ans.append(' ');
        }
        
        return ans.toString(); // Return the stored result
    }
}