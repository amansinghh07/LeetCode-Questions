class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length(), m = word2.length();

        // suf[i] = smallest j such that word2[j:] is an exact subsequence of word1[i:]
        int[] suf = new int[n + 1];
        suf[n] = m;
        for (int i = n - 1; i >= 0; i--) {
            suf[i] = suf[i + 1];
            if (suf[i] > 0 && word1.charAt(i) == word2.charAt(suf[i] - 1)) {
                suf[i]--;
            }
        }

        int[] result = new int[m];
        int idx = 0, i = 0, k = 0;
        boolean changed = false;

        while (i < n && k < m) {
            if (word1.charAt(i) == word2.charAt(k)) {
                result[idx++] = i;
                i++; k++;
            } else if (!changed && suf[i + 1] <= k + 1) {
                result[idx++] = i;
                changed = true;
                i++; k++;
            } else {
                i++;
            }
        }

        return (k == m) ? result : new int[0];
    }
}