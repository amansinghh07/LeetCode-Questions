class Solution {

    private Set<String> result = new HashSet<>();

    public List<String> removeInvalidParentheses(String s) {

        int leftRem = 0;
        int rightRem = 0;

        // Find minimum number of '(' and ')' to remove
        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                leftRem++;
            }

            else if (ch == ')') {

                if (leftRem > 0) {
                    leftRem--;
                } else {
                    rightRem++;
                }
            }
        }

        backtrack(
            s,
            0,
            leftRem,
            rightRem
        );

        return new ArrayList<>(result);
    }

    private void backtrack(
            String s,
            int start,
            int leftRem,
            int rightRem) {

        // If we have removed all required parentheses,
        // check whether the string is valid.
        if (leftRem == 0 && rightRem == 0) {

            if (isValid(s)) {
                result.add(s);
            }

            return;
        }

        for (int i = start; i < s.length(); i++) {

            // Skip duplicate removals
            if (i > start &&
                s.charAt(i) == s.charAt(i - 1)) {
                continue;
            }

            // Remove '('
            if (leftRem > 0 &&
                s.charAt(i) == '(') {

                String next =
                    s.substring(0, i) +
                    s.substring(i + 1);

                backtrack(
                    next,
                    i,
                    leftRem - 1,
                    rightRem
                );
            }

            // Remove ')'
            else if (rightRem > 0 &&
                     s.charAt(i) == ')') {

                String next =
                    s.substring(0, i) +
                    s.substring(i + 1);

                backtrack(
                    next,
                    i,
                    leftRem,
                    rightRem - 1
                );
            }
        }
    }

    private boolean isValid(String s) {

        int balance = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                balance++;
            }

            else if (ch == ')') {

                balance--;

                if (balance < 0) {
                    return false;
                }
            }
        }

        return balance == 0;
    }
}