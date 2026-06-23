class Solution {

    private static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        if (n == 1) {
            return m;
        }

        long[] inc = new long[m];
        long[] dec = new long[m];

        // length = 2
        for (int a = 0; a < m; a++) {
            for (int b = 0; b < m; b++) {

                if (a == b) continue;

                if (a < b) {
                    inc[b]++;
                } else {
                    dec[b]++;
                }
            }
        }

        for (int len = 3; len <= n; len++) {

            long[] newInc = new long[m];
            long[] newDec = new long[m];

            long[] prefixDec = new long[m];
            prefixDec[0] = dec[0];

            for (int i = 1; i < m; i++) {
                prefixDec[i] =
                        (prefixDec[i - 1] + dec[i]) % MOD;
            }

            long[] suffixInc = new long[m];
            suffixInc[m - 1] = inc[m - 1];

            for (int i = m - 2; i >= 0; i--) {
                suffixInc[i] =
                        (suffixInc[i + 1] + inc[i]) % MOD;
            }

            for (int v = 0; v < m; v++) {

                if (v > 0) {
                    newInc[v] = prefixDec[v - 1];
                }

                if (v < m - 1) {
                    newDec[v] = suffixInc[v + 1];
                }
            }

            inc = newInc;
            dec = newDec;
        }

        long ans = 0;

        for (long x : inc) {
            ans = (ans + x) % MOD;
        }

        for (long x : dec) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }
}