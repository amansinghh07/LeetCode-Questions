class Solution {
    public int earliestFinishTime(int[] landStartTime, int[] landDuration,
                                  int[] waterStartTime, int[] waterDuration) {

        int n = landStartTime.length;
        int m = waterStartTime.length;
        int ans = Integer.MAX_VALUE;

        // Case 1: Land ride first, then water ride
        for (int i = 0; i < n; i++) {
            int landFinish = landStartTime[i] + landDuration[i];

            for (int j = 0; j < m; j++) {
                int waterStart = Math.max(landFinish, waterStartTime[j]);
                int finish = waterStart + waterDuration[j];

                ans = Math.min(ans, finish);
            }
        }

        // Case 2: Water ride first, then land ride
        for (int j = 0; j < m; j++) {
            int waterFinish = waterStartTime[j] + waterDuration[j];

            for (int i = 0; i < n; i++) {
                int landStart = Math.max(waterFinish, landStartTime[i]);
                int finish = landStart + landDuration[i];

                ans = Math.min(ans, finish);
            }
        }

        return ans;
    }
}