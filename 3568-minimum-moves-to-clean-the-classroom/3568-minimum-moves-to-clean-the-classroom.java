class Solution {
    public int minMoves(String[] classroom, int energy) {

        int m = classroom.length;
        int n = classroom[0].length();

        int startR = 0;
        int startC = 0;

        int[][] litterId = new int[m][n];

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                litterId[i][j] = -1;
            }
        }

        int litterCount = 0;

        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {

                char ch = classroom[i].charAt(j);

                if (ch == 'S') {
                    startR = i;
                    startC = j;
                }

                if (ch == 'L') {
                    litterId[i][j] = litterCount++;
                }
            }
        }

        int allCollected = (1 << litterCount) - 1;

        Queue<int[]> queue = new ArrayDeque<>();

        queue.offer(new int[]{
            startR,
            startC,
            0,
            energy,
            0
        });

        boolean[][][][] visited =
            new boolean[m][n][1 << litterCount][energy + 1];

        visited[startR][startC][0][energy] = true;

        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        while (!queue.isEmpty()) {

            int[] curr = queue.poll();

            int r = curr[0];
            int c = curr[1];
            int mask = curr[2];
            int currEnergy = curr[3];
            int moves = curr[4];

            if (mask == allCollected) {
                return moves;
            }

            if (currEnergy == 0) {
                continue;
            }

            for (int d = 0; d < 4; d++) {

                int nr = r + dr[d];
                int nc = c + dc[d];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n) {
                    continue;
                }

                if (classroom[nr].charAt(nc) == 'X') {
                    continue;
                }

                int newEnergy = currEnergy - 1;

                if (classroom[nr].charAt(nc) == 'R') {
                    newEnergy = energy;
                }

                int newMask = mask;

                if (classroom[nr].charAt(nc) == 'L') {
                    int id = litterId[nr][nc];
                    newMask |= (1 << id);
                }

                if (!visited[nr][nc][newMask][newEnergy]) {

                    visited[nr][nc][newMask][newEnergy] = true;

                    queue.offer(new int[]{
                        nr,
                        nc,
                        newMask,
                        newEnergy,
                        moves + 1
                    });
                }
            }
        }

        return -1;
    }
}