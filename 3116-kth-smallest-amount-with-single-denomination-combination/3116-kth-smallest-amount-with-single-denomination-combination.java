class Solution {

    public long findKthSmallest(int[] coins, int k) {

        long low = 1;
        long high = (long) coins[0] * k;

        // The kth answer can never be greater than
        // k * smallest coin.
        for (int coin : coins) {
            high = Math.min(high, (long) coin * k);
        }

        while (low < high) {

            long mid = low + (high - low) / 2;

            if (count(mid, coins) >= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        return low;
    }

    private long count(long x, int[] coins) {

        int n = coins.length;
        long result = 0;

        // Try every non-empty subset of coins
        for (int mask = 1; mask < (1 << n); mask++) {

            long lcm = 1;
            int bits = 0;
            boolean valid = true;

            for (int i = 0; i < n; i++) {

                if ((mask & (1 << i)) != 0) {

                    bits++;

                    long gcd = gcd(lcm, coins[i]);

                    lcm = lcm / gcd * coins[i];

                    // This subset's LCM is already
                    // greater than x, so it contributes 0.
                    if (lcm > x) {
                        valid = false;
                        break;
                    }
                }
            }

            if (!valid) {
                continue;
            }

            long multiples = x / lcm;

            // Odd number of coins -> add
            // Even number of coins -> subtract
            if (bits % 2 == 1) {
                result += multiples;
            } else {
                result -= multiples;
            }
        }

        return result;
    }

    private long gcd(long a, long b) {

        while (b != 0) {
            long temp = a % b;
            a = b;
            b = temp;
        }

        return a;
    }
}