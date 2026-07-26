class Solution {

    public int maxSumSubmatrix(int[][] matrix, int k) {

        int rows = matrix.length;
        int cols = matrix[0].length;

        int ans = Integer.MIN_VALUE;

        for (int top = 0; top < rows; top++) {

            int[] sum = new int[cols];

            for (int bottom = top; bottom < rows; bottom++) {

                for (int c = 0; c < cols; c++)
                    sum[c] += matrix[bottom][c];

                TreeSet<Integer> set = new TreeSet<>();

                set.add(0);

                int prefix = 0;

                for (int x : sum) {

                    prefix += x;

                    Integer prev = set.ceiling(prefix - k);

                    if (prev != null)
                        ans = Math.max(ans, prefix - prev);

                    set.add(prefix);
                }
            }
        }

        return ans;
    }
}