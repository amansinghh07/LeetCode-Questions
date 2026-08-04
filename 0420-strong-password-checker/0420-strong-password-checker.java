class Solution {
    public int strongPasswordChecker(String password) {

        int n = password.length();

        boolean hasLower = false;
        boolean hasUpper = false;
        boolean hasDigit = false;

        // Check missing character types
        for (char c : password.toCharArray()) {
            if (Character.isLowerCase(c))
                hasLower = true;
            else if (Character.isUpperCase(c))
                hasUpper = true;
            else if (Character.isDigit(c))
                hasDigit = true;
        }

        int missingTypes = 0;
        if (!hasLower) missingTypes++;
        if (!hasUpper) missingTypes++;
        if (!hasDigit) missingTypes++;

        // Count replacements and repeating groups
        int replace = 0;

        // mod0 = groups where len % 3 == 0
        // mod1 = groups where len % 3 == 1
        int mod0 = 0;
        int mod1 = 0;

        for (int i = 0; i < n;) {

            int j = i;

            while (j < n && password.charAt(j) == password.charAt(i))
                j++;

            int len = j - i;

            if (len >= 3) {
                replace += len / 3;

                if (len % 3 == 0)
                    mod0++;
                else if (len % 3 == 1)
                    mod1++;
            }

            i = j;
        }

        // Case 1: Too short
        if (n < 6) {
            return Math.max(missingTypes, 6 - n);
        }

        // Case 2: Valid length
        if (n <= 20) {
            return Math.max(missingTypes, replace);
        }

        // Case 3: Too long
        int delete = n - 20;
        int remainDelete = delete;

        // First use deletions on groups where len % 3 == 0
        int use = Math.min(remainDelete, mod0);
        replace -= use;
        remainDelete -= use;

        // Then groups where len % 3 == 1
        use = Math.min(remainDelete / 2, mod1);
        replace -= use;
        remainDelete -= use * 2;

        // Finally remaining deletions
        use = remainDelete / 3;
        replace -= use;

        return delete + Math.max(missingTypes, replace);
    }
}