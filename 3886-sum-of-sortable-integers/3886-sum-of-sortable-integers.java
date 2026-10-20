class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int ans = 0;

        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;

            boolean sortable = true;
            int prevMax = Integer.MIN_VALUE;

            for (int start = 0; start < n && sortable; start += k) {
                int descents = 0;
                int min = Integer.MAX_VALUE;
                int max = Integer.MIN_VALUE;

                for (int i = start; i < start + k; i++) {
                    int next = (i + 1 == start + k) ? start : i + 1;

                    if (nums[i] > nums[next]) descents++;

                    min = Math.min(min, nums[i]);
                    max = Math.max(max, nums[i]);
                }

                // block must be a rotation of a sorted sequence
                if (descents > 1) {
                    sortable = false;
                    break;
                }

                // after sorting this block, it must not go below the previous block's max
                if (min < prevMax) {
                    sortable = false;
                    break;
                }

                prevMax = max;
            }

            if (sortable) ans += k;
        }

        return ans;
    }
}