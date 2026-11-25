
import java.util.*;

class Solution {

    public int smallestUniqueSubarray(int[] nums) {

        int n = nums.length;

        // Step 1: Build suffix array
        Integer[] sa = new Integer[n];

        for (int i = 0; i < n; i++) {
            sa[i] = i;
        }

        int[] rank = nums.clone();
        int[] newRank = new int[n];

        for (int k = 1; k < n; k *= 2) {

            final int len = k;
            final int[] r = rank;

            Arrays.sort(sa, (a, b) -> {

                if (r[a] != r[b]) {
                    return Integer.compare(r[a], r[b]);
                }

                int ra = a + len < n ? r[a + len] : -1;
                int rb = b + len < n ? r[b + len] : -1;

                return Integer.compare(ra, rb);
            });

            newRank[sa[0]] = 0;

            for (int i = 1; i < n; i++) {

                int a = sa[i - 1];
                int b = sa[i];

                int a1 = rank[a];
                int b1 = rank[b];

                int a2 = a + k < n ? rank[a + k] : -1;
                int b2 = b + k < n ? rank[b + k] : -1;

                newRank[b] = newRank[a];

                if (a1 != b1 || a2 != b2) {
                    newRank[b]++;
                }
            }

            rank = newRank.clone();

            if (rank[sa[n - 1]] == n - 1) {
                break;
            }
        }

        // Step 2: Build inverse suffix array
        int[] pos = new int[n];

        for (int i = 0; i < n; i++) {
            pos[sa[i]] = i;
        }

        // Step 3: Kasai's LCP algorithm
        int[] maxLcp = new int[n];

        int h = 0;

        for (int i = 0; i < n; i++) {

            int r = pos[i];

            if (r == 0) {
                h = 0;
                continue;
            }

            int j = sa[r - 1];

            while (i + h < n &&
                   j + h < n &&
                   nums[i + h] == nums[j + h]) {
                h++;
            }

            maxLcp[i] = Math.max(maxLcp[i], h);
            maxLcp[j] = Math.max(maxLcp[j], h);

            if (h > 0) {
                h--;
            }
        }

        // Step 4: Find minimum unique subarray
        int ans = n;

        for (int i = 0; i < n; i++) {

            int uniqueLength = maxLcp[i] + 1;

            if (uniqueLength <= n - i) {
                ans = Math.min(ans, uniqueLength);
            }
        }

        return ans;
    }
}
