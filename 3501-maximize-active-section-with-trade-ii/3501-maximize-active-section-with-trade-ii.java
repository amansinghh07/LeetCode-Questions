import java.util.*;

class Solution {
    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();

        // 1) Build run-length encoding of s
        List<int[]> tmp = new ArrayList<>();
        int i = 0;
        while (i < n) {
            int j = i;
            char c = s.charAt(i);
            while (j < n && s.charAt(j) == c) j++;
            tmp.add(new int[]{i, j - 1, c - '0'});
            i = j;
        }
        int m = tmp.size();
        int[] runStart = new int[m], runEnd = new int[m], runVal = new int[m], runLen = new int[m];
        for (int k = 0; k < m; k++) {
            int[] r = tmp.get(k);
            runStart[k] = r[0];
            runEnd[k] = r[1];
            runVal[k] = r[2];
            runLen[k] = r[1] - r[0] + 1;
        }

        // 2) position -> run index
        int[] pos2run = new int[n];
        for (int k = 0; k < m; k++)
            for (int p = runStart[k]; p <= runEnd[k]; p++) pos2run[p] = k;

        int total1 = 0;
        for (int p = 0; p < n; p++) if (s.charAt(p) == '1') total1++;

        final int NEG_INF = Integer.MIN_VALUE / 2;

        // 3) G[k] = gain of merging run k (a '1' run) with its two neighbors, if k is strictly interior
        int[] G = new int[m];
        for (int k = 0; k < m; k++) {
            if (runVal[k] == 1 && k > 0 && k < m - 1) {
                G[k] = runLen[k - 1] + runLen[k + 1];
            } else {
                G[k] = NEG_INF;
            }
        }

        // 4) Sparse table for range-max on G
        int LOG = 1;
        while ((1 << LOG) <= m) LOG++;
        int[][] table = new int[LOG][m];
        if (m > 0) table[0] = G.clone();
        for (int jj = 1; jj < LOG; jj++) {
            int half = 1 << (jj - 1);
            for (int ii = 0; ii + (1 << jj) <= m; ii++) {
                table[jj][ii] = Math.max(table[jj - 1][ii], table[jj - 1][ii + half]);
            }
        }
        int[] logTable = new int[m + 1];
        for (int k = 2; k <= m; k++) logTable[k] = logTable[k / 2] + 1;

        // 5) Answer queries
        List<Integer> ans = new ArrayList<>(queries.length);
        for (int[] query : queries) {
            int l = query[0], r = query[1];
            int idxL = pos2run[l], idxR = pos2run[r];
            int bestGain = 0;

            if (idxL != idxR) {
                int low = idxL + 1, high = idxR - 1;
                if (low <= high) {
                    if (low == high) {
                        int k = low;
                        if (runVal[k] == 1) {
                            bestGain = (runStart[k] - l) + (r - runEnd[k]);
                        }
                    } else {
                        int valLow = (runVal[low] == 1)
                                ? (runStart[low] - l) + runLen[low + 1] : NEG_INF;
                        int valHigh = (runVal[high] == 1)
                                ? runLen[high - 1] + (r - runEnd[high]) : NEG_INF;
                        int valMid = NEG_INF;
                        if (low + 1 <= high - 1) {
                            int a = low + 1, b = high - 1;
                            int jj = logTable[b - a + 1];
                            valMid = Math.max(table[jj][a], table[jj][b - (1 << jj) + 1]);
                        }
                        bestGain = Math.max(valLow, Math.max(valHigh, valMid));
                        bestGain = Math.max(bestGain, 0);
                    }
                }
            }

            ans.add(total1 + bestGain);
        }
        return ans;
    }
}