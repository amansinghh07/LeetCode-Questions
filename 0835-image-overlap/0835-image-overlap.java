class Solution {
    public int largestOverlap(int[][] img1, int[][] img2) {
        int n = img1.length;
        int maxOverlap = 0;

        // Try every possible row translation
        for (int dr = -(n - 1); dr <= n - 1; dr++) {

            // Try every possible column translation
            for (int dc = -(n - 1); dc <= n - 1; dc++) {

                int overlap = 0;

                for (int i = 0; i < n; i++) {
                    for (int j = 0; j < n; j++) {

                        if (img1[i][j] == 1) {
                            int newRow = i + dr;
                            int newCol = j + dc;

                            // Check whether translated position
                            // is still inside the matrix
                            if (newRow >= 0 && newRow < n &&
                                newCol >= 0 && newCol < n &&
                                img2[newRow][newCol] == 1) {

                                overlap++;
                            }
                        }
                    }
                }

                maxOverlap = Math.max(maxOverlap, overlap);
            }
        }

        return maxOverlap;
    }
}