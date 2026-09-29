class Solution {

    public boolean hasValidPath(char[][] grid) {

        int m = grid.length;
        int n = grid[0].length;

        int len = m + n - 1;

        // Valid parentheses string must have even length
        if (len % 2 == 1) {
            return false;
        }

        // First character must be '('
        if (grid[0][0] == ')') {
            return false;
        }

        boolean[][][] dp = new boolean[m][n][len + 1];

        // We have already processed (0,0)
        dp[0][0][1] = true;

        for (int i = 0; i < m; i++) {

            for (int j = 0; j < n; j++) {

                for (int balance = 0; balance <= len; balance++) {

                    if (!dp[i][j][balance]) {
                        continue;
                    }

                    // ----------------
                    // Move DOWN
                    // ----------------
                    if (i + 1 < m) {

                        int newBalance = balance;

                        if (grid[i + 1][j] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i + 1][j][newBalance] = true;
                        }
                    }

                    // ----------------
                    // Move RIGHT
                    // ----------------
                    if (j + 1 < n) {

                        int newBalance = balance;

                        if (grid[i][j + 1] == '(') {
                            newBalance++;
                        } else {
                            newBalance--;
                        }

                        if (newBalance >= 0) {
                            dp[i][j + 1][newBalance] = true;
                        }
                    }
                }
            }
        }

        return dp[m - 1][n - 1][0];
    }
}