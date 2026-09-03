class Solution {
    public String decodeString(String s) {

        Stack<Integer> countStack = new Stack<>();
        Stack<String> stringStack = new Stack<>();

        StringBuilder current = new StringBuilder();
        int count = 0;

        for (char ch : s.toCharArray()) {

            // Build the number
            if (Character.isDigit(ch)) {
                count = count * 10 + (ch - '0');
            }

            // Start a new nested string
            else if (ch == '[') {
                countStack.push(count);
                stringStack.push(current.toString());

                count = 0;
                current = new StringBuilder();
            }

            // Finish the current nested string
            else if (ch == ']') {
                int repeat = countStack.pop();
                String previous = stringStack.pop();

                StringBuilder temp = new StringBuilder(previous);

                for (int i = 0; i < repeat; i++) {
                    temp.append(current);
                }

                current = temp;
            }

            // Normal character
            else {
                current.append(ch);
            }
        }

        return current.toString();
    }
}