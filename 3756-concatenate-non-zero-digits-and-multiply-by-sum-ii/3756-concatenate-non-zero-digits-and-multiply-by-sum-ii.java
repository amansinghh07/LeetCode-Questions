class Solution {
    private static final int MOD = 1_000_000_007;

    public int[] sumAndMultiply(String s, int[][] queries) {

        int n = s.length();

        // cnt[i] = number of non-zero digits before index i
        int[] cnt = new int[n + 1];

        // Prefix sum of non-zero digits
        ArrayList<Integer> sumPref = new ArrayList<>();
        sumPref.add(0);

        // Prefix concatenated value
        ArrayList<Long> numPref = new ArrayList<>();
        numPref.add(0L);

        // Collect non-zero digits
        for (int i = 0; i < n; i++) {
            cnt[i + 1] = cnt[i];

            int d = s.charAt(i) - '0';

            if (d != 0) {
                cnt[i + 1]++;

                sumPref.add(sumPref.get(sumPref.size() - 1) + d);

                long val = (numPref.get(numPref.size() - 1) * 10 + d) % MOD;
                numPref.add(val);
            }
        }

        int m = numPref.size() - 1;

        // powers of 10
        long[] pow10 = new long[m + 1];
        pow10[0] = 1;

        for (int i = 1; i <= m; i++) {
            pow10[i] = (pow10[i - 1] * 10) % MOD;
        }

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {

            int l = queries[i][0];
            int r = queries[i][1];

            int left = cnt[l];
            int right = cnt[r + 1];

            if (left == right) {
                ans[i] = 0;
                continue;
            }

            int len = right - left;

            int digitSum = sumPref.get(right) - sumPref.get(left);

            long number =
                    (numPref.get(right)
                    - (numPref.get(left) * pow10[len]) % MOD
                    + MOD) % MOD;

            ans[i] = (int)((number * digitSum) % MOD);
        }

        return ans;
    }
}