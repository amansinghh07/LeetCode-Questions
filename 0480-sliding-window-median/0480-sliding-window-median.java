
import java.util.*;

class Solution {
    public double[] medianSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        double[] ans = new double[n - k + 1];

        PriorityQueue<Integer> small =
            new PriorityQueue<>(Collections.reverseOrder());
        PriorityQueue<Integer> large = new PriorityQueue<>();

        Map<Integer, Integer> delayed = new HashMap<>();
        int smallSize = 0, largeSize = 0;

        for (int i = 0; i < n; i++) {
            if (!small.isEmpty() && nums[i] <= small.peek()) {
                small.offer(nums[i]);
                smallSize++;
            } else {
                large.offer(nums[i]);
                largeSize++;
            }

            // Balance heaps: small has equal size or one extra element.
            if (smallSize > largeSize + 1) {
                large.offer(small.poll());
                smallSize--;
                largeSize++;
            } else if (smallSize < largeSize) {
                small.offer(large.poll());
                smallSize++;
                largeSize--;
            }

            if (i >= k) {
                int out = nums[i - k];
                delayed.put(out, delayed.getOrDefault(out, 0) + 1);

                if (out <= small.peek()) {
                    smallSize--;
                } else {
                    largeSize--;
                }

                // Remove expired elements from heap tops.
                while (!small.isEmpty()
                        && delayed.getOrDefault(small.peek(), 0) > 0) {
                    int x = small.poll();
                    delayed.put(x, delayed.get(x) - 1);
                }

                while (!large.isEmpty()
                        && delayed.getOrDefault(large.peek(), 0) > 0) {
                    int x = large.poll();
                    delayed.put(x, delayed.get(x) - 1);
                }

                // Rebalance after removal.
                if (smallSize > largeSize + 1) {
                    large.offer(small.poll());
                    smallSize--;
                    largeSize++;
                } else if (smallSize < largeSize) {
                    small.offer(large.poll());
                    smallSize++;
                    largeSize--;
                }
            }

            if (i >= k - 1) {
                // Prune stale top elements before reading the median.
                while (!small.isEmpty()
                        && delayed.getOrDefault(small.peek(), 0) > 0) {
                    int x = small.poll();
                    delayed.put(x, delayed.get(x) - 1);
                }

                while (!large.isEmpty()
                        && delayed.getOrDefault(large.peek(), 0) > 0) {
                    int x = large.poll();
                    delayed.put(x, delayed.get(x) - 1);
                }

                if (k % 2 == 1) {
                    ans[i - k + 1] = small.peek();
                } else {
                    ans[i - k + 1] =
                        ((double) small.peek() + large.peek()) / 2.0;
                }
            }
        }

        return ans;
    }
}
