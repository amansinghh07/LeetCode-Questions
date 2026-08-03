import java.util.HashMap;
import java.util.Map;
class Solution {
    public String minWindow(String s, String t) {
        Map<Character, Integer> charCount = new HashMap<>();
        int left = 0, right = 0;
        int minLength = Integer.MAX_VALUE, minWindowStart = 0;
        int requiredChars = t.length();
        // Initialize charCount with character frequencies in string t
        for (char c : t.toCharArray()) {
            charCount.put(c, charCount.getOrDefault(c, 0) + 1);
        }

        while (right < s.length()) {
            // Expand the window
            char currentChar = s.charAt(right);
            if (charCount.containsKey(currentChar)) {
                if (charCount.get(currentChar) > 0) {
                    requiredChars--;
                }
                charCount.put(currentChar, charCount.get(currentChar) - 1);
            }

            // Contract the window
            while (requiredChars == 0) {
                // Update the minimum window
                if (right - left < minLength) {
                    minLength = right - left;
                    minWindowStart = left;
                }

                // Shrink the window from the left
                char leftChar = s.charAt(left);
                if (charCount.containsKey(leftChar)) {
                    charCount.put(leftChar, charCount.get(leftChar) + 1);
                    if (charCount.get(leftChar) > 0) {
                        requiredChars++;
                    }
                }

                left++;
            }

            right++;
        }

        return minLength == Integer.MAX_VALUE ? "" : s.substring(minWindowStart, minWindowStart + minLength + 1);
    }
}