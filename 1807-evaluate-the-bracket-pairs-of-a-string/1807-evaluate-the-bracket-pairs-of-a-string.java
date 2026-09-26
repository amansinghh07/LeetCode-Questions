class Solution {
    public String evaluate(String s, List<List<String>> knowledge) {

        Map<String, String> map = new HashMap<>();

        // Store key -> value
        for (List<String> pair : knowledge) {
            map.put(pair.get(0), pair.get(1));
        }

        StringBuilder ans = new StringBuilder();

        for (int i = 0; i < s.length(); i++) {

            if (s.charAt(i) == '(') {

                int j = i + 1;

                // Find closing bracket
                while (s.charAt(j) != ')') {
                    j++;
                }

                String key = s.substring(i + 1, j);

                // Get value or ?
                ans.append(map.getOrDefault(key, "?"));

                // Skip everything up to ')'
                i = j;

            } else {
                ans.append(s.charAt(i));
            }
        }

        return ans.toString();
    }
}