class Solution {

    public int uniquePaths(int[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int MOD = 1_000_000_007;

        // Ways to reach a non-mirror cell
        int[][] dp = new int[m][n];

        // Ways to enter a mirror while moving RIGHT
        int[][] right = new int[m][n];

        // Ways to enter a mirror while moving DOWN
        int[][] down = new int[m][n];

        // Starting position
        dp[0][0] = 1;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                // CASE 1: Current cell is a mirror
                if (grid[i][j] == 1) {

                    // Entered this mirror while moving RIGHT
                    // Reflection forces us DOWN

                    if (right[i][j] > 0 && i + 1 < m) {

                        if (grid[i + 1][j] == 1) {

                            down[i + 1][j] =
                                (down[i + 1][j] + right[i][j]) % MOD;

                        } else {

                            dp[i + 1][j] =
                                (dp[i + 1][j] + right[i][j]) % MOD;
                        }
                    }

                    // Entered this mirror while moving DOWN
                    // Reflection forces us RIGHT

                    if (down[i][j] > 0 && j + 1 < n) {

                        if (grid[i][j + 1] == 1) {

                            right[i][j + 1] =
                                (right[i][j + 1] + down[i][j]) % MOD;

                        } else {

                            dp[i][j + 1] =
                                (dp[i][j + 1] + down[i][j]) % MOD;
                        }
                    }

                }

                // CASE 2: Current cell is empty
                else {

                    int ways = dp[i][j];

                    if (ways == 0) continue;

                    // Move RIGHT

                    if (j + 1 < n) {

                        if (grid[i][j + 1] == 1) {

                            right[i][j + 1] =
                                (right[i][j + 1] + ways) % MOD;

                        } else {

                            dp[i][j + 1] =
                                (dp[i][j + 1] + ways) % MOD;
                        }
                    }

                    // Move DOWN

                    if (i + 1 < m) {

                        if (grid[i + 1][j] == 1) {

                            down[i + 1][j] =
                                (down[i + 1][j] + ways) % MOD;

                        } else {

                            dp[i + 1][j] =
                                (dp[i + 1][j] + ways) % MOD;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1];
    }
}