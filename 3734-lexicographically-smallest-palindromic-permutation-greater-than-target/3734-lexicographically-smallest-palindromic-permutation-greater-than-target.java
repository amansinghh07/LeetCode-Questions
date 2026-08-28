class Solution {
    public String lexPalindromicPermutation(String s, String target) {

        int n = s.length();
        int m = n / 2;

        // Count characters.
        int[] freq = new int[26];
        for (char ch : s.toCharArray()) {
            freq[ch - 'a']++;
        }

        // A palindrome can exist only if at most one character
        // has an odd frequency.
        int odd = 0;
        for (int f : freq) {
            if (f % 2 == 1) {
                odd++;
            }
        }

        if (odd > 1) {
            return "";
        }

        // Count of characters available in the first half.
        int[] halfCount = new int[26];
        for (int i = 0; i < 26; i++) {
            halfCount[i] = freq[i] / 2;
        }

        String targetHalf = target.substring(0, m);

        /*
         * First check whether targetHalf itself can be formed.
         *
         * If yes, its palindrome may already be > target.
         */
        int[] temp = halfCount.clone();

        boolean canMakeTargetHalf = true;

        for (int i = 0; i < m; i++) {
            int c = targetHalf.charAt(i) - 'a';

            if (temp[c] == 0) {
                canMakeTargetHalf = false;
                break;
            }

            temp[c]--;
        }

        if (canMakeTargetHalf) {
            String palindrome = buildPalindrome(targetHalf, freq);

            if (palindrome.compareTo(target) > 0) {
                return palindrome;
            }
        }

        /*
         * Now we need the smallest half that is strictly greater
         * than targetHalf.
         *
         * We scan from left to right while matching targetHalf.
         * At every position, we remember the possibility of putting
         * a character greater than targetHalf[i].
         *
         * The rightmost such position gives the smallest answer.
         */
        int[] remaining = halfCount.clone();

        String bestHalf = null;

        for (int i = 0; i < m; i++) {

            int targetChar = targetHalf.charAt(i) - 'a';

            /*
             * Try making the current position strictly greater.
             *
             * Since we want the smallest answer, choose the smallest
             * available character > targetChar.
             */
            for (int c = targetChar + 1; c < 26; c++) {

                if (remaining[c] > 0) {

                    StringBuilder candidate = new StringBuilder();

                    // Prefix remains equal to targetHalf.
                    candidate.append(targetHalf, 0, i);

                    // Make this position greater.
                    candidate.append((char) ('a' + c));

                    // Remaining characters after using c.
                    int[] rest = remaining.clone();
                    rest[c]--;

                    // Put everything else in sorted order.
                    appendSorted(candidate, rest);

                    bestHalf = candidate.toString();

                    /*
                     * For this i, the smallest possible candidate
                     * is obtained using the smallest greater char.
                     *
                     * Since later i gives a smaller lexicographic
                     * result, we continue searching.
                     */
                    break;
                }
            }

            /*
             * To continue matching targetHalf, we must have the
             * exact target character available.
             */
            if (remaining[targetChar] == 0) {
                break;
            }

            remaining[targetChar]--;
        }

        if (bestHalf == null) {
            return "";
        }

        return buildPalindrome(bestHalf, freq);
    }

    // Builds the full palindrome from its first half.
    private String buildPalindrome(String half, int[] freq) {

        StringBuilder ans = new StringBuilder();

        ans.append(half);

        // Middle character exists only for odd length.
        if ((freq.length > 0)) {
            for (int i = 0; i < 26; i++) {
                if (freq[i] % 2 == 1) {
                    ans.append((char) ('a' + i));
                    break;
                }
            }
        }

        // Mirror the first half.
        for (int i = half.length() - 1; i >= 0; i--) {
            ans.append(half.charAt(i));
        }

        return ans.toString();
    }

    // Append remaining characters in sorted order.
    private void appendSorted(StringBuilder sb, int[] count) {

        for (int c = 0; c < 26; c++) {
            while (count[c] > 0) {
                sb.append((char) ('a' + c));
                count[c]--;
            }
        }
    }
}