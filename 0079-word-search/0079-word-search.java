class Solution {

    int rows;
    int cols;

    public boolean exist(char[][] board, String word) {

        rows = board.length;
        cols = board[0].length;

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {

                if (board[i][j] == word.charAt(0)) {

                    if (dfs(board, word, i, j, 0)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private boolean dfs(char[][] board, String word,
                        int row, int col, int index) {

        // All characters matched
        if (index == word.length()) {
            return true;
        }

        // Out of bounds
        if (row < 0 || row >= rows ||
            col < 0 || col >= cols) {
            return false;
        }

        // Wrong character
        if (board[row][col] != word.charAt(index)) {
            return false;
        }

        // Mark cell as visited
        char original = board[row][col];
        board[row][col] = '#';

        // Explore four directions
        boolean found =
                dfs(board, word, row + 1, col, index + 1) ||
                dfs(board, word, row - 1, col, index + 1) ||
                dfs(board, word, row, col + 1, index + 1) ||
                dfs(board, word, row, col - 1, index + 1);

        // Backtrack
        board[row][col] = original;

        return found;
    }
}