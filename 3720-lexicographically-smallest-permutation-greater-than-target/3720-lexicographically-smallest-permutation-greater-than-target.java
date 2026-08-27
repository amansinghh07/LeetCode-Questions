class Solution {
    public String lexGreaterPermutation(String s, String target) {

        int n = s.length();

        // Try making the answer greater at position i.
        // Start from the RIGHT because we want to keep
        // the longest possible prefix equal to target.
        for (int i = n - 1; i >= 0; i--) {

            int[] freq = new int[26];

            // Count all characters of s
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Use target[0 ... i-1] as the same prefix.
            boolean possible = true;

            for (int j = 0; j < i; j++) {

                int index = target.charAt(j) - 'a';

                if (freq[index] == 0) {
                    possible = false;
                    break;
                }

                freq[index]--;
            }

            // If target prefix cannot be formed,
            // this position cannot be our pivot.
            if (!possible) {
                continue;
            }

            // At position i, we need the smallest
            // character greater than target[i].
            int targetChar = target.charAt(i) - 'a';

            for (int c = targetChar + 1; c < 26; c++) {

                if (freq[c] > 0) {

                    StringBuilder ans = new StringBuilder();

                    // 1. Keep prefix same as target
                    ans.append(target, 0, i);

                    // 2. Make current character greater
                    ans.append((char) ('a' + c));

                    freq[c]--;

                    // 3. Put remaining characters
                    //    in smallest possible order
                    for (int j = 0; j < 26; j++) {
                        while (freq[j] > 0) {
                            ans.append((char) ('a' + j));
                            freq[j]--;
                        }
                    }

                    return ans.toString();
                }
            }
        }

        return "";
    }
}