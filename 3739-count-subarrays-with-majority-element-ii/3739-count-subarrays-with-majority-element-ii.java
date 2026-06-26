import java.util.*;

class Solution {

    class Fenwick {
        int[] bit;

        Fenwick(int n) {
            bit = new int[n + 2];
        }

        void update(int idx, int val) {
            while (idx < bit.length) {
                bit[idx] += val;
                idx += idx & -idx;
            }
        }

        int query(int idx) {
            int sum = 0;
            while (idx > 0) {
                sum += bit[idx];
                idx -= idx & -idx;
            }
            return sum;
        }
    }

    public long countMajoritySubarrays(int[] nums, int target) {

        int n = nums.length;

        int[] pref = new int[n + 1];

        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + (nums[i] == target ? 1 : -1);
        }

        int[] sorted = pref.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> map = new HashMap<>();
        int rank = 1;

        for (int x : sorted) {
            if (!map.containsKey(x))
                map.put(x, rank++);
        }

        Fenwick bit = new Fenwick(rank + 2);

        long ans = 0;

        for (int x : pref) {
            int idx = map.get(x);

            // count previous prefix sums strictly smaller
            ans += bit.query(idx - 1);

            bit.update(idx, 1);
        }

        return ans;
    }
}