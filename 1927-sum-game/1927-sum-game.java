class Solution {
    public boolean sumGame(String num) {

        int n = num.length();
        int mid = n / 2;

        int diff = 0;
        int qDiff = 0;

        for (int i = 0; i < n; i++) {

            if (num.charAt(i) == '?') {

                if (i < mid) {
                    qDiff++;
                } else {
                    qDiff--;
                }

            } else {

                int digit = num.charAt(i) - '0';

                if (i < mid) {
                    diff -= digit;
                } else {
                    diff += digit;
                }
            }
        }

        return 2 * diff != 9 * qDiff;
    }
}