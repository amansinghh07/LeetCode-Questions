class Solution {
    public String removeDuplicateLetters(String s) {

        int[] freq = new int[26];
        boolean[] used = new boolean[26];

        // Count frequency
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        StringBuilder stack = new StringBuilder();

        for (char ch : s.toCharArray()) {

            int idx = ch - 'a';

            // Current character is consumed
            freq[idx]--;

            // Already present in stack
            if (used[idx]) {
                continue;
            }

            // Remove larger characters if they appear later
            while (stack.length() > 0) {

                char top = stack.charAt(stack.length() - 1);

                if (top <= ch) {
                    break;
                }

                // top will not appear again
                if (freq[top - 'a'] == 0) {
                    break;
                }

                // Remove top
                stack.deleteCharAt(stack.length() - 1);
                used[top - 'a'] = false;
            }

            stack.append(ch);
            used[idx] = true;
        }

        return stack.toString();
    }
}