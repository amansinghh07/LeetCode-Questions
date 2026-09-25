class Solution {

    private int i;

    public List<String> braceExpansionII(String expression) {
        i = 0;

        Set<String> result = parseExpression(expression);

        List<String> ans = new ArrayList<>(result);
        Collections.sort(ans);

        return ans;
    }

    // Handles UNION: expr1, expr2, expr3
    private Set<String> parseExpression(String s) {
        Set<String> result = parseConcat(s);

        while (i < s.length() && s.charAt(i) == ',') {
            i++; // skip ','

            Set<String> next = parseConcat(s);

            result.addAll(next);
        }

        return result;
    }

    // Handles CONCATENATION: expr1expr2expr3
    private Set<String> parseConcat(String s) {
        Set<String> result = new HashSet<>();
        result.add("");

        while (i < s.length()
                && s.charAt(i) != '}'
                && s.charAt(i) != ',') {

            Set<String> next = parseTerm(s);

            Set<String> temp = new HashSet<>();

            for (String a : result) {
                for (String b : next) {
                    temp.add(a + b);
                }
            }

            result = temp;
        }

        return result;
    }

    // Handles either:
    //   a
    //   { ... }
    private Set<String> parseTerm(String s) {

        if (s.charAt(i) == '{') {
            i++; // skip '{'

            Set<String> result = parseExpression(s);

            i++; // skip '}'

            return result;
        }

        // single character
        Set<String> result = new HashSet<>();

        result.add(String.valueOf(s.charAt(i)));

        i++;

        return result;
    }
}