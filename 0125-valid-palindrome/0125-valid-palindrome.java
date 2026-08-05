class Solution {

    public boolean isPalindrome(String s) {
        return helper(s, 0, s.length() - 1);
    }

    private boolean helper(String s, int left, int right) {

        // Base case
        if (left >= right)
            return true;

        // Skip non-alphanumeric from left
        if (!Character.isLetterOrDigit(s.charAt(left)))
            return helper(s, left + 1, right);

        // Skip non-alphanumeric from right
        if (!Character.isLetterOrDigit(s.charAt(right)))
            return helper(s, left, right - 1);

        // Compare after converting to lowercase
        if (Character.toLowerCase(s.charAt(left)) !=
            Character.toLowerCase(s.charAt(right)))
            return false;

        return helper(s, left + 1, right - 1);
    }
}