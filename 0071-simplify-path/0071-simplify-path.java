class Solution {
    public String simplifyPath(String path) {
        Stack<String> stack = new Stack<>();

        String[] parts = path.split("/");

        for (String part : parts) {

            // Ignore empty parts and "."
            if (part.equals("") || part.equals(".")) {
                continue;
            }

            // Go to parent directory
            if (part.equals("..")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            }

            // Normal directory/file name
            else {
                stack.push(part);
            }
        }

        StringBuilder result = new StringBuilder();

        for (String dir : stack) {
            result.append("/").append(dir);
        }

        return result.length() == 0 ? "/" : result.toString();
    }
}