
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int minPrice = Integer.MAX_VALUE;

        int[] dp = new int[budget + 1];

        // Find the minimum price.
        for (int[] item : items) {
            minPrice = Math.min(minPrice, item[1]);
        }

        // 0/1 Knapsack: first purchase of each item type.
        for (int i = 0; i < n; i++) {

            int factor = items[i][0];
            int price = items[i][1];

            // Count purchased item + free items.
            int value = 0;

            for (int j = 0; j < n; j++) {
                if (items[j][0] % factor == 0) {
                    value++;
                }
            }

            // Each item type can provide free items only once.
            for (int b = budget; b >= price; b--) {
                dp[b] = Math.max(
                    dp[b],
                    dp[b - price] + value
                );
            }
        }

        // Use remaining budget to buy cheapest items.
        int ans = 0;

        for (int b = 0; b <= budget; b++) {
            ans = Math.max(
                ans,
                dp[b] + (budget - b) / minPrice
            );
        }

        return ans;
    }
}
