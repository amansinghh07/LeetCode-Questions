class Solution {
    public boolean checkValidString(String s) {

        int minOpen = 0;
        int maxOpen = 0;

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                minOpen++;
                maxOpen++;
            }

            else if (ch == ')') {
                minOpen--;
                maxOpen--;
            }

            else { // '*'
                minOpen--;
                maxOpen++;
            }

            // We cannot have negative minimum
            minOpen = Math.max(0, minOpen);

            // Even the maximum possibility is negative
            // means we have too many ')'
            if (maxOpen < 0) {
                return false;
            }
        }

        // There must be a possibility with exactly 0 open brackets
        return minOpen == 0;
    }
}