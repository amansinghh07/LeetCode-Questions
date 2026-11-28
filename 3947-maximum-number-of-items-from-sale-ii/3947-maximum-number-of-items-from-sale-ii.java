
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        // Step 1: Frequency of each factor.
        int[] freq = new int[n + 1];

        for (int[] item : items) {
            freq[item[0]]++;
        }

        // Step 2: Precompute bonus capacity for each factor.
        int[] bonusByFactor = new int[n + 1];

        for (int f = 1; f <= n; f++) {

            int count = 0;

            // Count all item types whose factor is divisible by f.
            for (int j = f; j <= n; j += f) {
                count += freq[j];
            }

            // Exclude the current item itself.
            bonusByFactor[f] = count - 1;
        }

        // Step 3: Store [price, bonus capacity].
        int[][] arr = new int[n][2];

        long minPrice = Long.MAX_VALUE;

        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            arr[i][0] = price;
            arr[i][1] = bonusByFactor[factor];

            minPrice = Math.min(minPrice, price);
        }

        // Step 4: Sort by price ascending.
        Arrays.sort(arr, (a, b) ->
            Integer.compare(a[0], b[0])
        );

        long remaining = budget;
        long ans = 0;

        // Step 5: Buy profitable bonus copies.
        for (int[] item : arr) {

            long price = item[0];
            long bonus = item[1];

            if (price > 2 * minPrice) {
                break;
            }

            long copies = Math.min(bonus, remaining / price);

            ans += 2 * copies;
            remaining -= copies * price;
        }

        // Step 6: Buy normal copies at minimum price.
        ans += remaining / minPrice;

        return (int) ans;
    }
}
