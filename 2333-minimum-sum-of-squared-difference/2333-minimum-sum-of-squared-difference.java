class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2,
                                 int k1, int k2) {

        long k = (long) k1 + k2;

        int[] freq = new int[100001];

        int maxDiff = 0;

        for (int i = 0; i < nums1.length; i++) {
            int diff = Math.abs(nums1[i] - nums2[i]);

            freq[diff]++;
            maxDiff = Math.max(maxDiff, diff);
        }

        // Reduce the largest differences first
        for (int d = maxDiff; d > 0 && k > 0; d--) {

            if (freq[d] == 0) {
                continue;
            }

            long count = freq[d];

            // Operations required to move all d's to d-1
            long operations = count;

            if (k >= operations) {

                // Move all d -> d-1
                freq[d - 1] += freq[d];
                freq[d] = 0;

                k -= operations;

            } else {

                // We cannot move all of them.
                // Move k elements from d -> d-1.
                freq[d] -= (int) k;
                freq[d - 1] += (int) k;

                k = 0;
            }
        }

        long answer = 0;

        for (int d = 1; d <= 100000; d++) {
            answer += (long) freq[d] * d * d;
        }

        return answer;
    }
}