class Solution {
    public int maximumLength(int[] nums) {

        Map<Long, Integer> freq = new HashMap<>();

        for (int x : nums) {
            freq.put((long) x, freq.getOrDefault((long) x, 0) + 1);
        }

        int ans = 1;

        // Special case for 1
        if (freq.containsKey(1L)) {
            int cnt = freq.get(1L);
            ans = Math.max(ans, (cnt % 2 == 0) ? cnt - 1 : cnt);
        }

        for (long start : freq.keySet()) {

            if (start == 1L)
                continue;

            long cur = start;
            int len = 0;
            boolean noNext = false;

            while (freq.getOrDefault(cur, 0) >= 2) {

                len += 2;

                long next = cur * cur;

                // No integer in nums can be larger than 1e9
                if (next > 1_000_000_000L || !freq.containsKey(next)) {
                    noNext = true;
                    break;
                }

                cur = next;
            }

            if (noNext) {
                len--; // last pair becomes the peak
            } else if (freq.getOrDefault(cur, 0) == 1) {
                len++; // current value is the peak
            }

            ans = Math.max(ans, len);
        }

        return ans;
    }
}