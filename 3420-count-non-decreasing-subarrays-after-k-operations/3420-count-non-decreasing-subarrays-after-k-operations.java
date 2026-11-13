
import java.util.*;

class Solution {
    public long countNonDecreasingSubarrays(int[] nums, int k) {

        int n = nums.length;
        int LOG = 18;

        // Step 1: Prefix sum
        long[] pref = new long[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        // Step 2: Next Greater Element
        int[] next = new int[n];
        Arrays.fill(next, n);

        Stack<Integer> st = new Stack<>();

        for (int i = n - 1; i >= 0; i--) {

            while (!st.isEmpty() &&
                   nums[st.peek()] <= nums[i]) {
                st.pop();
            }

            if (!st.isEmpty()) {
                next[i] = st.peek();
            }

            st.push(i);
        }

        // Step 3: Cost of each complete block
        long[] blockCost = new long[n];

        for (int i = 0; i < n; i++) {

            int end = next[i];

            blockCost[i] =
                (long) nums[i] * (end - i)
                - (pref[end] - pref[i]);
        }

        // Step 4: Binary lifting
        int[][] up = new int[LOG][n];
        long[][] cost = new long[LOG][n];

        for (int i = 0; i < n; i++) {
            up[0][i] = next[i];
            cost[0][i] = blockCost[i];
        }

        for (int j = 1; j < LOG; j++) {

            for (int i = 0; i < n; i++) {

                int mid = up[j - 1][i];

                if (mid < n) {

                    up[j][i] = up[j - 1][mid];

                    cost[j][i] =
                        cost[j - 1][i] +
                        cost[j - 1][mid];

                } else {
                    up[j][i] = n;
                }
            }
        }

        // Step 5: Calculate valid subarrays
        long ans = 0;

        for (int l = 0; l < n; l++) {

            int curr = l;
            long remaining = k;

            // Skip complete blocks using binary lifting
            for (int j = LOG - 1; j >= 0; j--) {

                if (up[j][curr] < n &&
                    cost[j][curr] <= remaining) {

                    remaining -= cost[j][curr];
                    curr = up[j][curr];
                }
            }

            // Binary search inside the current block
            int low = curr;
            int high = next[curr] - 1;
            int best = curr;

            while (low <= high) {

                int mid = low + (high - low) / 2;

                long required =
                    (long) nums[curr] * (mid - curr + 1)
                    - (pref[mid + 1] - pref[curr]);

                if (required <= remaining) {
                    best = mid;
                    low = mid + 1;
                } else {
                    high = mid - 1;
                }
            }

            ans += best - l + 1;
        }

        return ans;
    }
}
