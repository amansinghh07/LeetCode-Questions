
class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;

        // Case 1: nums is a left rotation of [0, 1, ..., n-1]
        int k = nums[0];
        boolean rotation = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != (i + k) % n) {
                rotation = false;
                break;
            }
        }

        if (rotation) {
            if (k == 0) return 0;
            return Math.min(n - k, k + 2);
        }

        // Case 2: nums is a left rotation of [n-1, ..., 1, 0]
        k = n - 1 - nums[0];
        boolean reversedRotation = true;

        for (int i = 0; i < n; i++) {
            if (nums[i] != n - 1 - ((i + k) % n)) {
                reversedRotation = false;
                break;
            }
        }

        if (reversedRotation) {
            return Math.min(k + 1, n - k + 1);
        }

        return -1;
    }
}
