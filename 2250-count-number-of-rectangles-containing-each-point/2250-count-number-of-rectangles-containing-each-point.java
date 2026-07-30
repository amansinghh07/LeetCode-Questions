class Solution {

    public int[] countRectangles(int[][] rectangles, int[][] points) {

        List<Integer>[] buckets = new ArrayList[101];

        for (int i = 1; i <= 100; i++)
            buckets[i] = new ArrayList<>();

        // Group rectangle lengths by height
        for (int[] rect : rectangles) {
            buckets[rect[1]].add(rect[0]);
        }

        // Sort each bucket
        for (int i = 1; i <= 100; i++) {
            Collections.sort(buckets[i]);
        }

        int[] ans = new int[points.length];

        for (int i = 0; i < points.length; i++) {

            int x = points[i][0];
            int y = points[i][1];

            int count = 0;

            // Check all heights >= y
            for (int h = y; h <= 100; h++) {

                List<Integer> list = buckets[h];

                int idx = lowerBound(list, x);

                count += list.size() - idx;
            }

            ans[i] = count;
        }

        return ans;
    }

    private int lowerBound(List<Integer> list, int target) {

        int left = 0;
        int right = list.size();

        while (left < right) {

            int mid = left + (right - left) / 2;

            if (list.get(mid) >= target)
                right = mid;
            else
                left = mid + 1;
        }

        return left;
    }
}