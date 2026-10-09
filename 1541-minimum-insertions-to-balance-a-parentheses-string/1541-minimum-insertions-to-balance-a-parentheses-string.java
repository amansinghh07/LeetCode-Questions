class Solution {
    public int minInsertions(String s) {

        int open = 0;
        int insertions = 0;

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Opening parenthesis
            if (ch == '(') {
                open++;
            }

            // Closing parenthesis
            else {

                // We need another ')' to form "))"
                if (i + 1 < s.length() && s.charAt(i + 1) == ')') {
                    i++; // consume the second ')'
                } else {
                    // Missing one ')'
                    insertions++;
                }

                // Now we have a complete "))"
                if (open > 0) {
                    open--;
                } else {
                    // No '(' available, so insert one
                    insertions++;
                }
            }
        }

        // Every remaining '(' needs two ')'
        insertions += open * 2;

        return insertions;
    }
}