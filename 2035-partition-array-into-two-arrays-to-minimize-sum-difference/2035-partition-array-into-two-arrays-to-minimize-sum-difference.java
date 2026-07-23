import java.util.*;

class Solution {

    public int minimumDifference(int[] nums) {

        int n = nums.length / 2;

        int[] left = Arrays.copyOfRange(nums, 0, n);
        int[] right = Arrays.copyOfRange(nums, n, 2 * n);

        int total = 0;
        for (int x : nums)
            total += x;

        List<List<Integer>> leftSums = new ArrayList<>();
        List<List<Integer>> rightSums = new ArrayList<>();

        for (int i = 0; i <= n; i++) {
            leftSums.add(new ArrayList<>());
            rightSums.add(new ArrayList<>());
        }

        generate(left, 0, 0, 0, leftSums);
        generate(right, 0, 0, 0, rightSums);

        for (int i = 0; i <= n; i++)
            Collections.sort(rightSums.get(i));

        int ans = Integer.MAX_VALUE;

        for (int leftCount = 0; leftCount <= n; leftCount++) {

            List<Integer> leftList = leftSums.get(leftCount);
            List<Integer> rightList = rightSums.get(n - leftCount);

            for (int leftSum : leftList) {

                int target = total / 2 - leftSum;

                int idx = Collections.binarySearch(rightList, target);

                if (idx < 0)
                    idx = -idx - 1;

                if (idx < rightList.size()) {
                    int sum = leftSum + rightList.get(idx);
                    ans = Math.min(ans, Math.abs(total - 2 * sum));
                }

                if (idx > 0) {
                    int sum = leftSum + rightList.get(idx - 1);
                    ans = Math.min(ans, Math.abs(total - 2 * sum));
                }
            }
        }

        return ans;
    }

    private void generate(int[] arr, int index, int count, int sum,
                          List<List<Integer>> lists) {

        if (index == arr.length) {
            lists.get(count).add(sum);
            return;
        }

        generate(arr, index + 1, count, sum, lists);

        generate(arr, index + 1, count + 1, sum + arr[index], lists);
    }
}