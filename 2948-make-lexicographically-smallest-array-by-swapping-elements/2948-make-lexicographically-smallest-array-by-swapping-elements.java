class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {

        int n = nums.length;

        // Store {value, originalIndex}
        int[][] arr = new int[n][2];

        for (int i = 0; i < n; i++) {
            arr[i][0] = nums[i];
            arr[i][1] = i;
        }

        // Sort by value
        Arrays.sort(arr, (a, b) -> Integer.compare(a[0], b[0]));

        int start = 0;

        while (start < n) {

            int end = start;

            // Find one connected group.
            while (end + 1 < n &&
                   arr[end + 1][0] - arr[end][0] <= limit) {
                end++;
            }

            /*
             * arr[start...end] belongs to the same group.
             *
             * Values are already sorted.
             * We need the original indices sorted as well.
             */
            int size = end - start + 1;

            int[] indices = new int[size];

            for (int i = 0; i < size; i++) {
                indices[i] = arr[start + i][1];
            }

            Arrays.sort(indices);

            /*
             * Smallest value goes to smallest index,
             * second smallest value goes to second smallest index,
             * etc.
             */
            for (int i = 0; i < size; i++) {
                nums[indices[i]] = arr[start + i][0];
            }

            start = end + 1;
        }

        return nums;
    }
}