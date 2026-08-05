class Solution {

    private static final int MOD = 1_000_000_007;

    public int countGoodNumbers(long n) {

        long even = (n + 1) / 2;
        long odd = n / 2;

        long ans = power(5, even);
        ans = (ans * power(4, odd)) % MOD;

        return (int) ans;
    }

    private long power(long base, long exp) {

        long ans = 1;
        base %= MOD;

        while (exp > 0) {

            if ((exp & 1) == 1) {
                ans = (ans * base) % MOD;
            }

            base = (base * base) % MOD;
            exp >>= 1;
        }

        return ans;
    }
}