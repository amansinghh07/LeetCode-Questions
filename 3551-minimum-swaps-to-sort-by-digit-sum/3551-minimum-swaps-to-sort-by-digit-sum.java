
import java.util.*;

class Solution {
    public int minSwaps(int[] nums) {
        int n = nums.length;
        int[][] digitSum = new int[n][2];

        for (int i = 0; i < n; i++) {
            digitSum[i][0] = nums[i];
            digitSum[i][1] = sumOfDigit(nums[i]);
        }

        Arrays.sort(digitSum, (a, b) -> {
            if (a[1] != b[1]) {
                return Integer.compare(a[1], b[1]);
            }
            return Integer.compare(a[0], b[0]);
        });

        Map<Integer, Integer> targetIndex = new HashMap<>();

        for (int i = 0; i < n; i++) {
            targetIndex.put(digitSum[i][0], i);
        }

        boolean[] visited = new boolean[n];
        int swaps = 0;

        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int j = i;
            int cycleLength = 0;

            while (!visited[j]) {
                visited[j] = true;
                cycleLength++;
                j = targetIndex.get(nums[j]);
            }

            if (cycleLength > 1) {
                swaps += cycleLength - 1;
            }
        }

        return swaps;
    }

    private int sumOfDigit(int num) {
        int sum = 0;

        while (num > 0) {
            sum += num % 10;
            num /= 10;
        }

        return sum;
    }
}
