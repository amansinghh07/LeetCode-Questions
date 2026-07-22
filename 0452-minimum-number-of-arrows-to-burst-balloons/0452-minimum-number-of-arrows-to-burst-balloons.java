class Solution {
    public int findMinArrowShots(int[][] points) {

        Arrays.sort(points, Comparator.comparingInt(a -> a[0]));

        int arrows = 1;
        int prevEnd = points[0][1];

        for (int i = 1; i < points.length; i++) {

            if (points[i][0] <= prevEnd) {

                prevEnd = Math.min(prevEnd, points[i][1]);

            } else {

                arrows++;
                prevEnd = points[i][1];
            }
        }

        return arrows;
    }
}