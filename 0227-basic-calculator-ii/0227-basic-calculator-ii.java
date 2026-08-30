class Solution {
    public int calculate(String s) {

        Stack<Integer> stack = new Stack<>();

        int num = 0;
        char prevOperator = '+';

        for (int i = 0; i < s.length(); i++) {

            char ch = s.charAt(i);

            // Build the number
            if (Character.isDigit(ch)) {
                num = num * 10 + (ch - '0');
            }

            // Process operator
            if ((!Character.isDigit(ch) && ch != ' ') || i == s.length() - 1) {

                if (prevOperator == '+') {
                    stack.push(num);

                } else if (prevOperator == '-') {
                    stack.push(-num);

                } else if (prevOperator == '*') {
                    stack.push(stack.pop() * num);

                } else if (prevOperator == '/') {
                    stack.push(stack.pop() / num);
                }

                prevOperator = ch;
                num = 0;
            }
        }

        int result = 0;

        for (int value : stack) {
            result += value;
        }

        return result;
    }
}