class Solution {
    public int numOfSubarrays(int[] arr) {
        final int MOD = 1_000_000_007;
        int even = 1;      // the empty prefix (sum 0) counts as even
        int odd = 0;
        int parity = 0;    // parity of the running prefix sum
        long res = 0;

        for (int x : arr) {
            parity = (parity + x) & 1;
            if (parity == 1) {
                res += even;   // odd prefix minus an even prefix = odd sum
                odd++;
            } else {
                res += odd;    // even prefix minus an odd prefix = odd sum
                even++;
            }
        }
        return (int) (res % MOD);
    }
}