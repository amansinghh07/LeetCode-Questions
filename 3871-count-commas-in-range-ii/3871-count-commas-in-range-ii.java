class Solution {
    public long countCommas(long n) {
        long count = 0;
        long threshold = 1000;

        while (threshold <= n) {
            count += n - threshold + 1;
            threshold *= 1000;
        }

        return count;
    }
}