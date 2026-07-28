class Solution {

    public int maxValue(int n, int index, int maxSum) {

        int low = 1;
        int high = maxSum;
        int ans = 1;

        while (low <= high) {

            int mid = low + (high - low) / 2;

            long total =
                    calc(mid, index)
                    + calc(mid, n - index - 1)
                    + mid;

            if (total <= maxSum) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return ans;
    }

    private long calc(long peak, long count) {

        if (peak > count) {

            long last = peak - count;

            return (peak - 1 + last) * count / 2;

        } else {

            long decreasing =
                    (peak - 1) * peak / 2;

            long ones =
                    count - (peak - 1);

            return decreasing + ones;
        }
    }
}