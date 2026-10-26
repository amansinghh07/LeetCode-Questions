class Solution {
    Boolean[][] memo;

    public boolean isMatch(String s, String p) {
        memo = new Boolean[s.length() + 1][p.length() + 1];
        return solve(0, 0, s, p);
    }

    private boolean solve(int i, int j, String s, String p) {

        // Pattern is exhausted
        if (j == p.length()) {
            return i == s.length();
        }

        if (memo[i][j] != null) {
            return memo[i][j];
        }

        // Check whether current characters match
        boolean match = i < s.length() &&
                (s.charAt(i) == p.charAt(j) ||
                 p.charAt(j) == '.');

        boolean result;

        // Check whether the next pattern character is '*'
        if (j + 1 < p.length() && p.charAt(j + 1) == '*') {

            // Option 1: Use zero occurrences
            // Option 2: Consume one character and reuse the pattern
            result = solve(i, j + 2, s, p) ||
                     (match && solve(i + 1, j, s, p));

        } else {

            // No '*': both characters must match
            result = match &&
                     solve(i + 1, j + 1, s, p);
        }

        memo[i][j] = result;
        return result;
    }
}