class Solution {

    static final long MOD = 1_000_000_007L;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;

        if (n == 1) return m;

        int sz = 2 * m;

        long[][] T = new long[sz][sz];

        // U'(v) = sum D(x), x < v
        for (int v = 0; v < m; v++) {
            for (int x = 0; x < v; x++) {
                T[v][m + x] = 1;
            }
        }

        // D'(v) = sum U(x), x > v
        for (int v = 0; v < m; v++) {
            for (int x = v + 1; x < m; x++) {
                T[m + v][x] = 1;
            }
        }

        long[] base = new long[sz];

        // length = 2
        for (int v = 0; v < m; v++) {
            base[v] = v;             // U[v] = v-1 (0-based => v)
            base[m + v] = m - v - 1; // D[v]
        }

        long[] res;

        if (n == 2) {
            res = base;
        } else {
            long[][] P = matrixPower(T, n - 2);
            res = multiply(P, base);
        }

        long ans = 0;
        for (long x : res) {
            ans = (ans + x) % MOD;
        }

        return (int) ans;
    }

    private long[][] matrixPower(long[][] A, long exp) {
        int n = A.length;

        long[][] res = new long[n][n];
        for (int i = 0; i < n; i++) {
            res[i][i] = 1;
        }

        while (exp > 0) {
            if ((exp & 1) == 1) {
                res = multiply(res, A);
            }

            A = multiply(A, A);
            exp >>= 1;
        }

        return res;
    }

    private long[][] multiply(long[][] A, long[][] B) {
        int n = A.length;
        long[][] C = new long[n][n];

        for (int i = 0; i < n; i++) {
            for (int k = 0; k < n; k++) {
                if (A[i][k] == 0) continue;

                long val = A[i][k];

                for (int j = 0; j < n; j++) {
                    if (B[k][j] == 0) continue;

                    C[i][j] = (C[i][j] + val * B[k][j]) % MOD;
                }
            }
        }

        return C;
    }

    private long[] multiply(long[][] A, long[] v) {
        int n = A.length;
        long[] res = new long[n];

        for (int i = 0; i < n; i++) {
            long cur = 0;

            for (int j = 0; j < n; j++) {
                if (A[i][j] == 0) continue;

                cur = (cur + A[i][j] * v[j]) % MOD;
            }

            res[i] = cur;
        }

        return res;
    }
}