class Solution {
    public String reverseParentheses(String s) {

        Stack<StringBuilder> stack = new Stack<>();

        StringBuilder current = new StringBuilder();

        for (char ch : s.toCharArray()) {

            if (ch == '(') {
                // Save the string before entering parentheses
                stack.push(current);

                // Start a fresh string for inside parentheses
                current = new StringBuilder();

            } else if (ch == ')') {

                // Reverse the content inside parentheses
                current.reverse();

                // Get the string before '('
                StringBuilder previous = stack.pop();

                // Combine them
                previous.append(current);

                current = previous;

            } else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}