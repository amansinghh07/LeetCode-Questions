class Solution {

    private static final long LIMIT = 1_000_000L;

    public String smallestPalindrome(String s, int k) {

        int[] freq = new int[26];

        for (char ch : s.toCharArray())
            freq[ch - 'a']++;

        int[] half = new int[26];
        int halfLen = 0;
        char mid = 0;

        for (int i = 0; i < 26; i++) {
            half[i] = freq[i] / 2;
            halfLen += half[i];

            if ((freq[i] & 1) == 1)
                mid = (char) ('a' + i);
        }

        if (countWays(half) < k)
            return "";

        StringBuilder first = new StringBuilder();

        for (int pos = 0; pos < halfLen; pos++) {

            for (int ch = 0; ch < 26; ch++) {

                if (half[ch] == 0)
                    continue;

                half[ch]--;

                long cnt = countWays(half);

                if (cnt >= k) {
                    first.append((char) ('a' + ch));
                    break;
                }

                k -= cnt;
                half[ch]++;
            }
        }

        StringBuilder ans = new StringBuilder();

        ans.append(first);

        if (mid != 0)
            ans.append(mid);

        ans.append(new StringBuilder(first).reverse());

        return ans.toString();
    }

    private long countWays(int[] freq) {

        int total = 0;

        for (int x : freq)
            total += x;

        long ways = 1;
        int remaining = total;

        for (int x : freq) {

            if (x == 0)
                continue;

            ways = multiplyCap(ways, combination(remaining, x));

            if (ways >= LIMIT)
                return LIMIT;

            remaining -= x;
        }

        return ways;
    }

    private long multiplyCap(long a, long b) {

        if (a == 0 || b == 0)
            return 0;

        if (a > LIMIT / b)
            return LIMIT;

        long res = a * b;

        return Math.min(res, LIMIT);
    }

    private long combination(int n, int r) {

        if (r > n)
            return 0;

        r = Math.min(r, n - r);

        long res = 1;

        for (int i = 1; i <= r; i++) {

            long num = n - r + i;
            long den = i;

            long g = gcd(num, den);
            num /= g;
            den /= g;

            g = gcd(res, den);
            res /= g;
            den /= g;

            if (res > LIMIT / num)
                return LIMIT;

            res *= num;
            res /= den;

            if (res >= LIMIT)
                return LIMIT;
        }

        return res;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long t = a % b;
            a = b;
            b = t;
        }

        return a;
    }
}