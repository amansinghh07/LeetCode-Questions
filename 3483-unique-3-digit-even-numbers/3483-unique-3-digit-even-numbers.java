class Solution {
    public int totalNumbers(int[] digits) {

        int[] freq = new int[10];

        // Count how many times each digit occurs
        for (int digit : digits) {
            freq[digit]++;
        }

        int count = 0;

        // Try every 3-digit number
        for (int num = 100; num <= 999; num++) {

            // Must be even
            if (num % 2 != 0) {
                continue;
            }

            int ones = num % 10;
            int tens = (num / 10) % 10;
            int hundreds = num / 100;

            // Check whether we have enough copies of each digit
            int[] needed = new int[10];

            needed[ones]++;
            needed[tens]++;
            needed[hundreds]++;

            boolean possible = true;

            for (int d = 0; d <= 9; d++) {
                if (needed[d] > freq[d]) {
                    possible = false;
                    break;
                }
            }

            if (possible) {
                count++;
            }
        }

        return count;
    }
}