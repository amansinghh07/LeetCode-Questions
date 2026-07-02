class Solution {
    public String countAndSay(int n) {
        if (n == 1) return "1";
        String prev = countAndSay(n - 1);
        int len = prev.length();
        String ans = ""; 
        int count = 1;  
        for (int i = 1; i < len; i++) {
            if (prev.charAt(i) == prev.charAt(i - 1)) count++;
            else {
                ans += (char) ('0' + count); // Add frequency
                ans += prev.charAt(i - 1); // Add the digit 
                count = 1; // Reset counter to 1
            }
        }
        ans += (char) ('0' + count);
        ans += prev.charAt(len - 1); 
        
        return ans; 
    }
}