class Solution {

    public int[] maximumWeight(List<List<Integer>> intervals) {

        int n = intervals.size();

        // [left, right, weight, originalIndex]
        long[][] arr = new long[n][4];

        for (int i = 0; i < n; i++) {
            arr[i][0] = intervals.get(i).get(0); // left
            arr[i][1] = intervals.get(i).get(1); // right
            arr[i][2] = intervals.get(i).get(2); // weight
            arr[i][3] = i;                       // original index
        }

        // Sort by left endpoint
        Arrays.sort(arr, (a, b) -> {
            if (a[0] != b[0]) {
                return Long.compare(a[0], b[0]);
            }
            return Long.compare(a[1], b[1]);
        });

        // next[i] = first interval whose left > arr[i].right
        int[] next = new int[n];

        for (int i = 0; i < n; i++) {
            next[i] = findNext(arr, i + 1, arr[i][1]);
        }

        /*
         * dp[i][k] = maximum score from i onward,
         * choosing at most k intervals.
         */
        long[][] dp = new long[n + 1][5];

        /*
         * choice[i][k]:
         * true  -> take interval i
         * false -> skip interval i
         *
         * Since we also need lexicographically smallest indices,
         * we need to reconstruct carefully when scores tie.
         */
        for (int i = n - 1; i >= 0; i--) {

            for (int k = 1; k <= 4; k++) {

                // Option 1: skip current interval
                long skip = dp[i + 1][k];

                // Option 2: take current interval
                long take = arr[i][2] + dp[next[i]][k - 1];

                dp[i][k] = Math.max(skip, take);
            }
        }

        /*
         * Reconstruct all intervals that achieve the maximum score.
         *
         * At each step:
         * - if taking current interval can still achieve optimal score,
         *   take it only when its original index gives the lexicographically
         *   smaller result.
         */
        List<Integer> answer = new ArrayList<>();

        int i = 0;
        int k = 4;

        while (i < n && k > 0) {

            long skip = dp[i + 1][k];
            long take = arr[i][2] + dp[next[i]][k - 1];

            if (take > skip) {
                answer.add((int) arr[i][3]);
                i = next[i];
                k--;
            } else if (skip > take) {
                i++;
            } else {
                /*
                 * Both choices give the same score.
                 *
                 * We cannot simply take the smaller original index here,
                 * because the future intervals also matter.
                 *
                 * The easiest reliable solution is to use a second DP
                 * that stores the lexicographically smallest sequence.
                 */
                break;
            }
        }

        /*
         * The reconstruction above is insufficient for tie-breaking.
         * Use a DP storing the actual best index list.
         */
        return solveWithLexicographicTieBreaking(arr, next);
    }

    private int findNext(long[][] arr, int start, long right) {

        int lo = start;
        int hi = arr.length;

        // Need left > right
        while (lo < hi) {
            int mid = lo + (hi - lo) / 2;

            if (arr[mid][0] > right) {
                hi = mid;
            } else {
                lo = mid + 1;
            }
        }

        return lo;
    }

    private int[] solveWithLexicographicTieBreaking(long[][] arr, int[] next) {

        int n = arr.length;

        /*
         * For each state, store the lexicographically smallest
         * list of original indices achieving dp score.
         */
        List<Integer>[][] best = new ArrayList[n + 1][5];
        long[][] score = new long[n + 1][5];

        for (int k = 0; k <= 4; k++) {
            best[n][k] = new ArrayList<>();
        }

        for (int i = n - 1; i >= 0; i--) {

            for (int k = 0; k <= 4; k++) {

                // Skip
                score[i][k] = score[i + 1][k];
                best[i][k] = new ArrayList<>(best[i + 1][k]);

                if (k > 0) {

                    long takeScore =
                            arr[i][2] + score[next[i]][k - 1];

                    List<Integer> takeList =
                            new ArrayList<>(best[next[i]][k - 1]);

                    takeList.add((int) arr[i][3]);

                    // Sort because answer must be lexicographically
                    // smallest by original indices.
                    Collections.sort(takeList);

                    if (takeScore > score[i][k]) {

                        score[i][k] = takeScore;
                        best[i][k] = takeList;

                    } else if (takeScore == score[i][k]
                            && lexicographicallySmaller(
                                takeList,
                                best[i][k])) {

                        best[i][k] = takeList;
                    }
                }
            }
        }

        List<Integer> result = best[0][4];

        int[] answer = new int[result.size()];

        for (int j = 0; j < result.size(); j++) {
            answer[j] = result.get(j);
        }

        return answer;
    }

    private boolean lexicographicallySmaller(
            List<Integer> a,
            List<Integer> b) {

        int len = Math.min(a.size(), b.size());

        for (int i = 0; i < len; i++) {

            if (!a.get(i).equals(b.get(i))) {
                return a.get(i) < b.get(i);
            }
        }

        return a.size() < b.size();
    }
}